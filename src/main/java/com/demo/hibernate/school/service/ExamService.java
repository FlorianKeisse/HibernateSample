package com.demo.hibernate.school.service;


import com.demo.hibernate.school.model.Exam;
import java.util.Set;
public interface ExamService {

    void addExam(Exam exam);

    Exam getExamById(long id);

    Set<Exam> getAllExams();

    void updateExam(Exam exam);

    void deleteExam(Exam exam);

}
