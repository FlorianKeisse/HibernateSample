package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.GradeDAO;
import com.demo.hibernate.school.dao.GradeDAOimp;
import com.demo.hibernate.school.model.Grade;
import com.demo.hibernate.school.model.Person;

import java.util.Set;

public class GradeServiceImpl implements GradeService {
    GradeDAO gradeDAO;

    public GradeServiceImpl() {
        this.gradeDAO = GradeDAOimp.getInstance();
    }

    @Override
    public void addGrade(Grade grade) {
        gradeDAO.addGrade(grade);
    }

    @Override
    public Grade getGradeById(long id) {
        return gradeDAO.getGradeById(id);
    }

    @Override
    public Set<Grade> getAllGrades() {
        return gradeDAO.getAllGrades();
    }

    @Override
    public Set<Grade> getGradesByPerson(Person person) {
        return gradeDAO.getGradesByPerson(person.getId());
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeDAO.updateGrade(grade);
    }

    @Override
    public void deleteGrade(Grade grade) {
        gradeDAO.deleteGrade(grade);
    }
}
