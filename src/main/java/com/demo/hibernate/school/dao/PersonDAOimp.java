package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class PersonDAOimp implements  PersonDAO{

    private static PersonDAO personDAO;
    private EntityManagerFactory emf;
    private Person person = new Person();


    public PersonDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static PersonDAO getInstance() {
        if (personDAO == null)
            personDAO = new PersonDAOimp();

        return personDAO;
    }

    @Override
    public void addPerson(Person person){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }

    //id is een string so idk prob not gonna work //Todo
    @Override
    public Person getPersonById(int id){
        EntityManager em = emf.createEntityManager();
        return em.find(Person.class,id);
    }

    @Override
    public Set<Person> getAllPeople (){
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Person p");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updatePerson(Person person) {
        EntityManager em= emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
    }

    @Override
    public void deletePerson(Person person){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        person = em.find(Person.class, person.getId());
        em.remove(person);
        em.getTransaction().commit();
    }
}
