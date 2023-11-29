package dev.ben.dao;

import dev.ben.model.Task;
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
}
