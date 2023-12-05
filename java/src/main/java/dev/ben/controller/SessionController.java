package dev.ben.controller;

import dev.ben.model.Session;
import dev.ben.model.WorkPeriod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class SessionController {
    // sessions and their associated work periods are handled here


}
