package dev.ben.dao;
import dev.ben.model.Task;
import dev.ben.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
@Component
public class JdbcTaskDao implements TaskDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public JdbcTaskDao(JdbcTemplate jdbcTemplate){
//        this.jdbcTemplate = jdbcTemplate;
//    }

    public Task createTask(Task newTask){
        String sql = "INSERT INTO tasks (task_description, task_estimated_duration, task_actual_duration, task_status_id)" +
                "VALUES(?, ?, ?, ?)";
        Task createdTask = jdbcTemplate.queryForObject(sql, Task.class, newTask.getDescription(), newTask.getEstimatedDurationMilliseconds(), newTask.getActualDurationMilliseconds(), newTask.getStatusId());

        return null;
    }
    private void addUserToTask(User user, Task task){
        String sql = "INSERT INTO user_tasks (user_id, task_id) VALUES (?, ?)";

    }


    public List<Task> findByStatus(int status) {
        return null;
    }

    @Override
    public List<Task> listUserTasks(String username) {
        return null;
    }

    @Override
    public List<Task> findTaskByStatus(int status) {
        return null;
    }

    @Override
    public Task findTaskById(int id) {
        return null;
    }

    @Override
    public Task createNewTask(Task task) {
        return null;
    }

    @Override
    public Task updateTask(Task task) {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }
}
