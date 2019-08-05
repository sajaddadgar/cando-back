package com.rahnema.domain;

enum Sort {
    TIME("TIME"),
    MOST_BOOKMARKED("MOST_BOOKMARKED");

    private String name;

    Sort(String name) {
        this.name = name;
    }
}

public class HomepageDomain {
    int page;
    int count;
    Sort sort;
    int categoryId;
    String search;

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public Sort getSort() {
        return sort;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getSearch() {
        return search;
    }
}
