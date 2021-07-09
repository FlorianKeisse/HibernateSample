package com.demo.hibernate.school;

import com.demo.hibernate.school.model.Module;
import com.demo.hibernate.school.model.*;
import com.demo.hibernate.school.model.Person.Gender;
import com.demo.hibernate.school.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestApp {

    private CourseService courseService;
    private ExamService examService;
    private PersonService personService;
    private UserService userService;
    private ModuleService moduleService;
    private GradeService gradeService;

    public TestApp() {
        this.courseService = new CourseServiceImpl();
        this.examService = new ExamServiceImpl();
        this.personService = new PersonServiceImpl();
        this.userService = new UserServiceImpl();
        this.moduleService = new ModuleServiceImpl();
        this.gradeService = new GradeServiceImpl();
    }

    public static void main(String[] args) {
        var app = new TestApp();
        //app.full_relat_test();
        app.addGradeToPersonAndExam();
    }

    public void addmoduleToCourseTest() {
        Course course = courseService.getCourseById(2);
        Module module = new Module();
        Exam exam = new Exam();

        module.setName("JPA");
        module.setCourse(course);
        module.setDescription("Description");

        exam.setName("ORM");
        exam.setDescription("Description");
        exam.setDate(LocalDate.now());
        exam.setTotal(50);
        exam.setWeight(15);
        exam.setModule(module);

        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        module.setExams(exams);

        course.getModules().add(module);

        courseService.updateCourse(course);
    }

    public void addGradeToPersonAndExam() {
        Grade grade = new Grade();

        grade.setAbsent(false);
        grade.setPerson(userService.getUserById("testuser").getPerson());
        grade.setExam(examService.getExamById(20));
        grade.setComment("knecht");
        grade.setInternalComment("jokster");
        grade.setLocalDate(LocalDate.now());

        grade.setGrade(BigDecimal.ZERO);

        gradeService.addGrade(grade);
    }

    public void full_relat_test() {
        User user = new User();
        Person person = new Person();
        Course course = new Course();
        Module module = new Module();
        Exam parentExam = new Exam();
        Exam exam = new Exam();


        user.setLogin("testuser");
        user.setActive(true);
        user.setPasswordHash("hash");
        user.setPerson(person);


        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Gender.MALE);
        person.setCourseActive(course);
        List<Course> history = new ArrayList<>();
        history.add(course);
        person.setCourseHistory(history);

        course.setName("Java EE Development");
        course.setActive(true);
        course.setCode("Y1337_4LSR");
        course.setImageURL("src/main/resources/fake_img.jpg");
        course.setDescription("Basic to advanced programming");
        List<Module> modules = new ArrayList<>();
        modules.add(module);
        course.setModules(modules);

        module.setName("Hibernate");
        module.setCourse(course);
        module.setDescription("Description");
        List<Exam> exams = new ArrayList<>();
        exams.add(parentExam);
        module.setExams(exams);

        parentExam.setName("ORM");
        parentExam.setDescription("Description");
        parentExam.setDate(LocalDate.now());
        parentExam.setTotal(50);
        parentExam.setWeight(15);
        parentExam.setModule(module);
        List<Exam> subExams = new ArrayList<>();
        exams.add(exam);
        parentExam.setSubExams(subExams);

        exam.setName("Entity management");
        exam.setDescription("Description");
        exam.setDate(LocalDate.now());
        exam.setTotal(50);
        exam.setWeight(15);
        exam.setModule(module);
        exam.setExamGroup(parentExam);
        userService.addUser(user);

    }


}


