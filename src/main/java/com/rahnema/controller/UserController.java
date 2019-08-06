package com.rahnema.controller;

import com.rahnema.domain.UserDomain;
import com.rahnema.exception.WrongArgumantException;
import com.rahnema.model.User;
import com.rahnema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @PostMapping("/recover/{email}")
    public boolean recoverPassword(@PathVariable String email) {
        userService.findByEmail(email);
        return true;
    }

    @RequestMapping("/redirect/{id}")
    public ModelAndView dis(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("recover.html");
        modelAndView.addObject("name", userService.getOneUser(id).get().getName());
        modelAndView.addObject("id", userService.getOneUser(id).get().getId());
        return modelAndView;
    }


    @PostMapping("/changepassword")
    public ModelAndView changePass
            (@RequestParam("id") String id, @RequestParam("pass") String password, @RequestParam("pass2") String password2) {
        if (password.equals(password2)) {
            userService.change(Long.parseLong(id), password);
        } else {
            throw new WrongArgumantException("پسورد ها با هم یکی نیست");
        }

        return new ModelAndView("complete.html");
    }


}



