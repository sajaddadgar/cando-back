package com.rahnema.domain;

import com.rahnema.model.User;

public class UserSignUpDomain {

    transient User user;
    String password;
    String email;
    String name;
    String imageUrl;

    public UserSignUpDomain(User user) {
        this.user = user;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.name = user.getName();
        this.imageUrl = user.getImageUrl() != null ? user.getImageUrl() : "default.png";
    }

    public static User generateUser(UserSignUpDomain signUpDomain) {
        User user = new User();
        user.setPassword(signUpDomain.password);
        user.setEmail(signUpDomain.email);
        user.setName(signUpDomain.name);
        user.setImageUrl(signUpDomain.imageUrl);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
