package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


public class UserDAOimp implements UserDAO {

    private static UserDAO userDAO;
    private EntityManagerFactory emf;

    public UserDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static UserDAO getInstance() {
        if (userDAO == null)
            userDAO = new UserDAOimp();

        return userDAO;
    }

    @Override
    public void addUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public User getUserById(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(User.class, id);
    }

    @Override
    public User getUserByPersonId(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM User u WHERE u.person.id=:userID");
        query.setParameter("userID", id);
        return (User) query.getSingleResult();
    }

    @Override
    public Set<User> getAllUsers() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM User u");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

    }

    @Override
    public void deleteUser(User user) {
        EntityManager em = EMFactory.getEMF().createEntityManager();
        em.getTransaction().begin();
        user = em.find(User.class, user.getLogin());
        em.remove(user);
        em.getTransaction().commit();
    }
}
