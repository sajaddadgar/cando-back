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
    // Todo: fix json ignore
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

    public User setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public User setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getRecoveryLink() {
        return recoveryLink;
    }

    public User setRecoveryLink(String recoveryLink) {
        this.recoveryLink = recoveryLink;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    public Set<Auction> getBookmarkAuction() {
        return bookmarkAuction;
    }

    public User setBookmarkAuction(Set<Auction> bookmarkAuction) {
        this.bookmarkAuction = bookmarkAuction;
        return this;
    }
}
