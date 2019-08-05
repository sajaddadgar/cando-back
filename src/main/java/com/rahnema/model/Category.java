package com.rahnema.model;


import com.rahnema.domain.CategoryDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Category {
    DIGITAL_GOODS("کالای دیجیتال"),
    SPORT("ورزشی"),
    ALL("تمام کالاها");


    private int id;
    private String title;

    Category(final String title) {
        this.id = title.hashCode();
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public List<CategoryDomain> getCategoryDomains() {
        List<CategoryDomain> categoryDomains = new ArrayList<>();
        Arrays.asList(Category.values()).forEach(category -> categoryDomains.add(new CategoryDomain(category)));
        return categoryDomains;
    }

    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }
}

