package models;

import java.util.*;

public class AutoScheduler {
    private List<Semester> semesters;
    private List<Faculty> facultyList;
    private Timetable timetable;

    private String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri"};
    private String[] slots = {"9AM", "10AM", "11AM", "12PM", "2PM", "3PM"};

    public AutoScheduler(List<Semester> semesters, List<Faculty> facultyList) {
        this.semesters = semesters;
        this.facultyList = facultyList;
        this.timetable = new Timetable();
    }

    public void generate() {
        for (Semester sem : semesters) {
            for (Subject subject : sem.getSubjects()) {
                boolean scheduled = false;

                for (String day : days) {
                    for (String slot : slots) {
                        if (!timetable.isSlotFree(day, slot)) continue;

                        Faculty suitableFaculty = findFaculty(subject.getSubjectCode(), day);
                        if (suitableFaculty != null) {
                            // Assign subject
                            timetable.assignSlot(day, slot, subject.getSubjectCode() + " (" + suitableFaculty.getName() + ")");
                            suitableFaculty.assignLecture(day);
                            scheduled = true;
                            break;
                        }
                    }
                    if (scheduled) break;
                }

                if (!scheduled) {
                    System.out.println("Couldn't schedule subject: " + subject.getSubjectCode());
                }
            }
        }
    }

    private Faculty findFaculty(String subjectCode, String day) {
        for (Faculty f : facultyList) {
            if (f.canTeach(subjectCode) && f.canTeachMoreToday(day)) {
                return f;
            }
        }
        return null;
    }

    public void printSchedule() {
        timetable.printTimetable();
    }

    public Timetable getTimetable() {
        return timetable;
    }
}
