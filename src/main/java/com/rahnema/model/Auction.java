package com.rahnema.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auction {

    @Id
    @SequenceGenerator(name = "auctionSeq", sequenceName = "AUCTION_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auctionSeq")
    private long auction_id;
    private String title;
    private String description;
    private long base_price;
    private Date date;
    private int visitor_limit;
//    private User winner;
//
//    public User getWinner() {
//        return winner;
//    }
//
//    public void setWinner(User winner) {
//        this.winner = winner;
//    }

    private long highest_price;


    public long getAuction_id() {
        return auction_id;
    }

    public void setAuction_id(long auction_id) {
        this.auction_id = auction_id;
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

    public long getBase_price() {
        return base_price;
    }

    public void setBase_price(long base_price) {
        this.base_price = base_price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getVisitor_limit() {
        return visitor_limit;
    }

    public void setVisitor_limit(int visitor_limit) {
        this.visitor_limit = visitor_limit;
    }

    public long getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(long highest_price) {
        this.highest_price = highest_price;
    }
}
