package com.rahnema.service;

import com.rahnema.domain.UserDomain;
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

    public User addUser(UserDomain userDomain) {
        User user = new User(userDomain);
        userRepository.save(user);
        return user;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    public Optional<User> getOneUser(long id){
        return userRepository.findById(id);
    }

    public void update(User user, long id){
        Optional<User> dbUser = userRepository.findById(id);
        if (dbUser.isPresent()){
            User preUser = dbUser.get();
            preUser.setName(!user.getName().isEmpty() ? user.getName() : preUser.getName());
            preUser.setEmail(!user.getEmail().isEmpty() ? user.getEmail() : preUser.getEmail());
            preUser.setPassword(!user.getPassword().isEmpty() ? user.getPassword() : preUser.getPassword());
            preUser.setImageUrl(!user.getImageUrl().isEmpty() ? user.getImageUrl() : preUser.getImageUrl());
        }

        userRepository.save(dbUser.get());

    }


    public void remove(long id){
        userRepository.deleteById(id);
    }

    public List<Auction> getMyAuctions(long id){
        Optional<User> user = userRepository.findById(id);
        return user.get().getCreatedAuction();
    }

}
