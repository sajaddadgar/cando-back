package com.rahnema.service;

import com.rahnema.domain.UserSignUpDomain;
import com.rahnema.exception.EmailAlreadyExistException;
import com.rahnema.model.User;
import com.rahnema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Value("${email.default.url}")
    private String url;

    private JavaMailSender javaMailSender;

    @Autowired
    public UserService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    private boolean emailValidation(String email) {
        List<User> userList = (List<User>) userRepository.findAll();
        for (User user : userList) {
            if (email.equals(user.getEmail())) {
                return false;
            }
        }
        return true;
    }

    public User addUser(UserSignUpDomain userInfoDomain) throws EmailAlreadyExistException {
        User user = userInfoDomain.generate();
        if (emailValidation(user.getEmail())) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new EmailAlreadyExistException("email already exist");
        }
        return user;
    }

    public Optional<User> getOneUser(long id){
        return userRepository.findById(id);
    }

    public void update(User user, long id){
        Optional<User> dbUser = userRepository.findById(id);
        if (dbUser.isPresent()){
            User preUser = dbUser.get();
            preUser.setName(user.getName() != null && !user.getName().isEmpty() ? user.getName() : preUser.getName());
            preUser.setEmail(user.getEmail() != null && !user.getEmail().isEmpty() ? user.getEmail() : preUser.getEmail());
            preUser.setPassword(user.getPassword() != null && !user.getPassword().isEmpty() ? user.getPassword() : preUser.getPassword());
            preUser.setImageUrl(user.getImageUrl() != null && !user.getImageUrl().isEmpty() ? user.getImageUrl() : preUser.getImageUrl());
        }

        userRepository.save(dbUser.get());

    }

    public void remove(long id){
        userRepository.deleteById(id);
    }

    public void findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        user.setToken(UUID.randomUUID().toString());
        String a = url + user.getId() + "/" + user.getToken();
        user.setRecoveryLink(a);
        userRepository.save(user);
        try {
            messageHelper.setSubject("کندو - بازیابی رمز عبور");
            messageHelper.setTo(user.getEmail());
            messageHelper.setText("برای تغییر رمز عبور، برو تو لینک زیر، و رمز عبورتو عوض کن؛" +
                    "<br>" + a, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    public void change(long id, String password) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> value.setPassword(encoder.encode(password)));
        user.ifPresent(user1 -> userRepository.save(user1));
    }
}
