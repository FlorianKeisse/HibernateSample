package com.demo.hibernate.school.service;


import com.demo.hibernate.school.model.User;

import java.util.Set;

public interface UserService {

    void addUser(User user);

    User getUserById(String id);

    Set<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);
}
