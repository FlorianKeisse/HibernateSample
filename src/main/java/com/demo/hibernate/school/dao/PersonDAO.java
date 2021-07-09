package com.demo.hibernate.school.dao;


import com.demo.hibernate.school.model.Person;

import java.util.Set;

public interface PersonDAO {

    void addPerson(Person person);

    Person getPersonById(int id);

    Set<Person> getAllPeople();
    
    Set<Person> getAllPeopleByCourse(long courseId);

    void updatePerson(Person person);

    void deletePerson(Person person);
}
