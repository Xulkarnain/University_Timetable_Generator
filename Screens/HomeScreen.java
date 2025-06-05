package Screens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import models.AutoScheduler;
import models.Subject;

public class HomeScreen extends Frame implements ActionListener {

    Button btnAddSubject, btnAddFaculty, btnAddRoom, btnCreateSchedule, btnViewTimetable, btnAutoSchedule;

    // Global subject list accessible across screens
    public static ArrayList<Subject> globalSubjectList = new ArrayList<>();

    public HomeScreen() {
        setTitle("Timetable Management System");
        setSize(400, 350);
        setLayout(new GridLayout(6, 1, 10, 10));

        btnAddSubject = new Button("Add Subject");
        btnAddFaculty = new Button("Add Faculty");
        btnAddRoom = new Button("Add Classroom");
        btnCreateSchedule = new Button("Create Schedule");
        btnViewTimetable = new Button("View Timetable");
        btnAutoSchedule = new Button("Auto Generate Timetable");

        add(btnAutoSchedule);
        add(btnAddSubject);
        add(btnAddFaculty);
        add(btnAddRoom);
        add(btnCreateSchedule);
        add(btnViewTimetable);

        btnAddSubject.addActionListener(this);
        btnAddFaculty.addActionListener(this);
        btnAddRoom.addActionListener(this);
        btnCreateSchedule.addActionListener(this);
        btnViewTimetable.addActionListener(this);
        btnAutoSchedule.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddSubject) {
            this.setVisible(false);
            new AddSubjectScreen(); // This screen will update globalSubjectList
        } else if (e.getSource() == btnAddFaculty) {
            this.setVisible(false);
            new AddFacultyScreen(globalSubjectList); // Pass latest subject list
        } else if (e.getSource() == btnAddRoom) {
            this.setVisible(false);
            new AddClassroomScreen();
        } else if (e.getSource() == btnCreateSchedule) {
            this.setVisible(false);
            new CreateScheduleScreen();
        } else if (e.getSource() == btnViewTimetable) {
            this.setVisible(false);
            new ViewTimetableScreen();
        } else if (e.getSource() == btnAutoSchedule) {
            // Trigger auto scheduling
            AutoScheduler scheduler = new AutoScheduler(
                AddSubjectScreen.semesters,
                AddFacultyScreen.facultyList
            );
            scheduler.generate();
            scheduler.printSchedule(); // For debug

            new AutoCreateScheduleScreen(scheduler.getTimetable());
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new HomeScreen();
    }
}
