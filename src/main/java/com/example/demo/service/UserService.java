package com.example.demo.service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Optional<User> findUserByName(String username){
        return userRepository.findByUsername(username);
    }

}
