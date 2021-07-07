package com.demo.hibernate.school;

import com.demo.hibernate.school.model.Module;
import com.demo.hibernate.school.model.*;
import com.demo.hibernate.school.model.Person.Gender;
import com.demo.hibernate.school.service.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestApp {

    private CourseService courseService;
    private ExamService examService;
    private PersonService personService;
    private UserService userService;
    private ModuleService moduleService;

    public TestApp() {
        this.courseService = new CourseServiceImpl();
        this.examService = new ExamServiceImpl();
        this.personService = new PersonServiceImpl();
        this.userService = new UserServiceImpl();
        this.moduleService = new ModuleServiceImpl();
    }

    public static void main(String[] args) {
        new TestApp().addmoduleToCourseTest();
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

    public void full_relat_test() {
        User user = new User();
        Person person = new Person();
        Course course = new Course();
        Module module = new Module();
        Exam exam = new Exam();

        user.setLogin("guga");
        user.setActive(true);
        user.setPasswordHash("hash");
        user.setPerson(person);


        person.setFirstName("John");
        person.setLastName("Doe");
        person.setGender(Gender.MALE);
        person.setCourse(course);

        course.setName("Front-end Development");
        course.setActive(true);
        course.setCode("Y1337_4LSR");
        course.setImageURL("src/main/resources/fake_img.jpg");
        course.setDescription("Basic to advanced programming");
        List<Module> modules = new ArrayList<>();
        modules.add(module);
        course.setModules(modules);

        module.setName("Angular");
        module.setCourse(course);
        module.setDescription("Description");
        List<Exam> exams = new ArrayList<>();
        exams.add(exam);
        module.setExams(exams);

        exam.setName("Services Test");
        exam.setDescription("Description");
        exam.setDate(LocalDate.now());
        exam.setTotal(50);
        exam.setWeight(15);
        exam.setModule(module);

        userService.addUser(user);
    }


}


