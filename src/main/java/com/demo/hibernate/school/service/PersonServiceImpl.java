package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.PersonDAO;
import com.demo.hibernate.school.dao.PersonDAOimp;
import com.demo.hibernate.school.dao.UserDAO;
import com.demo.hibernate.school.dao.UserDAOimp;
import com.demo.hibernate.school.model.Person;
import com.demo.hibernate.school.model.User;

import java.util.Set;

public class PersonServiceImpl implements PersonService {

    PersonDAO personDAO;
    UserDAO userDAO;

    public PersonServiceImpl() {
        this.personDAO = PersonDAOimp.getInstance();
        this.userDAO = UserDAOimp.getInstance();
    }

    @Override
    public void addPerson(Person person) {
        personDAO.addPerson(person);
    }

    @Override
    public Person getPersonById(int id) {
        return personDAO.getPersonById(id);
    }

    @Override
    public Set<Person> getAllPeople() {
        return personDAO.getAllPeople();
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        User user = userDAO.getUserByPersonId(person.getId());
        userDAO.deleteUser(user);
    }
}
