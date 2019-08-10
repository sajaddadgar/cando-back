package com.rahnema.domain;

import com.rahnema.model.Auction;
import com.rahnema.model.Category;

import java.util.Date;
import java.util.Optional;

public class AuctionInfoDomain implements IDomain<Auction> {

    transient Auction auction;
    String title;
    String description;
    long categoryId;
    String categoryTitle;
    String imageUrl;
    long dueDate;
    long remainedTime;
    int maxUsers;

    public AuctionInfoDomain(Auction auction) {
        this.auction = auction;
        this.title = auction.getTitle();
        this.description = auction.getDescription();
        this.categoryTitle = auction.getCategory().getTitle();
        this.categoryId = auction.getCategory().getId();
        this.dueDate = auction.getDueDate();
        this.maxUsers = auction.getMaxUsers();
        this.imageUrl = auction.getImageUrl();
        this.remainedTime = auction.getDueDate() - new Date().getTime();
    }

    @Override
    public boolean isValid() {
        return !title.isEmpty() &&
                maxUsers > 0;
    }

    @Override
    public Auction generate() {
        Optional<Category> optionalCategory = Category.ALL.getCategories().stream().filter(category -> category.getId() == this.categoryId).findFirst();
        Category category = optionalCategory.orElse(Category.ALL);
        return new Auction()
                .setTitle(this.title)
                .setDescription(this.description)
                .setCategory(category)
                .setImageUrl(this.imageUrl)
                .setDueDate(this.dueDate)
                .setMaxUsers(this.maxUsers);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
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

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
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

}
