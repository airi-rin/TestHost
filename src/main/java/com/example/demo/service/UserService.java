package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.CreateUserRequest;
import com.example.demo.respository.RoleRepository;
import com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        UserDetailsPrincipal userDetailsPrincipal = new UserDetailsPrincipal(user);
        return userDetailsPrincipal;
    }

    public UserDetails loadUserById(int id) {
        UserEntity user = userRepository.findById(id);
        UserDetailsPrincipal userDetailsPrincipal = new UserDetailsPrincipal(user);
        return userDetailsPrincipal;
    }

    public UserEntity addUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setGender(createUserRequest.getGender());
        RoleEntity role = roleRepository.findByRoleName(createUserRequest.getRole());
        user.setRole(role);
        return userRepository.save(user);
    }

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }
}