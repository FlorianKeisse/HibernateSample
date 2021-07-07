package com.demo.hibernate.school.model;

import javax.persistence.*;

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
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})

    private Course course;
    @Transient
    private Gender gender;

    public Person() {
    }

    public Person(String firstName, String lastName, Gender gender, Course course) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.course = course;

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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", course=" + course +
                '}';
    }
}
