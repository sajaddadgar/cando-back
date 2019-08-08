package com.rahnema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rahnema.domain.UserDomain;
import com.rahnema.domain.UserSignUpDomain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private String token;
    private String recoveryLink;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private List<Auction> createdAuction;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "BOOKMARK",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "auction_id")})
    @JsonIgnore
    private Set<Auction> bookmarkAuction;

    public User(UserDomain userDomain) {
        this.name = userDomain.getName() != null ? userDomain.getName() : "";
        this.email = userDomain.getEmail() != null ? userDomain.getEmail() : "";
        this.password = userDomain.getPassword() != null ? userDomain.getPassword() : "";
        this.imageUrl = userDomain.getImageUrl() != null ? userDomain.getImageUrl() : "";
        this.token = "";
        this.recoveryLink = "";
        this.createdAuction = null;
    }

    public User(UserSignUpDomain userInfoDomain) {
        this.email = userInfoDomain.getEmail() != null ? userInfoDomain.getEmail() : "";
        this.password = userInfoDomain.getPassword() != null ? userInfoDomain.getPassword() : "";
    }

    public User() {
    }


    public Set<Auction> getBookmarkAuction() {
        return bookmarkAuction;
    }

    public void setBookmarkAuction(Set<Auction> bookmarkAuction) {
        this.bookmarkAuction = bookmarkAuction;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
