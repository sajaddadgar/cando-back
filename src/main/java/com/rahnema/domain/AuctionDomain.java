package com.rahnema.domain;

import com.rahnema.model.Auction;
import com.rahnema.model.Category;

import java.util.Date;
import java.util.Optional;

public class AuctionDomain implements IDomain<Auction> {
    transient Auction auction;
    Long id;
    String title;
    String description;
    long basePrice;
    int categoryId;
    String categoryTitle;
    long dueDate;
    int maxUsers;
    int remainedUsers;
    long remainedTime;
    String imageUrl;
    boolean bookmarked;
    boolean enabled;
    boolean started;

    public AuctionDomain(Auction auction) {
        this.auction = auction;
        this.id = auction.getId();
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.basePrice = auction.getBasePrice();
        this.categoryTitle = auction.getCategory().getTitle();
        this.categoryId = auction.getCategory().getId();
        this.dueDate = auction.getDueDate();
        this.maxUsers = auction.getMaxUsers();
        this.imageUrl = auction.getImageUrl();
        this.remainedUsers = this.maxUsers - auction.getActiveUserCount();
        this.remainedTime = auction.getDueDate() - new Date().getTime();
        this.enabled = auction.getMaxUsers() > auction.getActiveUserCount();
        this.bookmarked = auction.isBookmarked();
        this.started = auction.getDueDate() < new Date().getTime();
    }


    @Override
    public boolean isValid() {
        return
                (!title.isEmpty()) &&
                        basePrice >= 0 &&
                        maxUsers >= 0;
    }

    @Override
    public Auction generate() {
        Optional<Category> cat = Category.ALL.getCategories().stream()
                .filter(category -> category.getId() == this.getCategoryId()).findFirst();
        Category category = cat.orElse(Category.ALL);
        return new Auction().setTitle(this.getTitle())
                .setDescription(this.getDescription())
                .setBasePrice(this.getBasePrice())
                .setActiveUserCount(0)
                .setCreateDate(new Date().getTime())
                .setImageUrl(this.getImageUrl())
                .setSoldPrice(-1)
                .setBookmarkedCount(0)
                .setCategory(category)
                .setStarted(false)
                .setDueDate(this.getDueDate())
                .setMaxUsers(this.getMaxUsers());
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public long getDueDate() {
        return dueDate;
    }

    public void setDueDate(long dueDate) {
        this.dueDate = dueDate;
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
