package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Users not found");
        }
    }

    public User getById(Long id) {
        try {
            Optional<User> user = userRepository.findById(id);
            return user.get();
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }

    public User insert(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id " + user.getId());
        }
    }

    public void delete(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id " + id);
            }
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }

    public User update(Long id, User user) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id " + id);
            }
            return userRepository.save(user);
        } catch (Exception e) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
    }
}