package com.ua.alevel.shop.service;

import com.ua.alevel.shop.model.User;

import java.util.List;

public interface UserService {

    User findByEmail(String email);

    void save(User user);

    void update(User user);

    List<User> findAllUser();

    void deleteUser(long userId);
}
