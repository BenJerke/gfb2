package dev.ben.dao;

import dev.ben.model.Session;
import dev.ben.model.Task;
import dev.ben.model.WorkPeriod;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class ResultSetMappers {

    public static Task mapRowToTask(SqlRowSet rs){
        Task newTask = new Task();
        newTask.setId(rs.getInt("task_id"));
        newTask.setOwningUserId(rs.getInt("task_owner"));
        newTask.setDescription(rs.getString("task_description"));
        newTask.setEstimatedDuration(rs.getLong("task_estimated_duration"));
        newTask.setActualDuration(rs.getLong("task_actual_duration"));
        newTask.setStatusId(rs.getInt("task_status_id"));
        return newTask;
    }

    public static Session mapRowToSession(SqlRowSet rs){
        Session newSession = new Session();
        newSession.setId(rs.getInt("session_id"));
        newSession.setUserId(rs.getInt("user_id"));
        newSession.setNotes(rs.getString("notes"));
        newSession.setStartTimeMilliseconds(rs.getLong("start_time_milliseconds"));
        newSession.setEndTimeMilliseconds(rs.getLong("end_time_milliseconds"));
        return newSession;
    }

    public static WorkPeriod mapRowToWorkPeriod(SqlRowSet rs){
        WorkPeriod newWorkPeriod = new WorkPeriod();
        newWorkPeriod.setSessionId(rs.getInt("session_id"));
        newWorkPeriod.setTaskId(rs.getInt("task_id"));
        newWorkPeriod.setStartTimeMilliseconds(rs.getLong("start_time_milliseconds"));
        newWorkPeriod.setEndTimeMilliseconds(rs.getLong("end_time_milliseconds"));
        return newWorkPeriod;
    }
}
