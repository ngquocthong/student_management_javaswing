package view;

import controller.GradeController;
import controller.SubjectController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GradeView extends JFrame {
    private JComboBox cbSubject;
    private JTextField txtGrade;
    private JButton submitButton;
    private JPanel mainPanel;
    private JTable tbGrade;
    private JLabel lblGrade_stName;
    DefaultComboBoxModel modelCombo;
    DefaultTableModel modelTable;

    private int index;
    public GradeView(String title, int indexStudent, String studentName) {
        super(title);
        this.index = index;
        this.setContentPane(mainPanel);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        lblGrade_stName.setText(studentName);

        modelCombo = (DefaultComboBoxModel) cbSubject.getModel();
        modelCombo.addAll( SubjectController.loadSubjects(0));
        tbGrade.setModel(new DefaultTableModel(
                new Object[][] {},
                new String[]{"ID", "Subject", "Grade"
                }
        ));
        modelTable = (DefaultTableModel) tbGrade.getModel();

        submitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexSubject = 0; // getSelectedIndex;
                GradeController.gradeStudent(5.6, indexStudent, indexSubject);
            }
        });
    }
}
