package com.example.secure.service;

import com.example.secure.entity.User;
import com.example.secure.repository.DaoImpl;
import com.example.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final DaoImpl dao;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(DaoImpl dao, UserRepository userRepository) {
        this.dao = dao;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found "+ username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities(user.getAuthorities()).build();
        return userDetails;
    }
    @Transactional
    @Override
    public void saveUser(User user) {
        dao.save(user);
    }
    @Transactional
    @Override
    public void removeById(Long id) {
        dao.removeById(id);
    }
    @Transactional
    @Override
    public void update(User user, Long id) {
        dao.update(user,id);
    }
    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
    @Override
    public User findById(Long id) {
        return dao.findById(id);
    }

}