package Screens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import models.Classroom;


public class AddClassroomScreen extends Frame implements ActionListener {

    Label lblRoom, lblCap;
    TextField txtRoom, txtCap;
    Button btnSave, btnBack;

    public static ArrayList<Classroom> classroomList = new ArrayList<>();

    public AddClassroomScreen() {
        setTitle("Add Classroom");
        setSize(400, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        lblRoom = new Label("Room Number:");
        lblCap = new Label("Capacity:");

        txtRoom = new TextField();
        txtCap = new TextField();

        btnSave = new Button("Save");
        btnBack = new Button("Back");

        add(lblRoom); add(txtRoom);
        add(lblCap); add(txtCap);
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
            String room = txtRoom.getText();
            String capText = txtCap.getText();

            if (room.isEmpty() || capText.isEmpty()) {
                System.out.println("All fields are required.");
            } else {
                try {
                    int cap = Integer.parseInt(capText);
                    Classroom c = new Classroom(room, cap);
                    classroomList.add(c);
                    System.out.println("Classroom Added: " + room + " - " + cap);
                    txtRoom.setText("");
                    txtCap.setText("");
                } catch (NumberFormatException ex) {
                    System.out.println("Capacity must be a number.");
                }
            }
        } else if (e.getSource() == btnBack) {
            this.setVisible(false);
            new HomeScreen();
        }
    }
}
