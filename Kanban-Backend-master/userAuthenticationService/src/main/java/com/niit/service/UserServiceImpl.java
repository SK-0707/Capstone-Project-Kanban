package com.niit.service;

import com.niit.exception.UserNotFoundException;
import com.niit.model.User;
import com.niit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmailAndPassword(email,password);
        if(user==null){
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
