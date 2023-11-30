package dev.ben.controller;

import dev.ben.dao.TaskDao;
import dev.ben.dao.UserDao;
import dev.ben.model.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/tag")
public class TagController {

    private TaskDao taskDao;
    private UserDao userDao;

    public TagController(TaskDao taskDao, UserDao userDao){
        this.taskDao = taskDao;
        this.userDao = userDao;
    }
    @PostMapping(value = "")
    public Tag createTag(){
        return null;
    }

}
