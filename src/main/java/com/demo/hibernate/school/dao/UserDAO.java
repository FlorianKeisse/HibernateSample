package com.demo.hibernate.school.dao;


import com.demo.hibernate.school.model.User;

import java.util.Set;

public interface UserDAO {

    void addUser(User user);

    User getUserById(String id);

    User getUserByPersonId(int id);

    Set<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);
}
