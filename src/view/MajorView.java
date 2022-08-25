package view;

import controller.MajorController;
import controller.StudentController;
import lib.XFile;
import model.Major;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MajorView extends JFrame {
    private JPanel majorJPane;
    private JTextField txtIDMajor;
    private JTextField txtNameMajor;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JList listMajorView;


    List<Major> listMajors;

    public MajorView(String title) {
        super(title);
        this.setContentPane(majorJPane);
        this.setSize(600, 300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MajorController.setMajorsList(listMajorView);


        //syncMajor();

        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MajorController.addNewMajor(txtIDMajor.getText(), txtNameMajor.getText(), listMajorView);
                //syncMajor();
            }
        });
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int majorIndex = listMajorView.getSelectedIndex();
                MajorController.updateMajor(txtIDMajor.getText(), txtNameMajor.getText(), majorIndex);
                MajorController.syncMajor(listMajorView);
            }
        });

        listMajorView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int majorIndex = listMajorView.getSelectedIndex();
                MajorController.setToTextField(txtIDMajor, txtNameMajor, majorIndex);
            }
        });
        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int majorIndex = listMajorView.getSelectedIndex();
                MajorController.deleteMajor(majorIndex);
                MajorController.syncMajor(listMajorView);
            }
        });


    }
}
