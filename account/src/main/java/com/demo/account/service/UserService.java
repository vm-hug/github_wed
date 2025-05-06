package com.demo.account.service;

import com.demo.account.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(long id);
    void deleteUserById(long id );
    List<User> getAllUsers();
}
