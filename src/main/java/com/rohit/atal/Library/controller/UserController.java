package com.rohit.atal.Library.controller;

import com.rohit.atal.Library.Model.User;
import com.rohit.atal.Library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id) {
        return userService.getUserById(Integer.parseInt(id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userService.updateUser(Integer.parseInt(id), updatedUser);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable String id) {
        return userService.deleteUser(Long.valueOf(id));
    }
}
