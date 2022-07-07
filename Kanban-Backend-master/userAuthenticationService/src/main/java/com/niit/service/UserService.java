package com.niit.service;

import com.niit.exception.UserNotFoundException;
import com.niit.model.User;

import java.util.List;

public interface UserService {
    public User saveUser(User user);
    public User findByEmailAndPassword(String email,String password) throws UserNotFoundException;
    public List<User> getAllUsers();
}
