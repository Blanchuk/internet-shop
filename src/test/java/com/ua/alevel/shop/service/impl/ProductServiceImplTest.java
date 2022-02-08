package com.ua.alevel.shop.service.impl;

import com.ua.alevel.shop.model.Category;
import com.ua.alevel.shop.model.Product;
import com.ua.alevel.shop.repository.CategoryRepository;
import com.ua.alevel.shop.repository.ProductRepository;
import com.ua.alevel.shop.service.CategoryService;
import com.ua.alevel.shop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void testAddProduct() {
        Product product = new Product();
        productService.addProduct(product);
        verify(productRepository).save(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setProductId(0L);
        productService.deleteProduct(0L);
        verify(productRepository).deleteById(0L);
    }

    @Test
    public void testListProduct() {
        Product product = new Product();
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));
        List<Product> products = productService.listProduct();
        assertEquals(product, products.get(0));
    }

    @Test
    public void testFindByCategory() {
        Product product = new Product();
        when(productRepository.findByCategory_CategoryId(0L)).thenReturn(Collections.singletonList(product));
        List<Product> products = productService.findByCategory(0L);
        assertEquals(product, products.get(0));
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setProductId(0L);
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        Optional<Product> productById = productService.getProductById(0L);
        assertEquals(Optional.of(product),productById);
    }
}