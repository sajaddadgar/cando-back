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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    @Value("${file.subject.path}")
    private String subjectText;

    @Value("${file.body.path}")
    private String bodyText;

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
        User user = UserSignUpDomain.generateUser(userInfoDomain);
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

    public String getTextFromFile(String filePath) throws IOException {
        FileReader fileReader = new FileReader(new File(filePath));
        int ascii = fileReader.read();
        String s = " ";
        while (ascii != -1) {
            s += (char) ascii;
            ascii = fileReader.read();
        }
        fileReader.close();
        return s;
    }

    public void findByEmail(String email) throws IOException, MessagingException {
        String subject = getTextFromFile(subjectText);
        String body = getTextFromFile(bodyText);
        User user = userRepository.findByEmail(email);
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        user.setToken(UUID.randomUUID().toString());
        String recoverLink = url + user.getId() + "/" + user.getToken();
        user.setRecoveryLink(recoverLink);
        userRepository.save(user);
        messageHelper.setSubject(subject);
        messageHelper.setTo(user.getEmail());
        messageHelper.setText(body + "<br>" + recoverLink, true);
        javaMailSender.send(message);
    }

    public void change(long id, String password) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(value -> value.setPassword(encoder.encode(password)));
        user.ifPresent(user1 -> userRepository.save(user1));
    }
}
