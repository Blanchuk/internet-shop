package com.ua.alevel.shop.controller;

import com.ua.alevel.shop.model.Product;
import com.ua.alevel.shop.model.User;
import com.ua.alevel.shop.service.ProductService;
import com.ua.alevel.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("profile")
public class ProfileController {

    private final ProductService productService;

    private final UserService userService;

    public ProfileController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("cart-product")
    public ModelAndView cartProduct(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("profile/cart-product");
        User user = userService.findByEmail(principal.getName());
        modelAndView.addObject("user", user);
        int total = findSum(user);
        modelAndView.addObject("total", total);
        return modelAndView;
    }

    private int findSum(User user) {
        List<Product> list = user.getProductList();
        int sum =0;
        for (Product product : list) {
            sum += product.getProductPrice();
        }
        return sum;
    }

    @GetMapping("addToCart/{productId}")
    public ModelAndView addToCart(@PathVariable("productId")String productId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("profile/cart-product");
        User user = userService.findByEmail(principal.getName());
        long productLongId = Long.parseLong(productId);
        Product product = productService.getProductById(productLongId).get();

        List<Product> productList = new ArrayList<>();
        productList.add(product);
        user.setProductList(productList);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        product.setUserList(userList);

        userService.update(user);
        productService.addProduct(product);
        int total = findSum(user);
        modelAndView.addObject("total", total);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

}

