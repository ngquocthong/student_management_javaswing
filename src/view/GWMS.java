package view;

import com.toedter.calendar.JDateChooser;
import controller.StudentController;
import lib.XFile;
import model.Student;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GWMS extends JFrame {

    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField txtID;
    private JTextField txtName;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField6;
    private JButton findButton;
    private JTable table1;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JCheckBox ITCheckBox;
    private JCheckBox artsCheckBox;
    private JCheckBox businessCheckBox;
    private JCheckBox eventCheckBox;
    private JPanel JPDate;
    private JPanel leftSide;
    private JLabel lblHome;
    private JLabel lblManageStudent;
    private JLabel lblManageClass;
    private JPanel contentSide;
    private JPanel mStudentCard;
    private JPanel homeCard;
    private JTextField textField1;
    private JEditorPane editorPane1;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem newMenuItem;
    CardLayout cardLayout;
    JDateChooser dateChooser = new JDateChooser();
    String fPath;

    public GWMS(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(800, 500); // replace setsize
        cardLayout = (CardLayout) contentSide.getLayout();
        JPDate.add(dateChooser);
        openMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Open();
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            Save();
            }
        });
        saveAsMenuItem.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               SaveAs();
           }
        });
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                New();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> clubs = new ArrayList<String>();
                String id = txtID.getText();
                String name = txtName.getText();
                String className = "GCC0902"; //ComboBox
                String gender;
                if (maleRadioButton.isSelected()) {
                    gender = maleRadioButton.getActionCommand();
                } else {
                    gender = femaleRadioButton.getActionCommand();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dayOfBirth = sdf.format(dateChooser.getDate());
                if (ITCheckBox.isSelected()) clubs.add(ITCheckBox.getActionCommand());
                if (artsCheckBox.isSelected()) clubs.add(artsCheckBox.getActionCommand());
                if (businessCheckBox.isSelected()) clubs.add(businessCheckBox.getActionCommand());
                if (eventCheckBox.isSelected()) clubs.add(eventCheckBox.getActionCommand());
                Student student = new Student(id, name, className, dayOfBirth, clubs);
                StudentController.addToList(student);

                //String msg = String.format("Successfully \n Added model.Student:%s; Gender:%s; Attented class: %s Date Birth: %s", txtName.getText(), gender, clubs, dayOfBirth);
                //showMessage(msg);
                resetForm();

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uncheck();
            }
        });
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentSide,"HomeCard");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
        lblManageStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentSide,"ManageStudentCard");
            }
        });
        lblHome.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblMoved(lblHome);
            }
        });
        lblHome.addMouseMotionListener(new MouseMotionAdapter() {
        });
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblHome);
            }
        });
        lblManageStudent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblMoved(lblManageStudent);
            }
        });
        lblManageStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblManageStudent);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void uncheck() {
        ButtonGroup bg = maleRadioButton.getModel().getGroup();
        bg.clearSelection();
    }

    private void resetForm() {
        ITCheckBox.setSelected(false);
        artsCheckBox.setSelected(false);
        businessCheckBox.setSelected(false);
        eventCheckBox.setSelected(false);

//        ButtonGroup genderGroup = new ButtonGroup();
//        genderGroup.add(maleRadioButton);
//        genderGroup.add(femaleRadioButton);
//        genderGroup.clearSelection();

        txtName.setText("");
    }
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    private void lblMoved(JLabel label) {
        Color fore = new Color(255, 255, 255);
        Color bg = new Color(39, 98, 201);
        label.setForeground(fore);
        label.setBackground(bg);
        label.setOpaque(true);
    }
    private void lblExited(JLabel label) {
        Color fore = new Color(0, 0, 0);
        Color bg = new Color(250, 248, 248);
        label.setForeground(fore);
        label.setBackground(bg);
        label.setOpaque(true);
    }
    private void Open() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int result = fc.showOpenDialog(GWMS.this);
        if(result == JFileChooser.APPROVE_OPTION) {
            fPath = fc.getSelectedFile().getPath();
            String data = XFile.readBuffer(fPath);
            editorPane1.setText(data);
            cardLayout.show(contentSide,"HomeCard");
        }


    }
    private void Save() {
        StudentController.saveStudent();
        if(fPath == null) {
            SaveAs();
        } else {
            String data = editorPane1.getText();
            XFile.writeBuffer(fPath, data);

        }
    }
    private void SaveAs() {
        JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int result = fc.showSaveDialog(GWMS.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            fPath = fc.getSelectedFile().getPath();
            System.out.println(fPath);
            String data = editorPane1.getText();
            XFile.writeBuffer(fPath, data);
        }
    }
    private void New() {
        fPath = null;
        editorPane1.setText("");
    }

}
