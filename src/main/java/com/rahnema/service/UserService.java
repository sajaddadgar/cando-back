package com.rahnema.service;

import com.rahnema.model.Auction;
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

    public void update(User user, long id){
        Optional<User> user1 = userRepository.findById(id);
        if (user1.isPresent()){
            user1.get().setName(user.getName());
            user1.get().setEmail(user.getEmail());
            user1.get().setPassword(user.getPassword());
            user1.get().setImageUrl(user.getImageUrl());
            user1.get().setCreatedAuction(user.getCreatedAuction());
            user1.get().setRecoveryLink(user.getRecoveryLink());
        }
    }


    public void remove(long id){
        userRepository.deleteById(id);
    }

    public List<Auction> getMyAuctions(long id){
        Optional<User> user = userRepository.findById(id);
        return user.get().getCreatedAuction();
    }

}
