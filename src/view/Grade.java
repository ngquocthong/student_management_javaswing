package view;

import controller.GradeController;
import controller.SubjectController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Grade extends JFrame {
    private JComboBox cbSubject;
    private JTextField txtGrade;
    private JButton submitButton;
    private JPanel mainPanel;
    private JTable tbGrade;
    private JLabel lblGrade_stName;
    DefaultComboBoxModel modelCombo;
    DefaultTableModel modelTable;

    private int index;
    public Grade(String title, int index, String studentName) {
        super(title);
        this.index = index;
        this.setContentPane(mainPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        lblGrade_stName.setText(studentName);

        modelCombo = (DefaultComboBoxModel) cbSubject.getModel();
        modelCombo.addAll( SubjectController.loadSubjects());
        tbGrade.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[]{"ID", "Subject", "Grade"
                }
        ));
        modelTable = (DefaultTableModel) tbGrade.getModel();

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GradeController.gradeStudent(5.6, index);
            }
        });
    }
}
