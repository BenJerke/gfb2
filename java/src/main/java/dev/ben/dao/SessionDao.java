package dev.ben.dao;

import dev.ben.model.Session;

import java.util.List;

public interface SessionDao {
    public void createSession(Session newSession);
    public List<Session> getSessionsForDateRange(int userId, long startDateMilliseconds, long endDateMilliseconds);

}
