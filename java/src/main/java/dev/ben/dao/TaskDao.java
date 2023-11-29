package dev.ben.dao;
import dev.ben.model.Tag;
import dev.ben.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TaskDao {
    List<Task> listUserTasks(String username);
    List<Task> listUserTasksByStatus(int status);
    Task findTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Task task);
    Task deleteTask(Task task);

    int createTag(Tag tag);
}
