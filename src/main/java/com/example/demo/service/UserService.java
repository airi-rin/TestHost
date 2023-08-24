package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.request.user.CreateUserRequest;
import com.example.demo.response.user.UserResponse;
import com.example.demo.respository.RoleRepository;
import com.example.demo.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public UserResponse addUser(CreateUserRequest createUserRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(createUserRequest.getPassword()));
        user.setEmail(createUserRequest.getEmail());
        user.setGender(createUserRequest.getGender());
        RoleEntity role = roleRepository.findByRoleName(createUserRequest.getRole());
        user.setRole(role);
        userRepository.save(user);
        return UserResponse.init(user);
    }

    public List<UserEntity> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserResponse> userResponseList = userEntityList.stream()
                .map(UserResponse::init)
                .collect(Collectors.toList());
        return  userEntityList;
    }
}