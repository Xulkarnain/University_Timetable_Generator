package models;
public class Subject {
    String subjectCode;
    String subjectName;
    int semester;

    public Subject(String code, String name, int sem) {
        this.subjectCode = code;
        this.subjectName = name;
        this.semester = sem;
    }
    
    public String getSubjectCode() {
    return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getSemester() {
        return semester;
    }

}
