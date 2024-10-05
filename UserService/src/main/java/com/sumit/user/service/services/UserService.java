package com.sumit.user.service.services;

import com.sumit.user.service.entities.User;

import java.util.List;

public interface UserService {

    // Create a new user
    User createUser(User user);

    // Get a user by their ID
    User getUser(String uid);

    // Update user details based on their ID
    User updateUser(String uid, User user);

    // Delete a user by their ID
    void deleteUser(String uid);

    // Get a list of all users
    List<User> getAllUsers();
}
