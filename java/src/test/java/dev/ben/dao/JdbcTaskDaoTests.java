package dev.ben.dao;

import dev.ben.model.Tag;
import dev.ben.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;


public class JdbcTaskDaoTests extends BaseDaoTests{
    private JdbcTaskDao sut;
    private JdbcTemplate jdbcTemplate;
    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTaskDao(jdbcTemplate);
    }

    @Test
    public void createTask(){
        Task basicTask = new Task(1, "basic test task", 0L, 0L, 0);
        Task createdTask = null;
        try {
            createdTask = sut.createTask(basicTask);
            if (createdTask == null){
                throw new SQLException("Created task was null.");
            }
        } catch (SQLException e){
            Assert.fail("Exception was thrown when creating task.");
        }

        Assert.assertNotNull("Created task was null.", createdTask);
        Assert.assertEquals("Descriptions don't match.", basicTask.getDescription(), createdTask.getDescription());
        Assert.assertEquals("Estimated durations don't match.", basicTask.getEstimatedDuration(), createdTask.getEstimatedDuration());
        Assert.assertEquals("Actual durations don't match.", basicTask.getActualDuration(), createdTask.getActualDuration());
        Assert.assertEquals("Owning User ID wasn't set.", basicTask.getOwningUserId(), createdTask.getOwningUserId());

    }

}
