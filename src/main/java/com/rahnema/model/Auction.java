package com.rahnema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Auction {

    @Id
    @SequenceGenerator(name = "auctionSeq", sequenceName = "AUCTION_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auctionSeq")
    private long id;
    private String title;
    private String description;
    private long basePrice;
    private long soldPrice;
    private long createDate;
    private long dueDate;
    private String imageUrl;
    private int bookmarkedCount;
    private int maxUsers;
    private Category category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "creator_id")
    private transient User creator;
    private int activeUserCount;
    private boolean started;
    private boolean boookmarked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id")
    private User winner;
    @ManyToMany(mappedBy = "bookmarkAuction")
    // Todo: fix json ignore

    @JsonIgnore
    private transient Set<User> bookmarkUser;

    public void addBookmarkUser(User user) {
        if (!bookmarkUser.contains(user)) {
            bookmarkUser.add(user);
            bookmarkedCount++;
        }
    }

    public void removeBookmarkUser(User user) {
        if (bookmarkUser.contains(user)) {
            bookmarkUser.remove(user);
            bookmarkedCount--;
        }
    }

    public Auction setBookmarkUser(Set<User> bookmarkUser) {
        this.bookmarkUser = bookmarkUser;
        return this;
    }

    public Auction() {
    }

    public Set<User> getBookmarkUser() {
        return bookmarkUser;
    }

    public boolean isStarted() {
        return started;
    }

    public Auction setStarted(boolean started) {
        this.started = started;
        return this;
    }

    public long getId() {
        return id;
    }

    public Auction setId(long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Auction setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Auction setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public Auction setBasePrice(long basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public long getSoldPrice() {
        return soldPrice;
    }

    public Auction setSoldPrice(long soldPrice) {
        this.soldPrice = soldPrice;
        return this;
    }

    public long getCreateDate() {
        return createDate;
    }

    public Auction setCreateDate(long createDate) {
        this.createDate = createDate;
        return this;
    }

    public long getDueDate() {
        return dueDate;
    }

    public Auction setDueDate(long dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Auction setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getBookmarkedCount() {
        return bookmarkedCount;
    }

    public Auction setBookmarkedCount(int bookmarkedCount) {
        this.bookmarkedCount = bookmarkedCount;
        return this;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public Auction setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Auction setCategory(Category category) {
        this.category = category;
        return this;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public Auction setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
        return this;
    }

    public User getWinner() {
        return winner;
    }

    public Auction setWinner(User winner) {
        this.winner = winner;
        return this;
    }

    public User getCreator() {
        return creator;
    }

    public Auction setCreator(User creator) {
        this.creator = creator;
        return this;
    }

    public boolean isBoookmarked() {
        return boookmarked;
    }

    public Auction setBoookmarked(boolean boookmarked) {
        this.boookmarked = boookmarked;
        return this;
    }
}
