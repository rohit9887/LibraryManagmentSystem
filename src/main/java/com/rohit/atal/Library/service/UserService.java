package com.rohit.atal.Library.service;

import com.rohit.atal.Library.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public abstract List<User> getAllUsers();
    public abstract Optional<User> getUserById(int Userid);
    public abstract User createUser(User user);
    public abstract User updateUser(int Userid, User updatedUser);
    public abstract User deleteUser(Long userId);
}
