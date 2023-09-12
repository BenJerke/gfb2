package dev.ben.dao;

import dev.ben.model.Session;

public interface SessionDao {
    public Session createSession(int userId, long session_started, long );

}
