package com.demo.hibernate.school.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Person {
    public enum Gender {
        MALE("MALE"),
        FEMALE("FEMALE");

        private String gender;

        Gender(String gender) {
            this.gender = gender;
        }

        @Override
        public String toString() {
            return this.gender;
        }
    }

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstName;
    private String lastName;
    @ManyToOne(cascade = CascadeType.ALL)
    private Course courseActive;
    @Transient
    private Gender gender;

    @ManyToMany(targetEntity = Course.class,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.PERSIST,
                    CascadeType.REFRESH})
    private List<Course> courseHistory;

    public Person() {
    }

    public Person(Integer id, String firstName, String lastName, Course courseActive, Gender gender, List<Course> courseHistory) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseActive = courseActive;
        this.gender = gender;
        this.courseHistory = courseHistory;
    }

    public Course getCourseActive() {
        return courseActive;
    }

    public void setCourseActive(Course courseActive) {
        this.courseActive = courseActive;
    }

    public List<Course> getCourseHistory() {
        return courseHistory;
    }

    public void setCourseHistory(List<Course> courseHistory) {
        this.courseHistory = courseHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseActive=" + courseActive +
                ", gender=" + gender +
                ", courseHistory=" + courseHistory +
                '}';
    }
}
