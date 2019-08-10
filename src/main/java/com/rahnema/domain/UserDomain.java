package com.rahnema.domain;

import com.rahnema.model.User;

public class UserDomain implements IDomain<User> {

    transient User user;
    String name;
    String email;
    String password;
    String imageUrl;
    String recoveryLink;

    public UserDomain(User user) {
        this.user = user;
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.imageUrl = user.getImageUrl();
        this.recoveryLink = user.getRecoveryLink();
    }

    @Override
    public boolean isValid() {
        return email.contains("@") &&
                !password.isEmpty();
    }

    @Override
    public User generate() {
        return new User()
                .setName(this.name)
                .setEmail(this.email)
                .setRecoveryLink(this.recoveryLink)
                .setImageUrl(this.imageUrl)
                .setPassword(this.password);
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

}
