package com.example.demoDynamo.service;

import com.example.demoDynamo.dto.User;

public interface UserCrudService {
    User createUser(User user);

    User readUser(String userId);

    User updateUser(User user);

    void deleteUser(String userId);
}
