package Screens;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import Screens.AddSubjectScreen;
import models.Semester;
import models.Subject;

public class AddSubjectScreen extends Frame implements ActionListener {

    Label lblCode, lblName;
    TextField txtCode, txtName; 
    Choice semesterChoice;
    Button btnSave, btnBack;

    public static ArrayList<Semester> semesters = new ArrayList<>();
    public static ArrayList<Subject> subjectList = new ArrayList<>();





    public AddSubjectScreen() {
        setTitle("Add Subject");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));

        lblCode = new Label("Subject Code:");
        lblName = new Label("Subject Name:");
       

        txtCode = new TextField();
        txtName = new TextField();
        semesterChoice = new Choice();
        for (int i = 1; i <= 8; i++) {
            semesterChoice.add("Semester " + i);
        }

        btnSave = new Button("Save");
        btnBack = new Button("Back");

        add(lblCode);
        add(txtCode);
        add(lblName);
        add(txtName);
        add(new Label("Semester:")); 
        add(semesterChoice);
        add(btnSave);
        add(btnBack);

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
        String code = txtCode.getText();
        String name = txtName.getText();

        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("All fields are required.");
        } else {
            int sem = semesterChoice.getSelectedIndex() + 1;

            Subject s = new Subject(code, name, sem);
            Semester semester = getOrCreateSemester(sem);
            semester.addSubject(s);

            // ✅ Add to subjectList and update global subject list
            subjectList.add(s);
            HomeScreen.globalSubjectList = subjectList;

            System.out.println("Subject Added to Semester " + sem + ": " + code + " - " + name);

            txtCode.setText("");
            txtName.setText("");
        }
    } else if (e.getSource() == btnBack) {
        this.setVisible(false);
        new HomeScreen();
    }
}


    private Semester getOrCreateSemester(int semNumber) {
        for (Semester s : semesters) {
            if (s.getSemesterNumber() == semNumber) {
                return s;
            }
        }
        Semester newSem = new Semester(semNumber);
        semesters.add(newSem);
        return newSem;
    }

}
