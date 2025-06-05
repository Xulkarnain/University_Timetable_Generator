package Screens;

import java.awt.*;
import java.awt.event.*;

import models.ScheduleEntry;
import Screens.ViewTimetableScreen;


public class ViewTimetableScreen extends Frame implements ActionListener {

    Choice semesterChoice;
    TextArea display;
    Button btnBack, btnShow;

    public ViewTimetableScreen() {
        setTitle("Timetable Viewer");
        setSize(600, 500);
        setLayout(new BorderLayout());

        // Top Panel for semester selection
        Panel topPanel = new Panel(new FlowLayout());
        topPanel.add(new Label("Select Semester:"));

        semesterChoice = new Choice();
        for (int i = 1; i <= 8; i++) {
            semesterChoice.add("Semester " + i);
        }

        btnShow = new Button("Show Timetable");
        btnShow.addActionListener(this);

        topPanel.add(semesterChoice);
        topPanel.add(btnShow);
        add(topPanel, BorderLayout.NORTH);

        // Text Area to show timetable
        display = new TextArea();
        display.setEditable(false);
        add(display, BorderLayout.CENTER);

        // Bottom Back Button
        btnBack = new Button("Back");
        btnBack.addActionListener(e -> {
            this.setVisible(false);
            new HomeScreen();
        });

        add(btnBack, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnShow) {
            int selectedSem = semesterChoice.getSelectedIndex() + 1;

            StringBuilder sb = new StringBuilder();
            for (ScheduleEntry entry : CreateScheduleScreen.scheduleList) {
                if (entry.getSubject().getSemester() == selectedSem) {
                    sb.append("[").append(entry.getSlot().getDay()).append(" - ").append(entry.getSlot().getTime()).append("]\n")
                      .append("Subject: ").append(entry.getSubject().getSubjectName())
                      .append(" (").append(entry.getSubject().getSubjectCode()).append(")\n")
                      .append("Faculty: ").append(entry.getFaculty().getName()).append("\n")
                      .append("Room: ").append(entry.getClassroom().getRoomNumber()).append("\n\n");
                }
            }

            if (sb.length() == 0) {
                sb.append(" No classes scheduled for Semester ").append(selectedSem);
            }

            display.setText(sb.toString());
        }
    }
}
