package dev.ben.controller;

import dev.ben.dao.JdbcTagDao;
import dev.ben.dao.JdbcTaskDao;
import dev.ben.dao.TagDao;
import dev.ben.dao.TaskDao;
import dev.ben.model.Task;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {

    private TaskDao taskDao;
    private TagDao tagDao;

    public TaskController(JdbcTaskDao taskDao, JdbcTagDao tagDao){
        this.taskDao = taskDao;
        this.tagDao = tagDao;
    }


    @PostMapping()
    public Task createTask(@RequestBody Task newTask){

        return null;
    }


}
