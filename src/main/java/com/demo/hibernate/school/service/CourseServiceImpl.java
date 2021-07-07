package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.CourseDAO;
import com.demo.hibernate.school.dao.CourseDAOimp;
import com.demo.hibernate.school.model.Course;
import com.demo.hibernate.school.model.Person;

import java.util.Set;

public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;

    public CourseServiceImpl() {
        this.courseDAO = CourseDAOimp.getInstance();
    }

    @Override
    public void addCourse(Course course) {
        courseDAO.addCourse(course);
    }

    @Override
    public Course getCourseById(long id) {
        return courseDAO.getCourseById(id);
    }

    @Override
    public Set<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    @Override
    public void updateCourse(Course course) {
        courseDAO.updateCourse(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseDAO.deleteCourse(course);
    }

   
}
