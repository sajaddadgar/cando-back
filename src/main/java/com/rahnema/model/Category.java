package com.rahnema.model;

import java.util.UUID;

enum Category {
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
}
