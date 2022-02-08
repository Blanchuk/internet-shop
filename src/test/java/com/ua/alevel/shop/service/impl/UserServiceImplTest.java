package com.ua.alevel.shop.service.impl;

import com.ua.alevel.shop.model.Product;
import com.ua.alevel.shop.model.User;
import com.ua.alevel.shop.repository.UserRepository;
import com.ua.alevel.shop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    public void testSave() {
        User user = new User();
        userService.save(user);
        verify(userRepository).save(user);
    }

    @Test
    public void testFindAllUser() {
        User user = new User();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        List<User> users = userService.findAllUser();
        assertEquals(user, users.get(0));
    }

    @Test
    public void testDeleteUser(){
        userService.deleteUser(0L);
        verify(userRepository).deleteById(0L);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        Product product = new Product();
        user.setEmail("qwert@maiol.com");
        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        user.setProductList(productList);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(user);
        userService.update(user);
        verify(userRepository).save(user);
    }
}