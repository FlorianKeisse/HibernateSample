package com.demo.hibernate.school.service;

import com.demo.hibernate.school.model.Grade;
import com.demo.hibernate.school.model.Person;

import java.util.Set;

public interface GradeService {

    void addGrade(Grade grade);

    Grade getGradeById(long id);

    Set<Grade> getAllGrades();
    
    Set<Grade> getGradesByPerson(Person person);

    void updateGrade(Grade grade);

    void deleteGrade(Grade grade);
}
