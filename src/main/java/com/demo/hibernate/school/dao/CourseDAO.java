package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.model.Course;
import com.demo.hibernate.school.model.Person;

import java.util.Set;

public interface CourseDAO {

    void addCourse(Course course);

    Course getCourseById(long id);

    Set<Course> getAllCourses();

     void updateCourse(Course course);

    void deleteCourse(Course course);

    Set<Person> getAllPeople();
}
