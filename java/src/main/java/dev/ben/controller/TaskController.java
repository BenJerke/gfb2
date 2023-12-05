package dev.ben.controller;

import dev.ben.dao.JdbcTaskDao;
import dev.ben.dao.TaskDao;
import dev.ben.dao.UserDao;
import dev.ben.model.Tag;
import dev.ben.model.Task;
import dev.ben.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/task")
public class TaskController {

    private TaskDao taskDao;
    private UserDao userDao;
    private TagController tagController;


    public TaskController(TaskDao taskDao, UserDao userDao, TagController tagController){
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.tagController = tagController;
    }

    @PutMapping(value = "/list")
    public List<Task> updateMultipleTasks(@RequestBody Task[] tasks, Principal principal){
        User currentUser = userDao.findByUsername(principal.getName());
        List<Task> updatedTasks = null;
        for(Task task : tasks){
            if(task.getOwningUserId() != currentUser.getId() && !task.getAllowedUserIds().contains(currentUser.getId())){
                throw new AccessDeniedException("User does not own task.");
            }
        }
        try{
            for(Task task : tasks){
                taskDao.updateTask(task);
                updatedTasks.add(task);
            }
            if(updatedTasks == null || updatedTasks.size() != tasks.length){
                throw new RuntimeException("Unable to update tasks.");
            }
            return updatedTasks;
        } catch (RuntimeException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to update tasks.");
        }
    }

    @PutMapping(value = "/{id}")
    public Task updateTask(@RequestBody Task task, @PathVariable int taskId, Principal principal){
        if (task.getId() != taskId || (!principalIsTaskOwner(task, principal) && !principalIsTaskAllowedUser(task, principal))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to update task.");
        }


        try{
            for(Tag tag :  task.getTags()){
                if(tag.getId() == 0){
                    tagController.createTag(tag, principal);
                }
            }
            return taskDao.updateTask(task);
        } catch (RuntimeException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to update task.");
        }
    }


    @PostMapping(value = "")
    public Task createTask(@RequestBody Task newTask){
        Task createdTask = null;
        try {
            createdTask = taskDao.createTask(newTask);
        }
        catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task creation failed.");
        }
        if(createdTask == null) {
            throw new RuntimeException("Task creation failed.");
        }
        return createdTask;
    }

    @PostMapping(value = "/list")
    public List<Task> createMultipleTasks(@RequestBody Task[] tasks, Principal principal){
        User currentUser = userDao.findByUsername(principal.getName());
        List<Task> createdTasks = null;
        for(Task task : tasks){
            if(task.getOwningUserId() != currentUser.getId()){
                throw new AccessDeniedException("User does not own task.");
            }
        }
        try{
            for(Task task : tasks){
                taskDao.createTask(task);
                createdTasks.add(task);
            }
            if(createdTasks == null || createdTasks.size() != tasks.length){
                throw new RuntimeException("Unable to create tasks.");
            }
            return createdTasks;
        } catch (RuntimeException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to create tasks.");
        }
    }

    @GetMapping(path = "/{id}")
    public Task findTaskById(@PathVariable int id, Principal principal){
        Task foundTask = null;
        try{
            foundTask = taskDao.findTaskById(id);
        }
        catch (RuntimeException e){
            System.out.println("Task GET Failed: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found.");
        }
        if(!principalIsTaskOwner(foundTask, principal) && !principalIsTaskAllowedUser(foundTask, principal)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found.");
        }
        return foundTask;
    }

    @GetMapping(value = "")
    public List<Task> getUserTasks(Principal principal){
        List<Task> foundTasks = null;
        User loggedInUser = userDao.findByUsername(principal.getName());
        foundTasks = taskDao.listUserTasks(loggedInUser);
        if(foundTasks == null){
            throw new RuntimeException("Tasks not found.");
        }
        return foundTasks;
    }

    private boolean principalIsTaskOwner(Task task, Principal principal){
        User currentUser = userDao.findByUsername(principal.getName());
        if(currentUser.getId() == task.getOwningUserId()){
            return true;
        }
        return false;
    }
    private boolean principalIsTaskAllowedUser(Task task, Principal principal){
        User currentUser = userDao.findByUsername(principal.getName());
        if(task.getAllowedUserIds().contains(currentUser.getId())){
            return true;
        }
        return false;
    }


}
