package com.rahnema.domain;

import com.rahnema.model.Category;

public class CategoryDomain {
    transient Category category;
    int id;
    String title;

    public CategoryDomain(Category category) {
        this.category = category;
        this.id = category.getId();
        this.title = category.getTitle();
    }
}
