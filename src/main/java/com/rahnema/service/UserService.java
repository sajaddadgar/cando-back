package com.rahnema.service;

import com.rahnema.model.User;
import com.rahnema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }

    public Optional<User> getOneUser(long id){
        return userRepository.findById(id);
    }

}
