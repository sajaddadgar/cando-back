package com.rahnema.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @SequenceGenerator(name = "categorySeq", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "categorySeq")
    private long category_id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private List<Auction> auctions;

    public List<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
