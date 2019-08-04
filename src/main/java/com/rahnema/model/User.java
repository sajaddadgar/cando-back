package com.rahnema.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "userSeq")
    private long user_id;
    private String name;
    private String email;
    private String password;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "creator_id")
    private List<Auction> createdAuction;





    public User(String name, String email, String password, List<Auction> createdAuction) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAuction = createdAuction;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public List<Auction> getAuctions() {
        return createdAuction;
    }

    public void setAuctions(List<Auction> createdAuction) {
        this.createdAuction = createdAuction;
    }
}
