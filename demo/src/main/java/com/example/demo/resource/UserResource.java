package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import com.example.demo.service.exceptions.ResourceNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        try {
            User user = userService.getById(id);
            return ResponseEntity.ok().body(user);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<User> insert(@RequestBody User user) {
        try {
            User newUser = userService.insert(user);
            return ResponseEntity.ok().body(newUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        try {
            User newUser = userService.update(id, user);
            return ResponseEntity.ok().body(newUser);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
