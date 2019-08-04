package com.rahnema.controller;

import com.rahnema.model.Auction;
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

    @GetMapping("/{id}")
    public Optional<User> getOneUser(@PathVariable long id) {
        return userService.getOneUser(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "a user added";
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody User user, @PathVariable long id){
        userService.update(user, id);
        return "a user changed";
    }

    @DeleteMapping("/remove/{id}")
    public String remove(@PathVariable long id){
        userService.remove(id);
        return "a user removed";
    }

    @GetMapping("/myAuction/{id}")
    public List<Auction> getMyAuctions(@PathVariable long id){
        return userService.getMyAuctions(id);
    }


}
