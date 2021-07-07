package com.demo.hibernate.school.dao;

import com.demo.hibernate.school.data.EMFactory;
import com.demo.hibernate.school.model.Exam;

import java.util.Set;

public interface ExamDAO {

    void addExam(Exam exam);

    Exam getExamById(long id);

    Set<Exam> getAllExams();

    void updateExam(Exam exam);

    void deleteExam(Exam exam);
}
