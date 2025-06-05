package models;

public class ScheduleEntry {
    Subject subject;
    Faculty faculty;
    Classroom classroom;
    TimeSlot slot;

    public ScheduleEntry(Subject subject, Faculty faculty, Classroom room, TimeSlot slot) {
        this.subject = subject;
        this.faculty = faculty;
        this.classroom = room;
        this.slot = slot;
    }
    public Subject getSubject() { return subject; }
    public Faculty getFaculty() { return faculty; }
    public Classroom getClassroom() { return classroom; }
    public TimeSlot getSlot() { return slot; }

}
