package com.model;

/**
 * Created by Michał on 2017-02-05.
 */
public final class StudentBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String universityName;

    private StudentBuilder() {
    }

    public static StudentBuilder get() {
        return new StudentBuilder();
    }

    public StudentBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public StudentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilder withUniversityName(String universityName) {
        this.universityName = universityName;
        return this;
    }

    public static Student getStudentWith(String firstName, String lastName, String universityName) {
        return get().withFirstName(firstName)
                    .withLastName(lastName)
                    .withUniversityName(universityName)
                    .build();
    }

    public Student build() {
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUniversityName(universityName);
        return student;
    }
}