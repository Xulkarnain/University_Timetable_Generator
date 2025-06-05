package Screens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import models.Classroom;
import models.Faculty;
import models.ScheduleEntry;
import models.Semester;
import models.Subject;
import models.TimeSlot;

public class CreateScheduleScreen extends Frame implements ActionListener {

    Choice subjectChoice, facultyChoice, roomChoice, dayChoice, timeChoice;
    Button btnSave, btnBack;

    public static ArrayList<ScheduleEntry> scheduleList = new ArrayList<>();

    public CreateScheduleScreen() {
        setTitle("Create Schedule");
        setSize(500, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        subjectChoice = new Choice();
        facultyChoice = new Choice();
        roomChoice = new Choice();
        dayChoice = new Choice();
        timeChoice = new Choice();

        for (Semester sem : AddSubjectScreen.semesters) {
            for (Subject s : sem.getSubjects()) {
                subjectChoice.add(s.getSubjectCode());
            }
        }   
        for (Faculty f : AddFacultyScreen.facultyList)
            facultyChoice.add(f.getName());
        for (Classroom c : AddClassroomScreen.classroomList)
            roomChoice.add(c.getRoomNumber());

        dayChoice.add("Monday"); dayChoice.add("Tuesday");
        dayChoice.add("Wednesday"); dayChoice.add("Thursday");
        dayChoice.add("Friday");

        timeChoice.add("9AM-10AM"); timeChoice.add("10AM-11AM");
        timeChoice.add("11AM-12PM"); timeChoice.add("2PM-3PM");
        timeChoice.add("3PM-4PM");

        btnSave = new Button("Save Entry");
        btnBack = new Button("Back");

        add(new Label("Subject")); add(subjectChoice);
        add(new Label("Faculty")); add(facultyChoice);
        add(new Label("Classroom")); add(roomChoice);
        add(new Label("Day")); add(dayChoice);
        add(new Label("Time")); add(timeChoice);
        add(btnSave); add(btnBack);

        btnSave.addActionListener(this);
        btnBack.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String subCode = subjectChoice.getSelectedItem();
            String facultyName = facultyChoice.getSelectedItem();
            String room = roomChoice.getSelectedItem();
            String day = dayChoice.getSelectedItem();
            String time = timeChoice.getSelectedItem();

            // Clash detection
        for (ScheduleEntry entry : scheduleList) {
            if (entry.getFaculty().getName().equals(facultyName) &&
                entry.getSlot().getDay().equals(day) &&
                entry.getSlot().getTime().equals(time)) {
                System.out.println("⚠️ Clash: Faculty already has a class at this time.");
                return;
        }

        if (entry.getClassroom().getRoomNumber().equals(room) &&
            entry.getSlot().getDay().equals(day) &&
            entry.getSlot().getTime().equals(time)) {
            System.out.println("⚠️ Clash: Room already booked at this time.");
            return;
        }
}


            // Find actual objects
            Subject selectedSub = AddSubjectScreen.subjectList.stream()
                .filter(s -> s.getSubjectCode().equals(subCode)).findFirst().get();
            Faculty selectedFac = AddFacultyScreen.facultyList.stream()
                .filter(f -> f.getName().equals(facultyName)).findFirst().get();
            Classroom selectedRoom = AddClassroomScreen.classroomList.stream()
                .filter(c -> c.getRoomNumber().equals(room)).findFirst().get();
            TimeSlot slot = new TimeSlot(day, time);

            ScheduleEntry newEntry = new ScheduleEntry(selectedSub, selectedFac, selectedRoom, slot);
            scheduleList.add(newEntry);
            System.out.println("✅ Schedule Added: " + subCode + " at " + day + " " + time);
        } else if (e.getSource() == btnBack) {
            this.setVisible(false);
            new HomeScreen();
        }
    }
}
