package view;

import controller.GradeController;
import controller.StudentController;
import controller.SubjectController;
import model.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class GradeView extends JFrame {
    private JComboBox cbSubject;
    private JTextField txtGrade;
    private JButton submitButton;
    private JPanel mainPanel;
    private JTable tbGrade;
    private JLabel lblGrade_stName;
    private JLabel lblStudentMajor;
    DefaultComboBoxModel modelCombo;

    List<Student> students;
    Student student;
    private int index;
    public GradeView(String title, int indexStudent) {
        super(title);
        this.index = indexStudent;
        this.setContentPane(mainPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setLocationRelativeTo(null);

        students = StudentController.readAllStudent();
        student = students.get(indexStudent);
        lblGrade_stName.setText(student.getName());
        lblStudentMajor.setText(student.getMajor().getName());

        modelCombo = (DefaultComboBoxModel) cbSubject.getModel();
        modelCombo.addAll(SubjectController.loadSubjects(student.getMajor()));

        tbGrade.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[]{"ID", "Subject", "Grade"
                }
        ));
        tbGrade.setDefaultEditor(Object.class, null);
        GradeController.setTable(tbGrade, indexStudent);

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Double grade = 0.0;
                int indexSubject = cbSubject.getSelectedIndex(); // getSelectedIndex;
                try {
                    grade = Double.valueOf(txtGrade.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Invalid grade number");
                }
                if(indexSubject > -1) {
                    GradeController.gradeStudent(grade, indexStudent, indexSubject);
                    GradeController.setTable(tbGrade, indexStudent);
                }
            }
        });

    }
}
