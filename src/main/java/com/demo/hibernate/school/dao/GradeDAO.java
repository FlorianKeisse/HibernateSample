package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.model.Grade;
import java.util.Set;

public interface GradeDAO {

    void addGrade(Grade grade);

    Grade getGradeById(long id);

    Set<Grade> getAllGrades();
    
   Set<Grade> getGradesByPerson(int id);

    void updateGrade(Grade grade);

    void deleteGrade(Grade grade);
}
