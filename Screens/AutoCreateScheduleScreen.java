package Screens;

import java.awt.*;
import java.awt.event.*;
import models.Timetable;

public class AutoCreateScheduleScreen extends Frame {

    public AutoCreateScheduleScreen(Timetable timetable) {
        setTitle("Auto Generated Timetable");
        setSize(600, 400);
        setLayout(new BorderLayout());

        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setText(timetable.toString()); // toString() should return formatted timetable
        add(outputArea, BorderLayout.CENTER);

        Button btnBack = new Button("Back");
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
}
