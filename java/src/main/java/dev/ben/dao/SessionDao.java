package dev.ben.dao;

import dev.ben.model.Session;
import dev.ben.model.WorkPeriod;

import java.util.List;

public interface SessionDao {
    Session createSession(Session newSession);
    // work periods, notes, and start/end times

    List<Session> listUserSessions(int userId);

    Session findSessionById(int sessionId);

    List<Session> findSessionsByDateRange(long startTimestamp, long endTimestamp);

    List<Session> findSessionsByTaskId(int taskId);

    List<Session> findSessionsByTaskIdList(List<Integer> taskIds);



}
