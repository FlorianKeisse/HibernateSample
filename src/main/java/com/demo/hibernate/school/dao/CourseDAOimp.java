package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Course;
import com.demo.hibernate.school.model.Person;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class CourseDAOimp implements CourseDAO {

    private static CourseDAO courseDao;
    private EntityManagerFactory emf;

    public CourseDAOimp() {
        emf = EMFactory.getEMF();
    }

    public static CourseDAO getInstance() {
        if (courseDao == null)
            courseDao = new CourseDAOimp();

        return courseDao;
    }

    @Override
    public void addCourse(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
    }

    @Override
    public Course getCourseById(long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Course.class, id);
    }

    @Override
    public Set<Person> getAllPeople() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Person p");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public Set<Course> getAllCourses() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Course c");
        return new HashSet<>(query.getResultList());
    }

    @Override
    public void updateCourse(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(course);
        em.getTransaction().commit();
    }

    @Override
    public void deleteCourse(Course course) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Course.class, course.getId()));
        em.getTransaction().commit();
    }

}
