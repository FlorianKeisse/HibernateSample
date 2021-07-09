package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Grade;
import com.demo.hibernate.school.model.Module;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

public class GradeDAOimp implements GradeDAO{

    private static GradeDAO gradeDAO;
    private EntityManagerFactory emf;

    public GradeDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static GradeDAO getInstance() {
        if (gradeDAO == null)
            gradeDAO = new GradeDAOimp();

        return gradeDAO;
    }

    @Override
    public void addGrade(Grade grade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(grade);
        em.getTransaction().commit();
    }

    @Override
    public Grade getGradeById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Grade.class, id);
    }

    @Override
    public Set<Grade> getAllGrades() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Grade g");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Grade> getGradesByPerson(int id) {
            EntityManager em = emf.createEntityManager();
            Query query = em.createQuery("SELECT g FROM Grade g WHERE g.person.id=:userID");
            query.setParameter("userID", id);
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateGrade(Grade grade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(grade);
        em.getTransaction().commit();
    }

    @Override
    public void deleteGrade(Grade grade) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Grade.class, grade.getId()));
        em.getTransaction().commit();
    }
}
