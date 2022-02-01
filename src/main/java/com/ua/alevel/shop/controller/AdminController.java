package com.ua.alevel.shop.controller;

import com.ua.alevel.shop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("index")
    public String index() {
        return "admin/index";
    }

    @GetMapping("user-list")
    public String userList(Model model) {
        model.addAttribute("userList", userService.findAllUser());
        return "admin/user-list";
    }

    @GetMapping("delete-User/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId")String userId) {
        ModelAndView modelAndView = new ModelAndView("admin/user-list");
        userService.deleteUser(Long.parseLong(userId));
        modelAndView.addObject("userList", userService.findAllUser());
        return modelAndView;
    }

    @GetMapping("add-user")
    public String addUser() {
        return "admin/add-user";
    }

}
