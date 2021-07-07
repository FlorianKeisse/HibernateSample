package com.demo.hibernate.school.service;

import com.demo.hibernate.school.dao.ExamDAO;
import com.demo.hibernate.school.dao.ExamDAOimp;
import com.demo.hibernate.school.model.Exam;

import java.util.Set;

public class ExamServiceImpl implements ExamService {

    ExamDAO examDAO;

    public ExamServiceImpl() {
        this.examDAO = ExamDAOimp.getInstance();
    }

    @Override
    public void addExam(Exam exam) {
        examDAO.addExam(exam);
    }

    @Override
    public Exam getExamById(long id) {
        return examDAO.getExamById(id);
    }

    @Override
    public Set<Exam> getAllExams() {
        return examDAO.getAllExams();
    }

    @Override
    public void updateExam(Exam exam) {
        examDAO.updateExam(exam);
    }

    @Override
    public void deleteExam(Exam exam) {
        examDAO.deleteExam(exam);
    }
}
