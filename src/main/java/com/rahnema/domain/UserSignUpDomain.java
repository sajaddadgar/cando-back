package com.rahnema.domain;

import com.rahnema.model.User;

public class UserSignUpDomain {

    transient User user;
    String email;
    String password;

    public UserSignUpDomain(User user) {
        this.user = user;
        this.email = user.getEmail();
        this.password = user.getPassword();
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
