package dev.ben.controller;

import dev.ben.dao.TaskDao;
import dev.ben.dao.UserDao;
import dev.ben.model.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public Tag createTag(@RequestBody Tag tag, Principal principal){
        int userId = userDao.findIdByUsername(principal.getName());
        tag.setUserId(userId);
        try{
            return taskDao.createTag(tag);
        } catch (RuntimeException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Tag creation failed.");
        }
    }
}
