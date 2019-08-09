package com.rahnema.controller;

import com.rahnema.domain.UserInfoDomain;
import com.rahnema.domain.UserSignUpDomain;
import com.rahnema.domain.UserUpdateDomain;
import com.rahnema.exception.EmailAlreadyExistException;
import com.rahnema.exception.WrongArgumantException;
import com.rahnema.model.User;
import com.rahnema.service.UserDetailsServiceImpl;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/info")
    public UserInfoDomain getOneUser() {
        return new UserInfoDomain(userDetailsService.getUser());
    }

    private boolean isValid(UserSignUpDomain userSignUpDomain) {
        return userSignUpDomain.getEmail().contains("@") && !userSignUpDomain.getPassword().isEmpty();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSignUpDomain addUser(@RequestBody UserSignUpDomain userSignUpDomain) throws EmailAlreadyExistException {
        if (!isValid(userSignUpDomain)) {
            throw new WrongArgumantException("email or password not valid");
        }
        User user = userService.addUser(userSignUpDomain);
        return new UserSignUpDomain(user);
    }

    @PutMapping("/update")
    public String update(@RequestBody UserUpdateDomain userUpdateDomain) {
        User user = UserUpdateDomain.generateUser(userUpdateDomain);
        userService.update(user, userDetailsService.getUser().getId());
        return "a user changed";
    }

    @DeleteMapping("/remove")
    public String remove() {
        userService.remove(userDetailsService.getUser().getId());
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



