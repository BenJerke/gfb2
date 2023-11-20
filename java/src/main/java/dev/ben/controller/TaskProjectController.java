package dev.ben.controller;

import dev.ben.model.Task;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskProjectController {
    // task & project record CRUD
    //
    //
    @PostMapping("/new")
    public Task createTask(@RequestBody Task newTask){
        /*
        get a number of milliseconds

         */

        return null;
    }

}
