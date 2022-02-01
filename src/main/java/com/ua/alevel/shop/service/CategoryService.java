package com.ua.alevel.shop.service;

import com.ua.alevel.shop.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

     void addCategory(Category category);

    List<Category> listCategory();

    void deleteCategory(long categoryId);

    void updateCategory(Category category);

    Optional<Category> getCategory(long categoryId);

}
