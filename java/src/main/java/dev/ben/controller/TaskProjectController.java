package dev.ben.controller;

import dev.ben.model.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskProjectController {
    // create tasks and projects
    // update tasks and projects
    // delete tasks and projects
    @PostMapping("/new")
    public Task createTask(@RequestBody Task newTask){
        return null;
    }

}
