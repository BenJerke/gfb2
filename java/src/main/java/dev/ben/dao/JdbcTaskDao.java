package dev.ben.dao;
import dev.ben.model.Tag;
import dev.ben.model.Task;
import dev.ben.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static dev.ben.dao.ResultSetMappers.mapRowToTask;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcTaskDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }

    @Override
    @Transactional
    public Task createTask(Task newTask){
        try{
            jdbcTemplate.update("BEGIN TRANSACTION;");
            String createTaskBaseQuery = "INSERT INTO tasks (task_owner, task_description, task_estimated_duration, task_actual_duration, task_status_id)" +
                    " VALUES(?, ?, ?, ?, ?) RETURNING task_id";

            Integer createdTaskId = jdbcTemplate.queryForObject(createTaskBaseQuery, Integer.class, newTask.getOwningUserId(), newTask.getDescription(), newTask.getEstimatedDuration(), newTask.getActualDuration(),newTask.getStatusId());

            newTask.setId(createdTaskId);

            int usersAdded = addAllowedUsers(newTask);

            addTagsToTask(newTask);

            if (createdTaskId == 0 || usersAdded == 0){
                throw new RuntimeException("Task creation failed.");
            }
            newTask.setId(createdTaskId);
            jdbcTemplate.update("COMMIT TRANSACTION;");
            return newTask;
        } catch (RuntimeException e){
            jdbcTemplate.update("ROLLBACK TRANSACTION;");
            throw e;
        }

    }
    @Transactional
    public List<Integer> getAllowedUserIds(int taskId){
        String sql = "SELECT user_id FROM user_tasks WHERE task_id = ?";
        List<Integer> allowedUserIds = jdbcTemplate.queryForList(sql, Integer.class, taskId);
        return allowedUserIds;
    }

    @Transactional
    public int addAllowedUsers(Task task) {
        String addUserTaskQuery = "INSERT INTO user_tasks (user_id, task_id) VALUES (?, ?)";
        int userTableEntries = 0;
        for(Integer userId : task.getAllowedUserIds()){
            userTableEntries += jdbcTemplate.update(addUserTaskQuery, userId, task.getId());
        }
        if (userTableEntries == 0){
            throw new RuntimeException("User additions failed.");
        }
        return userTableEntries;
    }
    @Transactional
    public Tag createTag(Tag tag){

        String tagInsertSql = "INSERT INTO tags (tag_name, user_id) VALUES (?, ?) RETURNING tag_id;";

        Integer createdTagId = jdbcTemplate.queryForObject(tagInsertSql, Integer.class, tag.getDescription(), tag.getUserId());

        if(createdTagId == 0){
            throw new RuntimeException("Tag creation failed.");
        }

        tag.setId(createdTagId);

        String userTagInsertSql = "INSERT INTO user_tags (user_id, tag_id) VALUES (?, ?)";

        int userTagRows = jdbcTemplate.update(userTagInsertSql, tag.getUserId(), tag.getId());

        if(userTagRows == 0){
            throw new RuntimeException("User tag assignment failed.");
        }

        return tag;
    }

    @Transactional
    public Tag findTagById(int tagId){
        String sql = "SELECT tag_id, tag_name, user_id FROM tags WHERE tag_id = ?";

        Tag newTag = jdbcTemplate.queryForObject(sql, Tag.class, tagId);

        if(newTag == null){
            throw new RuntimeException("Tag not found.");
        }

        return newTag;
    }



    @Transactional
    @Override
    public Task updateTask(Task task){

        // Access this when doing a PUT on a task
        // This can happen when a user edits a task outside a session, or when a session is completed -
        // the session will update the task with the actual duration, status, new tags, new users; whatever happened while working.
        // Add/remove tags from the task
        // Add/remove users from the task
        // Add/remove notes from the task
        // Update the task owner
        // Update the task description
        // Update the task estimated duration
        // Update the actual duration

        String sql = "UPDATE tasks SET task_owner = ?, task_description = ?, task_estimated_duration = ?, task_actual_duration = ?, task_status_id = ? WHERE task_id = ?";

        removeAllowedUsersFromTask(task);
        removeTagsFromTask(task);
        addTagsToTask(task);
        addAllowedUsers(task);

        int taskUpdateCount = jdbcTemplate.update(sql, task.getOwningUserId(), task.getDescription(), task.getEstimatedDuration(), task.getActualDuration(), task.getStatusId(), task.getId());

        if (taskUpdateCount == 0){
            throw new RuntimeException("Task update failed.");
        }

        return task;
    }

    @Transactional
    public void removeTagsFromTask(Task task){
        String sql = "DELETE FROM task_tags WHERE task_id = ?;";
        // this will get rid of every task-tag entry for the given task.
        try {jdbcTemplate.update(sql, task.getId());
        } catch (RuntimeException e){
            throw e;
        }
    }


    public void addTagsToTask(Task task) {
        // When we create a tag, check to see if there are any tags.
        if(task.getTags() == null || task.getTags().size() == 0){
            return;
        }

        String addTagQuery = "INSERT INTO task_tags (task_id, tag_id) VALUES (?, ?)";

        int tagTableEntryCount = 0;
        for (Tag tag : task.getTags()){
            tagTableEntryCount += jdbcTemplate.update(addTagQuery, task.getId(), tag.getId());
        }
        if (tagTableEntryCount == 0){
            throw new RuntimeException("Tag creation failed.");
        }
    }

    public void removeAllowedUsersFromTask(Task task){
        int deletedRows = 0;
        if(task.getAllowedUserIds().size() == 1){
            return;
        }
        String sql = "DELETE FROM user_tasks WHERE task_id = ?;";
        deletedRows = jdbcTemplate.update(sql, task.getId());
        if (deletedRows == 0){
            throw new RuntimeException("User removal failed.");
        }
    }


    @Override
    public List<Task> listUserTasks(User user) {
        List<Task> userTasks = new ArrayList<>();
        String taskQuery = "SELECT task_id, task_owner, task_description, task_estimated_duration, task_actual_duration, task_status_id FROM tasks WHERE task_id IN (SELECT task_id FROM user_tasks WHERE user_id = ?);";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(taskQuery, user.getId());
            while(rs.next()){
                userTasks.add(mapRowToTask(rs));
            }

        } catch (RuntimeException e){
            throw e;
        }

        for (Task task : userTasks){
            task.setAllowedUserIds(getAllowedUserIds(task.getId()));
            task.setTags(getTagsByTaskId(task.getId()));
        }

        return userTasks;
    }

    @Override
    public List<Task> listUserTasksByStatus(int status, User user) {
        return null;
    }

    @Override
    @Transactional
    public Task findTaskById(int id) {
        Task foundTask = null;
        String sql = "SELECT task_id, task_owner, task_description, task_estimated_duration, task_actual_duration, task_status_id FROM tasks WHERE task_id = ?;";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            foundTask = mapRowToTask(rs);
            foundTask.setAllowedUserIds(getAllowedUserIds(id));
        } catch (RuntimeException e){
            throw e;
        }


        foundTask.setAllowedUserIds(getAllowedUserIds(id));

        return foundTask;
    }


    @Override
    public int deleteTask(Task task) {
        removeAllowedUsersFromTask(task);
        removeTagsFromTask(task);
        String sql = "DELETE FROM tasks WHERE task_id = ?;";
        int deletedRows = jdbcTemplate.update(sql, task.getId());
        if(deletedRows == 0){
            throw new RuntimeException("Task deletion failed.");
        }
        return deletedRows;
    }

    @Transactional
    public List<Tag> getTagsByTaskId(int taskId){
        String sql = "SELECT tag_id, tag_name, user_id FROM tags WHERE tag_id IN (SELECT tag_id FROM task_tags WHERE task_id = ?);";
        List<Tag> tags = jdbcTemplate.query(sql, (rs, rowNum) -> new Tag(rs.getInt("tag_id"),rs.getInt("user_id"), rs.getString("tag_name") ), taskId);
        return tags;
    }

}
