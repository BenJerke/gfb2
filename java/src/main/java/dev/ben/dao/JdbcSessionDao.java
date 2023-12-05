package dev.ben.dao;

import dev.ben.model.Session;
import dev.ben.model.WorkPeriod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static dev.ben.dao.ResultSetMappers.mapRowToSession;
import static dev.ben.dao.ResultSetMappers.mapRowToWorkPeriod;

import java.util.List;

@Component
public class JdbcSessionDao implements SessionDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcSessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Session createSession(Session newSession) {
        // we should never update a session - they should come in from the client fully populated with tasks and work periods.

        String sql = "INSERT INTO sessions (user_id, notes, start_time_milliseconds, end_time_milliseconds) VALUES (?, ?, ?, ?) RETURNING session_id";
        try {
            int newId = jdbcTemplate.queryForObject(sql, Integer.class, newSession.getUserId(), newSession.getNotes(), newSession.getStartTimeMilliseconds(), newSession.getEndTimeMilliseconds());
            newSession.setId(newId);

            for (WorkPeriod wp : newSession.getWorkPeriods()){
                String sql2 = "INSERT INTO work_periods (session_id, task_id, start_time_milliseconds, end_time_milliseconds) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(sql2, newSession.getId(), wp.getTaskId(), wp.getStartTimeMilliseconds(), wp.getEndTimeMilliseconds());
            }

            // now we need to add the work periods
            // we'll do this in a separate transaction
        } catch (RuntimeException e) {
            System.out.println(e);
            throw e;
        }

        return newSession;
    }


    @Override
    @Transactional
    public List<Session> listUserSessions(int userId) {
        List<Session> userSessions = null;
        String sql = "SELECT session_id, user_id, session_notes, session_started, session_ended FROM sessions WHERE user_id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
            while (rs.next()) {
                userSessions.add(mapRowToSession(rs));
            }
            for(Session s : userSessions){
                String sql2 = "SELECT  FROM session_tasks WHERE session_id = ?";
                SqlRowSet rs2 = jdbcTemplate.queryForRowSet(sql2, s.getId());
                while (rs2.next()){
                    s.getWorkPeriods().add(mapRowToWorkPeriod(rs2));
                }
            }
        } catch (RuntimeException e) {
            System.out.println(e);
            throw e;
        }
        return userSessions;
    }

    @Override
    public Session findSessionById(int sessionId) {
        return null;
    }

    @Override
    public List<Session> findSessionsByDateRange(long startTimestamp, long endTimestamp) {
        return null;
    }

    @Override
    public List<Session> findSessionsByTaskId(int taskId) {
        return null;
    }

    @Override
    public List<Session> findSessionsByTaskIdList(List<Integer> taskIds) {
        return null;
    }
}
