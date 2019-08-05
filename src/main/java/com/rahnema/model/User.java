package com.rahnema.model;


import com.rahnema.domain.UserDomain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
    private long id;
    private String name;
    private String email;
    private String password;
    private String imageUrl;
    private String recoveryLink;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private List<Auction> createdAuction;

    public User(UserDomain userDomain) {
        this.name = userDomain.getName();
        this.email = userDomain.getEmail();
        this.password = userDomain.getPassword();
        this.imageUrl = userDomain.getImageUrl();
        this.recoveryLink = "";
        this.createdAuction = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Auction> getCreatedAuction() {
        return createdAuction;
    }

    public void setCreatedAuction(List<Auction> createdAuction) {
        this.createdAuction = createdAuction;
    }
}
