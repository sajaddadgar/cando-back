package com.rahnema.domain;

import com.rahnema.model.User;

public class UserUpdateDomain {

    transient User user;
    String name;
    String email;
    String password;
    String imageUrl;
    String token;
    String recoveryLink;

    public UserUpdateDomain(User user) {
        this.user = user;
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.imageUrl = user.getImageUrl();
        this.token = user.getToken();
        this.recoveryLink = user.getRecoveryLink();
    }

    public static User generateUser(UserUpdateDomain userUpdateDomain) {
        User user = new User();
        user.setEmail(userUpdateDomain.email != null ? userUpdateDomain.email : "");
        user.setImageUrl(userUpdateDomain.imageUrl != null ? userUpdateDomain.imageUrl : "");
        user.setName(userUpdateDomain.name != null ? userUpdateDomain.name : "");
        user.setPassword(userUpdateDomain.password != null ? userUpdateDomain.password : "");
        user.setToken(userUpdateDomain.token != null ? userUpdateDomain.token : "");
        user.setRecoveryLink(userUpdateDomain.recoveryLink != null ? userUpdateDomain.recoveryLink : "");
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecoveryLink() {
        return recoveryLink;
    }

    public void setRecoveryLink(String recoveryLink) {
        this.recoveryLink = recoveryLink;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
