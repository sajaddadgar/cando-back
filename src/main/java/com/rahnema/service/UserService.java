package com.rahnema.service;

import com.rahnema.domain.UserDomain;
import com.rahnema.model.Auction;
import com.rahnema.model.User;
import com.rahnema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private JavaMailSender javaMailSender;

    @Autowired
    public UserService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

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

    //TODO: change this method to auctionService
    public List<Auction> getMyAuctions(long id){
        Optional<User> user = userRepository.findById(id);
        return user.get().getCreatedAuction();
    }


    //ToDo: generate a random url
    public void recoverPassword(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message);
            try {
                messageHelper.setSubject("کندو - بازیابی رمز عبور");
                messageHelper.setTo(user.get().getEmail());
                messageHelper.setText("برای تغییر رمز عبور، برو تو لینک زیر، و رمز عبورتو عوض کن؛" +
                        "<br/><a href='http://localhost:8080/user/1'>ok</a>!\n", true);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            javaMailSender.send(message);
        }

    }

}
