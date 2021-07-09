package com.demo.hibernate.school;

import com.demo.hibernate.school.model.Module;
import com.demo.hibernate.school.model.*;
import com.demo.hibernate.school.service.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class MainApp {

    private Scanner scanner;
    public static final String MENU = "What do you want to look at?\n1: Users\n2: People\n3: Courses\n4: Modules\n5: Exams\n6: Grades\n0: End";
    public static final String SUBMENU = "What do you want to look at? %n1: See all %n2: See id %n3: Add %n4: Update%n5: Delete %s %n0: End%n";
    public static final String ENTER_USERNAME = "Enter Login:";
    public static final String ENTER_PASSWORD = "Enter Password:";

    private CourseService courseService;
    private PersonService personService;
    private ModuleService moduleService;
    private ExamService examService;
    private UserService userService;
    private GradeService gradeService;

    public MainApp() {
        this.scanner = new Scanner(System.in);
        this.courseService = new CourseServiceImpl();
        this.personService = new PersonServiceImpl();
        this.moduleService = new ModuleServiceImpl();
        this.examService = new ExamServiceImpl();
        this.userService = new UserServiceImpl();
        this.gradeService = new GradeServiceImpl();
    }

    public static void main(String[] args) {
        MainApp mainApp = new MainApp();
        mainApp.run();
    }

    public void run() {
        try {
            mainMenu();

        } finally {
            scanner.close();
        }

    }

    private void mainMenu() {
        while (true) {
            System.out.println(MENU);
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    userMenu();
                    break;
                case 2:
                    personMenu();
                    break;
                case 3:
                    courseMenu();
                    break;
                case 4:
                    moduleMenu();
                    break;
                case 5:
                    examMenu();
                    break;
                case 6:
                    gradeMenu();
                    break;
                case 0:
                    System.exit(0);
                    return;
                default:
                    break;
            }
        }
    }

    private void userMenu() {
        System.out.printf(SUBMENU, "");
        int subMenuChoice = scanner.nextInt();
        switch (subMenuChoice) {
            case 1:
                seeAllUsers();
                break;
            case 2:
                printUser();
                break;
            case 3:
                createUser();
                break;
            case 4:
                updateUser();
                break;
            case 5:
                deleteUser();
                break;
            default:
                break;
        }
    }

    private void seeAllUsers() {
        Set<User> userSet = userService.getAllUsers();
        userSet.stream().forEach(System.out::println);
    }

    private void printUser() {
        User user = getUserById();
        System.out.println(user);
    }

    private void createUser() {
        User user = new User();
        //                Person person = new Person();

        setUserInfo(user);
        user.setPerson(createPerson());
        userService.addUser(user);
    }

    private void updateUser() {
        User user = getUserById();
        if (user == null) {
            System.err.println("User doesn't exist.");
        } else {
            setUserInfo(user);
            userService.updateUser(user);
        }
    }

    private void setUserInfo(User user) {
        System.out.println(ENTER_USERNAME);
        String login = scanner.next();
        System.out.println(ENTER_PASSWORD);
        String passwordHash = scanner.next();

        user.setLogin(login);
        user.setPasswordHash(passwordHash);
        user.setActive(true);


    }

    private void deleteUser() {
        User user = getUserById();

        if (user == null)
            System.err.println("User doesn't exist.");

        else
            userService.deleteUser(user);
    }

    private void personMenu() {
        System.out.printf(SUBMENU, "");
        int subMenuChoice = scanner.nextInt();
        switch (subMenuChoice) {
            case 1:
                seeAllPeople();
                break;
            case 2:
                printPerson();
                break;
            case 3:
                createUser();
                break;
            case 4:
                updatePerson();
                break;
            case 5:
                deletePerson();
                break;
            default:
                break;
        }
    }

    private void seeAllPeople() {
        Set<Person> people = personService.getAllPeople();
        people.stream().forEach(System.out::println);
    }

    private void printPerson() {
        Person person = getPersonById();
        System.out.println(person);
    }

    private Person createPerson() {
        Person person = new Person();
        setPersonInfo(person);

        return person;
    }

    private void updatePerson() {
        Person person = getPersonById();

        if (person == null) {
            System.err.println("Person doesn't exist.");
        } else {
            setPersonInfo(person);
            personService.updatePerson(person);
        }
    }

    private void setPersonInfo(Person person) {
        System.out.println("Enter your first name:");
        String firstName = scanner.next();
        System.out.println("Enter your last name:");
        String lastName = scanner.next();

        System.out.println("Enter gender, 1 for Male | 2 for Female");
        int genderChoice = scanner.nextInt();

        if (genderChoice == 1) {
            person.setGender(Person.Gender.MALE);
        } else if (genderChoice == 2) {
            person.setGender(Person.Gender.FEMALE);
        }

        person.setFirstName(firstName);
        person.setLastName(lastName);
    }

    private void deletePerson() {
        Person person = getPersonById();
        personService.deletePerson(person);
    }

    private void courseMenu() {
        System.out.printf(SUBMENU, "\n6:Add module to course\n7:Add course to person\n");
        int subMenuChoice = scanner.nextInt();

        switch (subMenuChoice) {
            case 1:
                seeAllCourses();
                break;
            case 2:
                printCourse();
            case 3:
                addCourse();
                break;

            case 4:
                updateCourse();
                break;
            case 5:
                deleteCourse();
                break;
            case 6:
                addModuleToCourse();
                break;
            case 7:
                addCourseToPerson();
                break;
            default:
                break;
        }
    }

    private void addCourseToPerson() {
        seeAllCourses();
        Course course = getCourseById();

        seeAllPeople();
        Person person = getPersonById();

        person.setCourseActive(course);
        personService.updatePerson(person);
    }

    private void seeAllCourses() {
        Set<Course> courses = courseService.getAllCourses();
        courses.stream().forEach(System.out::println);
    }

    private void printCourse() {
        Course course = getCourseById();
        System.out.println(course);
    }

    private void addCourse() {
        Course course = new Course();
        setCourseInfo(course);
        courseService.addCourse(course);
    }

    private void updateCourse() {
        Course course = getCourseById();
        if (course == null) {
            System.err.println("Course doesn't exist.");
        } else {
            setCourseInfo(course);
            courseService.updateCourse(course);
        }
    }

    private void setCourseInfo(Course course) {
        System.out.println("Enter CourseName:");
        String name = scanner.next();
        System.out.println("Enter Description");
        String description = scanner.next();
        System.out.println("Enter ImgURL:");
        String imgURL = scanner.next();
        System.out.println("Enter Code:");
        String code = scanner.next();

        course.setName(name);
        course.setDescription(description);
        course.setImageURL(imgURL);
        course.setCode(code);
    }

    private void deleteCourse() {
        seeAllCourses();
        System.out.println("Enter id to be deleted:");
        Course course = getCourseById();
        courseService.deleteCourse(course);
    }

    private void moduleMenu() {
        System.out.printf(SUBMENU, "\n6: Add module to course\n7: Add exam to Module");
        int subMenuChoice = scanner.nextInt();

        switch (subMenuChoice) {
            case 1:
                seeAllModules();
                break;
            case 2:
                printModule();
                break;
            case 3:
                addModule();
                break;
            case 4:
                updateModule();
                break;
            case 5:
                deleteModule();
                break;
            case 6:
                addModuleToCourse();
                break;
            case 7:
                addExamToModule();
                break;
            default:
                break;
        }
    }

    private void addModuleToCourse() {
        seeAllCourses();
        Course course = getCourseById();
        seeAllModules();
        Module module = getModuleById();

        module.setCourse(course);
        moduleService.updateModule(module);
    }

    private void seeAllModules() {
        Set<Module> modules = moduleService.getAllModules();
        modules.stream().forEach(System.out::println);
    }

    private void printModule() {
        Module module = getModuleById();
        System.out.println(module);
        System.out.println("Exams in module:");
        module.getExams().forEach(System.out::println);
    }

    private void addModule() {
        Module module = new Module();

        setModuleInfo(module);
        moduleService.addModule(module);

    }

    private void setModuleInfo(Module module) {
        System.out.println("Enter Module name:");
        String name = scanner.next();
        System.out.println("Enter Module description:");
        String description = scanner.nextLine();

        module.setName(name);
        module.setDescription(description);

    }

    private void updateModule() {
        Module module = getModuleById();
        if (module == null) {
            System.err.println("Module doesn't exist");
        } else {
            setModuleInfo(module);
            moduleService.updateModule(module);
        }
    }


    private void examMenu() {
        System.out.printf(SUBMENU, "\n6: Add to Module\n");
        int subMenuChoice = scanner.nextInt();
        switch (subMenuChoice) {
            case 1:
                seeAllExams();
                break;
            case 2:
                printExam();
                break;
            case 3:
                createExam();
                break;
            case 4:
                updateExam();
                break;
            case 5:
                deleteExam();
                break;
            case 6:
                addExamToModule();
                break;
            default:
                break;
        }

    }

    private void seeAllExams() {
        Set<Exam> examSet = examService.getAllExams();
        examSet.stream().forEach(System.out::println);
    }

    private void printExam() {
        Exam exam = getExamById();
        System.out.println(exam);
    }

    private void createExam() {
        Exam exam = new Exam();

        System.out.println("Are you making a subExam? Y/N");
        String answer = scanner.next().toUpperCase();

        if (answer.equals("Y")){
            seeAllExams();
            System.out.println("Provide Parent ExamId");
            Exam parentExam = getExamById();

            if (parentExam!=null){
                exam.setExamGroup(parentExam);
                exam.setModule(parentExam.getModule());
            }
        }


        setExamInfo(exam);
        examService.addExam(exam);

    }

    private void updateExam() {
        Exam exam = getExamById();
        if (exam == null) {
            System.err.println("Exam doesn't exist.");
        } else {
            setExamInfo(exam);
            examService.updateExam(exam);
        }
    }

    private void setExamInfo(Exam exam) {
        System.out.println("Enter ExamName:");
        String name = scanner.next();
        System.out.println("Enter ExamDescription:");
        String description = scanner.next();
        System.out.println("Enter weight of the Exam:");
        int examWeight = scanner.nextInt();
        System.out.println("Enter total points of the Exam:");
        int examTotal = scanner.nextInt();
        System.out.println("Enter date of Exam:");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();

        exam.setName(name);
        exam.setDescription(description);
        exam.setWeight(examWeight);
        exam.setTotal(examTotal);
        exam.setDate(LocalDate.of(year, month, day));
    }

    private void deleteExam() {
        seeAllExams();
        System.out.println("Choose Exam you want to delete by id:");
        Exam exam = getExamById();
        if (exam == null)
            System.err.println("Exam doesn't exist.");
        else
            examService.deleteExam(exam);
    }

    private void addExamToModule() {
        seeAllExams();

        System.out.println("Choose an exam to add");
        Exam exam = getExamById();

        seeAllModules();
        System.out.println("Enter the module for the exam");
        Module module = getModuleById();

        exam.setModule(module);

        examService.updateExam(exam);
    }


    private void deleteModule() {
        Module module = getModuleById();
        moduleService.deleteModule(module);
    }

    private void gradeMenu() {
        System.out.printf(SUBMENU,"");
        int subMenuChoice = scanner.nextInt();

        switch (subMenuChoice) {
            case 1:
                seeAllGrades();
                break;
            case 2:
                printGrade();
                break;
            case 3:
                createGrades();
                break;
            case 4:
                updateGrades();
                break;
            case 5:
                deleteGrades();
                break;
            default:
                break;
        }
    }

    private void seeAllGrades() {
        seeAllPeople();
        System.out.println("Select a Person to see grades from");
        Person person = getPersonById();
        gradeService.getGradesByPerson(person).forEach(System.out::println);
    }

    private void printGrade() {
        Grade grade = getGradeById();
        System.out.println(grade);
    }

    private void createGrades() {
        seeAllExams();
        System.out.println("Select Exam to add a Grade.");
        Exam exam = getExamById();

        seeAllPeople();
        System.out.println("Select a Person to add a Grade.");
        Person person = getPersonById();


        Grade grade = new Grade();
        setGradeInfo(grade);
        grade.setExam(exam);
        grade.setPerson(person);

        gradeService.addGrade(grade);
    }

    private void setGradeInfo(Grade grade) {
        System.out.println("Set the grade:");
        BigDecimal ultimateGrade = scanner.nextBigDecimal();
        System.out.println("Enter the date of the grades:");
        System.out.println("YEAR/MONTH/DAY");
        int year = scanner.nextInt();
        int month = scanner.nextInt();
        int day = scanner.nextInt();

        System.out.println("ABSENT? Y/N");
        String yesOrNo = scanner.next().toUpperCase();
        if (yesOrNo == "Y") {
            grade.setAbsent(true);
        } else if (yesOrNo == "N") {
            grade.setAbsent(false);
        } else {
            grade.setAbsent(true);
        }

        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println("Enter comment about Grade:");
        String examComment = scanner.nextLine();
        System.out.println("Enter InternalComments about Grade:");
        String internalComment = scanner.nextLine();

        grade.setGrade(ultimateGrade);
        grade.setLocalDate(localDate);
        grade.setComment(examComment);
        grade.setInternalComment(internalComment);
    }

    private void updateGrades() {
        Grade grade = getGradeById();
        if (grade == null) {
            System.err.println("Grade doesn't exist.");
        } else {
            setGradeInfo(grade);
            gradeService.updateGrade(grade);
        }
    }

    private void deleteGrades() {
        Grade grade = getGradeById();
        gradeService.deleteGrade(grade);
    }


    private User getUserById() {
        String login = scanner.next();
        return userService.getUserById(login);
    }

    private Exam getExamById() {
        int id = scanner.nextInt();
        return examService.getExamById(id);
    }

    private Module getModuleById() {
        int id = scanner.nextInt();
        return moduleService.getModuleById(id);
    }

    private Course getCourseById() {
        int id = scanner.nextInt();
        return courseService.getCourseById(id);
    }

    private Person getPersonById() {
        int id = scanner.nextInt();
        return personService.getPersonById(id);
    }

    private Grade getGradeById() {
        Long id = scanner.nextLong();
        return gradeService.getGradeById(id);
    }
}


