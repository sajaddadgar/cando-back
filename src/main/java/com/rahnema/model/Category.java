package com.rahnema.model;

import java.util.Arrays;
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

    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }
}

