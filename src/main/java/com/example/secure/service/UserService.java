package com.example.secure.service;

import com.example.secure.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;


public interface UserService extends UserDetailsService {
    void saveUser(User user);
    User findById(Long id);
    void removeById(Long id);
    void update(User user, Long id);
    List<User> getAllUsers();
}

