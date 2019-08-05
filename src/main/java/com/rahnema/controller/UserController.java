package com.rahnema.controller;

import com.rahnema.domain.UserDomain;
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

    private boolean isValid(UserDomain userDomain) {
        return userDomain.getEmail().contains("@") && !userDomain.getPassword().isEmpty();
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDomain addUser(@RequestBody UserDomain userDomain) {
        if (isValid(userDomain)) {
            User user = userService.addUser(userDomain);
            return new UserDomain(user);
        } else throw new IllegalArgumentException("Arguments are not valid!");
    }

    @PutMapping("/update/{id}")
    public String update(@RequestBody UserDomain userDomain, @PathVariable long id) {
        User user = new User(userDomain);
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


    @PostMapping("/recover")
    public boolean recoverPassword() {
        userService.recoverPassword();
        return true;
    }
}
