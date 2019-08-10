package com.rahnema.domain;

import com.rahnema.model.User;

public class UserSignUpDomain implements IDomain<User> {

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

    @Override
    public boolean isValid() {
        return !name.isEmpty() &&
                !password.isEmpty() &&
                email.contains("@");
    }

    @Override
    public User generate() {
        return new User()
                .setImageUrl(this.imageUrl)
                .setEmail(this.email)
                .setPassword(this.password)
                .setName(this.name);
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

    public void setUser(User user) {
        this.user = user;
    }


}
