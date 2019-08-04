package com.rahnema.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Category {

    private UUID id;
    private String name;


    public Category(String name){
        id = UUID.randomUUID();
        this.name = name;
    }

}

public class Categories{
    public static Category DIGITAL = new Category("دیجیتال");
    public static Category SPORT = new Category("ورزشی");

    public List<Category> getCategory(){

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(DIGITAL);
        categoryList.add(SPORT);
        return categoryList;

    }

}
