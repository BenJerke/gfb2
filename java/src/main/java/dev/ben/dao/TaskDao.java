package dev.ben.dao;

import dev.ben.model.Task;

import java.util.List;

public interface TaskDao {

    List<Task> listUserTasks(String username);
    List<Task> findTaskByStatus(int status);
    Task findTaskById(int id);
    Task createNewTask(Task task);
    Task updateTask(Task task);
    Task deleteTask(Task task);
}
