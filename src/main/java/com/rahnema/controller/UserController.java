package com.rahnema.controller;

import com.rahnema.model.User;
import com.rahnema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "a user added";
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/getOne/{id}")
    public Optional<User> getOneUser(@PathVariable long id) {
        return userService.getOneUser(id);
    }
}
