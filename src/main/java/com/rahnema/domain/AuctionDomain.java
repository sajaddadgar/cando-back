package com.rahnema.domain;

import com.rahnema.model.Auction;

import java.util.UUID;

public class AuctionDomain {
    transient Auction auction;
    Long id;
    String title;
    String description;
    long basePrice;
    UUID categoryId;
    String categoryTitle;
    long dueDate;
    int maxUsers;
    String imageUrl;

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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
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
