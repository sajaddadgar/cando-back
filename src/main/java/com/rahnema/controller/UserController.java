package com.rahnema.controller;

import com.rahnema.domain.UserDomain;
import com.rahnema.domain.UserSignUpDomain;
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
    public UserSignUpDomain addUser(@RequestBody UserSignUpDomain userSignUpDomain) {
        User user = userService.addUser(userSignUpDomain);
        return new UserSignUpDomain(user);
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

    @RequestMapping("/redirect/{id}/{token}")
    public ModelAndView dis(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("recover.html");
        modelAndView.addObject("id", userService.getOneUser(id).get().getId());
        return modelAndView;
    }


    @PostMapping("/changepassword")
    public ModelAndView changePass
            (@RequestParam("id") String id, @RequestParam("pass") String password, @RequestParam("pass2") String password2) {
        if (password.equals(password2)) {
            userService.change(Long.parseLong(id), password);
            Optional<User> user = userService.getOneUser(Long.parseLong(id));
            if (user.isPresent()) {
                user.get().setRecoveryLink("");
                userService.addUser(user.get());
            }
        } else {
            throw new WrongArgumantException("Not same");
        }

        return new ModelAndView("complete.html");
    }
}



