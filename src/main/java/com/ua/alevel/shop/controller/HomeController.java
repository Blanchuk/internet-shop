package com.ua.alevel.shop.controller;

import com.ua.alevel.shop.model.User;
import com.ua.alevel.shop.service.CategoryService;
import com.ua.alevel.shop.service.ProductService;
import com.ua.alevel.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private final UserService userService;

    private final CategoryService categoryService;

    private final ProductService productService;


    public HomeController(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping({"index", "/"})
    public String index(Model model) {
        model.addAttribute("categoryList", categoryService.listCategory());
        model.addAttribute("productList", productService.listProduct());
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public ModelAndView signUp(@Valid User user) {
        ModelAndView modelAndView = new ModelAndView("/index");
        userService.save(user);
        modelAndView.addObject("productList", productService.listProduct());
        modelAndView.addObject("categoryList", categoryService.listCategory());
        return modelAndView;
    }

    @GetMapping("allProduct")
    public String allProduct(Model model) {
        model.addAttribute("productList", productService.listProduct());
        model.addAttribute("categoryList", categoryService.listCategory());
        return "index";
    }

    @GetMapping("getProducts/{categoryId}")
    public ModelAndView getProductFromCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView modelAndView = new ModelAndView("index");
        long categoryLongId = Long.parseLong(categoryId);
        System.out.println(categoryLongId);
        modelAndView.addObject("productList", productService.findByCategory(categoryLongId));
        modelAndView.addObject("categoryList", categoryService.listCategory());
        return modelAndView;
    }

    @GetMapping("error")
    public String error() {
        return "error";
    }

}
