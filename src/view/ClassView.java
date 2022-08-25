package view;

import controller.ClassController;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClassView extends JFrame {
    private JList classListView;
    private JPanel classPanel;
    private JTextField txtClassName;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;

    public ClassView(String title)  {
        super(title);
        this.setContentPane(classPanel);
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        ClassController.setListClass(classListView);


        classListView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ClassController.fillToForm(txtClassName, classListView.getSelectedIndex());
            }
        });


        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String className = txtClassName.getText();
                ClassController.addNewClass(className);
                ClassController.setListClass(classListView);
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = classListView.getSelectedIndex();
                String className = txtClassName.getText();
                if(index > -1) {
                    ClassController.updateClass(index, className);
                }
                ClassController.setListClass(classListView);
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = classListView.getSelectedIndex();
                if(index > -1) {
                    ClassController.deleteClass(index);
                }
                ClassController.setListClass(classListView);
            }
        });
    }
}
