package com.rahnema.domain;

import com.rahnema.model.User;

public class UserUpdateDomain implements IDomain<User> {

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

    @Override
    public boolean isValid() {
        return !name.isEmpty() &&
                email.contains("@") &&
                !password.isEmpty();
    }

    @Override
    public User generate() {
        return new User()
                .setEmail(this.email != null ? this.email : "")
                .setImageUrl(this.imageUrl != null ? this.imageUrl : "")
                .setName(this.name != null ? this.name : "")
                .setPassword(this.password != null ? this.password : "")
                .setToken(this.token != null ? this.token : "")
                .setRecoveryLink(this.recoveryLink != null ? this.recoveryLink : "");
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
