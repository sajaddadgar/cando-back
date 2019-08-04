package com.rahnema.service;

import com.rahnema.model.Category;
import com.rahnema.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public void addCategory(Category category){
        categoryRepository.save(category);
    }


    public Optional<Category> getOneCategory(long id){
        return categoryRepository.findById(id);
    }

    public List<Category> getAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }

}
