package com.niit.repository;

import com.niit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByEmailAndPassword(String email,String password);
}
