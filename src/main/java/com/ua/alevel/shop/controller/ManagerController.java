package com.ua.alevel.shop.controller;

import com.ua.alevel.shop.model.Category;
import com.ua.alevel.shop.model.Product;
import com.ua.alevel.shop.service.CategoryService;
import com.ua.alevel.shop.service.FileUploadService;
import com.ua.alevel.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("manager")
public class ManagerController {

    private final CategoryService categoryService;

    private final ProductService productService;

    private final FileUploadService fileUploadService;

    public ManagerController(CategoryService categoryService, ProductService productService, FileUploadService fileUploadService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("index")
        public String index() {
            return "manager/index";
        }

        @GetMapping("category-form")
        public ModelAndView listCategory() {
            ModelAndView modelAndView = new ModelAndView("manager/category-form");
            modelAndView.addObject("categoryList", categoryService.listCategory());
            return modelAndView;
        }

        @PostMapping("add-category")
        public ModelAndView addCategory(Category category) {
            ModelAndView modelAndView = new ModelAndView("manager/category-form");
            categoryService.addCategory(category);
            modelAndView.addObject("categoryList", categoryService.listCategory());
            return modelAndView;
        }

        @GetMapping("delete-Category/{categoryId}")
        public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
            ModelAndView modelAndView = new ModelAndView("manager/category-form");
            categoryService.deleteCategory(Long.parseLong(categoryId));
        modelAndView.addObject("categoryList", categoryService.listCategory());
        return modelAndView;
    }

    @GetMapping("updateCategory/{categoryId}")
    public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView modelAndView = new ModelAndView("manager/updateCategory");
        modelAndView.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
        return modelAndView;
    }

    @GetMapping("product-form")
    public ModelAndView listProduct() {
        ModelAndView modelAndView = new ModelAndView("manager/product-form");
        modelAndView.addObject("categoryList", categoryService.listCategory());
        modelAndView.addObject("productList", productService.listProduct());
        return modelAndView;
    }

    @PostMapping("add-product")
    public ModelAndView addProduct(Product product, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("manager/product-form");
        System.out.println("file: " + file.getOriginalFilename());
        String filePath = fileUploadService.upload(file);
        product.setImage(filePath);

        System.out.println(product.getImage());

        productService.addProduct(product);
        modelAndView.addObject("productList", productService.listProduct());
        return modelAndView;
    }

    @GetMapping("delete-Product/{productId}")
    public ModelAndView deleteProduct(@PathVariable("productId")String productId) {
        ModelAndView modelAndView = new ModelAndView("manager/product-form");
        productService.deleteProduct(Long.parseLong(productId));
        modelAndView.addObject("productList", productService.listProduct());
        return modelAndView;
    }

    @GetMapping("updateProduct/{productId}")
    public ModelAndView updateProduct(@PathVariable("productId")String productId) {
        ModelAndView modelAndView = new ModelAndView("manager/updateProduct");
        modelAndView.addObject("categoryList", categoryService.listCategory());
        modelAndView.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
        return modelAndView;
    }

}

