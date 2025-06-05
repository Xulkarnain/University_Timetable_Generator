package Screens;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import models.Faculty;
import models.Subject;

public class AddFacultyScreen extends Frame implements ActionListener {

    Label lblName, lblDept, lblSubjects, lblMaxLectures;
    TextField txtName, txtDept, txtMaxLectures;
    java.awt.List subjectListUI;  // Using java.awt.List to avoid java.util.List confusion
    Button btnSave, btnBack;

    public static ArrayList<Faculty> facultyList = new ArrayList<>();

    public AddFacultyScreen(ArrayList<Subject> subjects) {
        setTitle("Add Faculty");
        setSize(500, 400);
        setLayout(new GridLayout(6, 2, 10, 10));

        lblName = new Label("Faculty Name:");
        lblDept = new Label("Department:");
        lblSubjects = new Label("Select Subjects:");
        lblMaxLectures = new Label("Max Lectures/Day:");

        txtName = new TextField();
        txtDept = new TextField();
        txtMaxLectures = new TextField();

        // Enable multi-select with 5 visible rows
        subjectListUI = new java.awt.List(5, true);
        for (Subject s : subjects) {
            subjectListUI.add(s.getSubjectCode() + " - " + s.getSubjectName());
        }

        btnSave = new Button("Save");
        btnBack = new Button("Back");

        add(lblName); add(txtName);
        add(lblDept); add(txtDept);
        add(lblSubjects); add(subjectListUI);
        add(lblMaxLectures); add(txtMaxLectures);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            String name = txtName.getText().trim();
            String dept = txtDept.getText().trim();
            String maxLecturesInput = txtMaxLectures.getText().trim();

            // Use subjectListUI here, not subjectListBox
            if (name.isEmpty() || dept.isEmpty() || maxLecturesInput.isEmpty() || subjectListUI.getSelectedItems().length == 0) {
                System.out.println("Please fill all fields and select subjects.");
                return;
            }

            int maxLectures;
            try {
                maxLectures = Integer.parseInt(maxLecturesInput);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid number for max lectures.");
                return;
            }

            List<String> subjectCodes = new ArrayList<>();
            for (String item : subjectListUI.getSelectedItems()) {
                // item example: "CS101 - Data Structures"
                String code = item.split("-")[0].trim();  // Get subject code only
                subjectCodes.add(code);
            }

            Faculty f = new Faculty(name, subjectCodes, maxLectures);
            facultyList.add(f);

            System.out.println("Faculty Added: " + name + " Subjects: " + subjectCodes + " Max Lectures: " + maxLectures);

            // Clear inputs
            txtName.setText("");
            txtDept.setText("");
            txtMaxLectures.setText("");

            // Deselect all subjects
            for (int i = 0; i < subjectListUI.getItemCount(); i++) {
                subjectListUI.deselect(i);
            }
        } else if (e.getSource() == btnBack) {
            this.setVisible(false);
            new HomeScreen();
        }
    }
}
