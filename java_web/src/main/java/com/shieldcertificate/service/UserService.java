package com.shieldcertificate.service;

import com.shieldcertificate.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
}
