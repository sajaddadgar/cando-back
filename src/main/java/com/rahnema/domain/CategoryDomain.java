package com.rahnema.domain;

import com.rahnema.model.Category;

import java.util.UUID;

public class CategoryDomain {
    transient Category category;
    UUID id;
    String title;

    public CategoryDomain(Category category) {
        this.category = category;
        this.id = category.getId();
        this.title = category.getTitle();
    }
}
