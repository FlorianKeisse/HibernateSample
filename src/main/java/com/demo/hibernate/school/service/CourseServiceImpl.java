package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.*;
import com.demo.hibernate.school.model.Course;
import com.demo.hibernate.school.model.Module;
import com.demo.hibernate.school.model.Person;

import java.util.List;
import java.util.Set;

public class CourseServiceImpl implements CourseService {

    private CourseDAO courseDAO;
    private PersonDAO personDAO;
    private ModuleDAO moduleDAO;

    public CourseServiceImpl() {
        this.courseDAO = CourseDAOimp.getInstance();
        this.personDAO = PersonDAOimp.getInstance();
        this.moduleDAO = ModuleDAOimp.getInstance();
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

        Set<Person> peopleInCourse = personDAO.getAllPeopleByCourse(course.getId());
        for (Person p : peopleInCourse) {
            p.setCourseActive(null);
            List<Course> courseHistory = p.getCourseHistory();
            if (courseHistory.contains(course))
                courseHistory.remove(course);
            p.setCourseHistory(courseHistory);
            personDAO.updatePerson(p);
        }
        Set<Module> modulesInCourse= moduleDAO.getAllModulesByCourse(course.getId());
        for (Module m:modulesInCourse){
            m.setCourse(null);
            moduleDAO.updateModule(m);
        }

        courseDAO.deleteCourse(course);
    }


}
