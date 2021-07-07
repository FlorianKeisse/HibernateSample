package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.PersonDAO;
import com.demo.hibernate.school.dao.PersonDAOimp;
import com.demo.hibernate.school.dao.UserDAO;
import com.demo.hibernate.school.dao.UserDAOimp;
import com.demo.hibernate.school.model.Person;
import com.demo.hibernate.school.model.User;

import java.util.Set;

public class UserServiceImpl implements UserService {

    UserDAO userDAO;
    PersonDAO personDAO;

    public UserServiceImpl() {
        this.userDAO = UserDAOimp.getInstance();
        this.personDAO = PersonDAOimp.getInstance();
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    @Override
    public Set<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }
}
