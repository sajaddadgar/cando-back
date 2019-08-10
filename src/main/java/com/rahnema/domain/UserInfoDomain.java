package com.rahnema.domain;

import com.rahnema.model.User;

public class UserInfoDomain implements IDomain<User> {
    transient User user;
    String imageUrl;
    String name;
    String email;

    public UserInfoDomain(User user) {
        this.user = user;
        this.imageUrl = user.getImageUrl();
        this.name = user.getName();
        this.email = user.getEmail();
    }

    @Override
    public boolean isValid() {
        return !name.isEmpty() &&
                email.contains("@");
    }

    @Override
    public User generate() {
        return new User()
                .setName(this.name)
                .setEmail(this.email)
                .setImageUrl(this.imageUrl);
    }
}
