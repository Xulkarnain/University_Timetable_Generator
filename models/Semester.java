package models;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private int semesterNumber;
    private List<Subject> subjects;

    public Semester(int semesterNumber) {
        this.semesterNumber = semesterNumber;
        this.subjects = new ArrayList<>();
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
}
