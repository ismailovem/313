package com.example.secure.service;

import com.example.secure.entity.User;
import com.example.secure.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
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
        userRepository.save(user);
    }
    @Transactional
    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }
    @Transactional
    @Override
    public void update(User user, Long id) {
        User beforeUpdateUser = findById(id);
        beforeUpdateUser.setUsername(user.getUsername());
        beforeUpdateUser.setLastName(user.getLastName());
        beforeUpdateUser.setPassword(user.getPassword());
        beforeUpdateUser.setRoles(user.getRoles());
    }
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}