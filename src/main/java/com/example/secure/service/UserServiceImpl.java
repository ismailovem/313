package com.example.secure.service;


import com.example.secure.entity.Role;
import com.example.secure.entity.User;
import com.example.secure.repository.RoleRepository;
import com.example.secure.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Lazy
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found "+ username);
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword()).authorities(user.getRoles()).build();
        return userDetails;
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = findByRole("ROLE_USER");
        user.setRoles(new ArrayList<>(Arrays.asList(userRole)));
        userRepository.save(user);
    }
    @Override
    public void removeById(Long id) {
        userRepository.deleteById(id);
    }
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
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Role> getAllRoles() { return roleRepository.findAll(); }
    public Role findByRole (String role) { return roleRepository.findByRole(role); }
}