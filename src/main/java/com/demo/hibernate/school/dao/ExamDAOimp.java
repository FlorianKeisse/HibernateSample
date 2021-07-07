package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Exam;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


public class ExamDAOimp implements ExamDAO {

    private static ExamDAO examDAO;
    private EntityManagerFactory emf;

    public ExamDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static ExamDAO getInstance() {
        if (examDAO== null)
            examDAO = new ExamDAOimp();

        return examDAO;
    }

    @Override
    public void addExam(Exam exam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(exam);
        em.getTransaction().commit();
    }

    @Override
    public Exam getExamById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Exam.class, id);
    }

    @Override
    public Set<Exam> getAllExams() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Exam e");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateExam(Exam exam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(exam);
        em.getTransaction().commit();
    }

    @Override
    public void deleteExam(Exam exam) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Exam.class, exam.getId()));
        em.getTransaction().commit();
    }
}
