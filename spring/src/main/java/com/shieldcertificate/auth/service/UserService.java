package com.shieldcertificate.auth.service;

import com.shieldcertificate.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
