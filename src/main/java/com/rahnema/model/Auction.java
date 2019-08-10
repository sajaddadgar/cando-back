package com.rahnema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rahnema.domain.AuctionDomain;
import com.rahnema.domain.AuctionInfoDomain;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;
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
    private User creator;
    private int activeUserCount;
    private boolean started;
    private boolean bookmarked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id")
    private User winner;
    @ManyToMany(mappedBy = "bookmarkAuction")
    // Todo: fix json ignore

    @JsonIgnore
    private Set<User> bookmarkUser;

    public Auction(AuctionDomain auctionDomain) {
        this.title = auctionDomain.getTitle();
        this.basePrice = auctionDomain.getBasePrice();
        this.description = auctionDomain.getDescription();
        this.activeUserCount = 0;
        this.createDate = new Date().getTime();
        this.imageUrl = auctionDomain.getImageUrl();
        this.soldPrice = -1;
        this.bookmarkedCount = 0;
        Optional<Category> first = Category.DIGITAL_GOODS.getCategories().stream()
                .filter(category1 -> category1.getId() == auctionDomain.getCategoryId()).findFirst();
        this.category = first.orElse(Category.ALL);
        this.started = false;
        this.dueDate = auctionDomain.getDueDate();
        this.maxUsers = auctionDomain.getMaxUsers();
        this.winner = null;
    }

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

    public void setBookmarkUser(Set<User> bookmarkUser) {
        this.bookmarkUser = bookmarkUser;
    }

    public Auction(AuctionInfoDomain AuctionInfoDomain) {
        this.title = AuctionInfoDomain.getTitle();
        this.description = AuctionInfoDomain.getDescription();
        this.imageUrl = AuctionInfoDomain.getImageUrl();
        Optional<Category> first = Category.DIGITAL_GOODS.getCategories().stream()
                .filter(category1 -> category1.getId() == AuctionInfoDomain.getCategoryId()).findFirst();
        this.category = first.orElse(Category.ALL);
        this.dueDate = AuctionInfoDomain.getDueDate();
        this.maxUsers = AuctionInfoDomain.getMaxUsers();
    }

    public Auction() {
    }

    public Set<User> getBookmarkUser() {
        return bookmarkUser;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(long basePrice) {
        this.basePrice = basePrice;
    }

    public long getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(long soldPrice) {
        this.soldPrice = soldPrice;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getBookmarkedCount() {
        return bookmarkedCount;
    }

    public void setBookmarkedCount(int bookmarkedCount) {
        this.bookmarkedCount = bookmarkedCount;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getActiveUserCount() {
        return activeUserCount;
    }

    public void setActiveUserCount(int activeUserCount) {
        this.activeUserCount = activeUserCount;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}
