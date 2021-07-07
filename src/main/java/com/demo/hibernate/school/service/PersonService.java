package com.demo.hibernate.school.service;

import com.demo.hibernate.school.model.Person;
import java.util.Set;
public interface PersonService {

    void addPerson(Person person);

    Person getPersonById(int id);

    Set<Person> getAllPeople();

    void updatePerson(Person person);

    void deletePerson(Person person);
}
