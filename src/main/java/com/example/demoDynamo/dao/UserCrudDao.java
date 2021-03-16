package com.example.demoDynamo.dao;

import com.example.demoDynamo.dto.User;

public interface UserCrudDao {
    User createUser(User user);

    User readUser(String userId);

    User updateUser(User user);

    void deleteUser(String userId);
}
