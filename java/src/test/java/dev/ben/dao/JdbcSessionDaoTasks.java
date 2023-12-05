package dev.ben.dao;

import dev.ben.model.Session;
import org.junit.Test;

public class JdbcSessionDaoTasks extends BaseDaoTests{
    private TaskDao taskDao;
    private UserDao userDao;

    private SessionDao sessionDao;
    public JdbcSessionDaoTasks(JdbcTaskDao taskDao, JdbcUserDao userDao){
        this.taskDao = taskDao;
        this.userDao = userDao;
    }
//    @Test
//    public void createSession(Session newSession){
//        int userId = userDao.findIdByUsername(principal.getName());
//        newSession.setUserId(userId);
//        try{
//            return taskDao.createSession(newSession);
//        } catch (RuntimeException e){
//            System.out.print(e.getMessage());
//            e.printStackTrace();
//            throw new RuntimeException("Session creation failed.");
//        }
//    }
}
