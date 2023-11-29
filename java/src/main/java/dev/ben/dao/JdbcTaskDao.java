package dev.ben.dao;
import dev.ben.model.Tag;
import dev.ben.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static dev.ben.dao.ResultSetMappers.mapRowToTask;

import java.util.List;
@Component
public class JdbcTaskDao implements TaskDao {

    private JdbcTemplate jdbcTemplate;

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

            String addUserTaskQuery = "INSERT INTO user_tasks (user_id, task_id) VALUES (?, ?);";

            Integer createdTaskId = jdbcTemplate.queryForObject(createTaskBaseQuery, Integer.class, newTask.getOwningUserId(), newTask.getDescription(), newTask.getEstimatedDuration(), newTask.getActualDuration(),newTask.getStatusId());

            int usersAdded = 0;

//            for (int userId : newTask.getAllowedUserIds()){
//                usersAdded += jdbcTemplate.update(addUserTaskQuery, userId, createdTaskId);
//            }

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
    private List<Integer> getAllowedUserIds(int taskId){
        String sql = "SELECT user_id FROM user_tasks WHERE task_id = ?";
        List<Integer> allowedUserIds = jdbcTemplate.queryForList(sql, Integer.class, taskId);
        return allowedUserIds;
    }

    public void addAllowedUsers(Task task) {
        String addUserTaskQuery = "INSERT INTO user_tasks (user_id, task_id) VALUES (?, ?)";
        for(int userId : task.getAllowedUserIds()){
            int userTableEntry = jdbcTemplate.update(addUserTaskQuery, userId, task.getId(), int.class);
        }
    }
    @Transactional
    public int createTag(Tag tag){
        String tagInsertSql = "INSERT INTO tags (tag_name, user_id) VALUES (?, ?)";
        int createdTag = jdbcTemplate.update(tagInsertSql, tag.getDescription(), tag.getUserId());
        if(createdTag == 0){
            throw new RuntimeException("Tag creation failed.");
        }
        String userTagInsertSql = "INSERT INTO user_tags (user_id, tag_id) VALUES (?, ?)";
        int userTagRows = jdbcTemplate.update(userTagInsertSql, tag.getUserId(), tag.getId());
        if(userTagRows == 0){
            throw new RuntimeException("User tag assignment failed.");
        }
        return createdTag;
    }



    public void addTags (Task task) {
        String addTagQuery = "INSERT INTO task_tags (task_id, tag_id) VALUES (?, ?)";
        for (Tag tag : task.getTags()){
            int tagTableEntry = jdbcTemplate.update(addTagQuery, task.getId(), tag.getId(), int.class);
        }
    }




    @Override
    public List<Task> listUserTasks(String username) {
        return null;
    }

    @Override
    public List<Task> listUserTasksByStatus(int status) {
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
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

//    private Task mapRowToTask(SqlRowSet rs){
//        Task newTask = null;
//
//    }


}
