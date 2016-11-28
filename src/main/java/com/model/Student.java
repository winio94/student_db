package com.model;

public class Student {
    private String firstName;
    private String lastName;
    private String universityName;
    private int id = -1;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String universityName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.universityName = universityName;
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

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}