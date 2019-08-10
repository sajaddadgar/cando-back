package com.rahnema.domain;

import com.rahnema.model.User;

public class UserInfoDomain {
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
}
