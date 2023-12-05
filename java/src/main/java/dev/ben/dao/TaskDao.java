package dev.ben.dao;
import dev.ben.model.Tag;
import dev.ben.model.Task;
import dev.ben.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TaskDao {
    List<Task> listUserTasks(User user);

    List<Task> listUserTasksByStatus(int status, User user);

    Task findTaskById(int id);
    Task createTask(Task task);
    Task updateTask(Task task);
    int deleteTask(Task task);

    Tag createTag(Tag tag);

    void addTagsToTask(Task createdTask);

    List<Tag> getTagsByTaskId(int id);

    List<Integer> getAllowedUserIds(int id);
}
