package com.ua.alevel.shop.service.impl;

import com.ua.alevel.shop.model.Category;
import com.ua.alevel.shop.repository.CategoryRepository;
import com.ua.alevel.shop.service.CategoryService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class CategoryServiceImplTest {


    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void testAddCategory() {
        Category category = new Category();
        categoryService.addCategory(category);
        verify(categoryRepository).save(category);
    }

    @Test
    public void testListCategory() {
        Category category = new Category();
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(category));
        List<Category> categories = categoryService.listCategory();
        assertEquals(category, categories.get(0));
    }

    @Test
    public void testDeleteCategory() {
        categoryService.deleteCategory(0L);
        verify(categoryRepository).deleteById(0L);
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        categoryService.updateCategory(category);
        verify(categoryRepository).save(category);
    }

    @Test
    public void testGetCategory() {
        Category category = new Category();
        category.setCategoryId(0L);
        when(categoryRepository.findById(category.getCategoryId())).thenReturn(Optional.of(category));
        Optional<Category> categoryOptional = categoryService.getCategory(0L);
        assertEquals(Optional.of(category), categoryOptional);
    }

}