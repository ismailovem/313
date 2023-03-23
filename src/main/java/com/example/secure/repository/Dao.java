package com.example.secure.repository;


import com.example.secure.entity.User;

import java.util.List;


public interface Dao {
    User findById(Long id);
    void save(User user);
    void update(User user,Long id);
    void removeById(Long id);
    List<User> getAll();

}
