package com.rahnema.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public enum Category {
    DIGITAL_GOODS("کالای دیجیتال"),
    SPORT("ورزشی");


    private UUID id;
    private String title;

    Category(final String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    public List<String> getCategories() {
        List<String> persian = new ArrayList<>();
        persian.add(Category.SPORT.getTitle());
        persian.add(Category.DIGITAL_GOODS.getTitle());
        return persian;
//        return Arrays.asList(Category.values());
    }
}

