package dev.ben.dao;

import dev.ben.model.Tag;
import dev.ben.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTaskDaoTests extends BaseDaoTests{
    private JdbcTaskDao sut;
    private JdbcUserDao userDao;
    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTaskDao(jdbcTemplate);
        userDao = new JdbcUserDao(jdbcTemplate);
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
    @Test
    public void createTag(){
    }

    @Test
    public void addTagToTask(){
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

        Tag tag = new Tag(1, "test tag");

        Tag createdTag = sut.createTag(tag);

        createdTask.addTag(createdTag);
        // gotta actually put it in the database
        sut.addTagsToTask(createdTask);

        List<Tag> tags = sut.getTagsByTaskId(createdTask.getId());

        Assert.assertNotNull("Tags were null.", tags);
        Assert.assertEquals("Wrong number of tags returned.", 1, tags.size());
        Assert.assertEquals("Tag IDs don't match.", tag.getId(), tags.get(0).getId());
        Assert.assertEquals("Tag descriptions don't match.", tag.getDescription(), tags.get(0).getDescription());
    }

    @Test
    public void getTasksByUserId(){
        List<Task> tasks = sut.listUserTasks(userDao.getUserById(1));
        Assert.assertNotNull("Tasks were null.", tasks);
        Assert.assertEquals("Wrong number of tasks returned.", 3, tasks.size());
    }

    @Test
    public void addAllowedUserToTask(){
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

        createdTask.addAllowedUser(2);
        for(int i : createdTask.getAllowedUserIds()){
            System.out.println(i);
        }
        sut.updateTask(createdTask);

        List<Integer> allowedUserIds = sut.getAllowedUserIds(createdTask.getId());
        Assert.assertNotNull("Allowed user IDs were null.", allowedUserIds);
        Assert.assertEquals("Wrong number of allowed user IDs returned.", 2, allowedUserIds.size());
        Assert.assertEquals("User ID 1 wasn't present.", 1, allowedUserIds.get(0).intValue());
        Assert.assertEquals("User ID 2 wasn't present.", 2, allowedUserIds.get(1).intValue());
    }

    @Test
    public void removeAllowedUserFromTask(){
        List<Integer> allowedUserIds = new ArrayList<>();
        allowedUserIds.add(1);
        allowedUserIds.add(2);
        allowedUserIds.add(3);

        Task testTask = new Task(1, "user removal test task", 0L, 0L, 0);
        testTask.setAllowedUserIds(allowedUserIds);
        sut.createTask(testTask);

        testTask.removeAllowedUser(2);

        Task returnedTask = sut.updateTask(testTask);

        Assert.assertEquals("Wrong number of allowed user IDs returned.", 2, sut.getAllowedUserIds(returnedTask.getId()).size());
        Assert.assertFalse("User ID 2 was still present.", sut.getAllowedUserIds(returnedTask.getId()).contains(2));
        Assert.assertTrue("User ID 1 wasn't present.", sut.getAllowedUserIds(returnedTask.getId()).contains(1));
    }

    @Test
    public void removeTagFromTask(){
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

        Tag tag = new Tag(1, "test tag");

        Tag createdTag = sut.createTag(tag);

        createdTask.addTag(createdTag);
        // gotta actually put it in the database
        sut.addTagsToTask(createdTask);

        List<Tag> tags = sut.getTagsByTaskId(createdTask.getId());

        Assert.assertNotNull("Tags were null.", tags);
        Assert.assertEquals("Wrong number of tags returned.", 1, tags.size());
        Assert.assertEquals("Tag IDs don't match.", tag.getId(), tags.get(0).getId());
        Assert.assertEquals("Tag descriptions don't match.", tag.getDescription(), tags.get(0).getDescription());

        createdTask.removeTag(createdTag);

        createdTask = sut.updateTask(createdTask);

        tags = sut.getTagsByTaskId(createdTask.getId());

        Assert.assertNotNull("Tags were null.", tags);
        Assert.assertEquals("Wrong number of tags returned.", 0, tags.size());
    }

}
