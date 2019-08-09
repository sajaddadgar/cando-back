package com.rahnema.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BOOKMARK",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "auction_id")})
    @JsonIgnore
    private Set<Auction> bookmarkAuction;

    public User() {
    }

    public void addBookmarkedAuction(Auction auction) {
        bookmarkAuction.add(auction);
    }

    public void removeBookmarkedAuction(Auction auction) {
        bookmarkAuction.remove(auction);
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Auction> getBookmarkAuction() {
        return bookmarkAuction;
    }

    public void setBookmarkAuction(Set<Auction> bookmarkAuction) {
        this.bookmarkAuction = bookmarkAuction;
    }
}
