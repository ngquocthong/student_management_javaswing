package view;

import controller.MajorController;
import controller.StudentController;
import controller.SubjectController;
import lib.XFile;
import lib.XUtil;
import model.Major;
import model.Student;
import model.Subject;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

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
    private JPanel Student;
    private JPanel Home;
    private JEditorPane editorPane1;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem newMenuItem;
    private JComboBox comboBox3;
    private JTable tbStudent;
    private JDatePickerImpl JDatePickerImpl2;
    private JPanel SubjectCard;
    private JList listSubjectsView;
    private JButton addSubjectButton;
    private JButton updateSubjectButton;
    private JButton deleteSubjectButton;
    private JTextField txtSubjectID;
    private JTextField txtSubjectName;
    private JLabel lblManageSubject;
    private JButton gradeButton;
    private JComboBox majorCombo;
    private JButton modifyMajorButton;
    public JLabel stateLabel; // TẠI SAO KHÔNG CHO ĐẶT BIẾN LÀ STATIC ????
    CardLayout cardLayout;

    DefaultComboBoxModel modelComboMajor;
    String fPath = "testStudent.dat";
    DefaultTableModel modelTable;
    List<Student> listStTable;
    List<Major> listMajors;

    DefaultListModel modelSubjects;
    List<Subject> listSubjects;

    int row; // sự kiện người ta click vào trong table || trong giao diện
    int indexMajor = 0;
    public GWMS(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);

        //KHỞI TẠO GIÁ TRỊ CHO CÁC COMPONENTS
        MajorController.setComboBox(majorCombo);
        SubjectController.setSubjects(listSubjectsView, 0);

        StudentController.setTable(tbStudent);

        // SỰ KIỆN

        modelTable = (DefaultTableModel) tbStudent.getModel();
        listStTable = (List<Student>) XFile.readObject(fPath);
        if(listStTable.size() == 0) {
            //(String id, String name, String email, String className, Double spring, Double summer, Double fall, List<String> clubs, String dob4
            List<String> clubs = new ArrayList<String>();
            listStTable.add(new Student("001", "Thong", "@email.com", "GCC0902", clubs, "2022-01-01"));
            XFile.writeObject(StudentController.getfStudentPath(), listStTable);
        }
        // fill list to JTable
        fillToTable();
        //show all to form
        showDetail(row);

        cardLayout = (CardLayout) contentSide.getLayout();
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
        addButton.addActionListener(new ActionListener() { // Action Listener
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

                if (ITCheckBox.isSelected()) clubs.add(ITCheckBox.getActionCommand());
                if (artsCheckBox.isSelected()) clubs.add(artsCheckBox.getActionCommand());
                if (businessCheckBox.isSelected()) clubs.add(businessCheckBox.getActionCommand());
                if (eventCheckBox.isSelected()) clubs.add(eventCheckBox.getActionCommand());
                //Student student = new Student(id, name, className, dayOfBirth, clubs);
                //StudentController.addToList(student);

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

        });
        lblHome.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblMoved(lblHome);
            }
        });
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblHome);
            }
        });

        lblManageStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentSide,"ManageStudentCard");
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


        lblManageClass.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblMoved(lblManageClass);
            }
        });

        lblManageClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        lblManageClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblManageClass);
            }
        });
        lblManageSubject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contentSide,"SubjectCard");
            }
        });
        addSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexMajor = majorCombo.getSelectedIndex();
                SubjectController.addSubject(txtSubjectID.getText(),txtSubjectName.getText(), indexMajor);
                SubjectController.setSubjects(listSubjectsView, indexMajor); // CAP NHAT LAI BAN
            }
        });
        listSubjectsView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int indexSubject = listSubjectsView.getSelectedIndex();
                SubjectController.setToTextField(txtSubjectID, txtSubjectName, indexSubject);
            }
        });
        updateSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                int index = listSubjectsView.getSelectedIndex();
//                if (index != -1) {
//                    listSubjects.get(index).setName(txtSubjectName.getText());
//                    listSubjects.get(index).setID(txtSubjectID.getText());
//                    SubjectController.updateSubject(listSubjects);
//                    syncSubjects();
//                } else {
//                    showMessage("Please choose a subject");
//                }
            }
        });
        deleteSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                int index = listSubjectsView.getSelectedIndex();
//                if (index != -1) {
//                    listSubjects.remove(index);
//                    SubjectController.updateSubject(listSubjects);
//                    syncSubjects();
//                } else {
//                    showMessage("Please choose a subject");
//                }
            }
        });
        gradeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame fr = new GradeView("Grade", 0, (String) tbStudent.getValueAt(row, 1));
                fr.setVisible(true);
            }
        });
        modifyMajorButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //GO to Major Panel
                JFrame fr = new MajorView("Major");
                fr.setVisible(true);
            }
        });
        majorCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                indexMajor = majorCombo.getSelectedIndex();
                SubjectController.setSubjects(listSubjectsView, indexMajor);
            }
        });
    }

    private void syncSubjects() {
        //listSubjects.clear();
        modelSubjects.removeAllElements();
        for (Subject sub : SubjectController.loadSubjects(indexMajor)) {
            listSubjects.add(sub);
            modelSubjects.addElement(sub.toString());
        }
        listSubjectsView.setModel(modelSubjects);
    }


    private void fillToTable() {
        modelTable.setRowCount(0);
        //(String id, String name, String email, String className, Double spring, Double summer, Double fall, List<String> clubs, String dob4
        for (Student s: listStTable) {
            Object[] row = new Object[] {
                    s.getId(), s.getName(), s.getEmail(), s.getClassName(),
                    s.getClubs(), XUtil.convertDateToString(s.getDob())
            };
            modelTable.addRow(row);
        }

    }
    private void showDetail(int row) {
        String sID = (String) tbStudent.getValueAt(row, 0);
        txtID.setText(sID);
        String name = (String) tbStudent.getValueAt(row, 1);
        txtName.setText(name);
        Boolean gender = true;//(Boolean) tbStudent.getValueAt(row, true);
        maleRadioButton.setSelected(!gender);
        femaleRadioButton.setSelected(gender);
        String bDay = (String) tbStudent.getValueAt(row, 5);

        Calendar c = Calendar.getInstance();
        c.setTime(XUtil.convertStringToDate(bDay));
        JDatePickerImpl2.getJFormattedTextField().setValue(c);

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
        Color bg = new Color(255, 255, 255);
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



    private void createUIComponents() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl2 = new JDatePickerImpl(datePanel, new DateLabelFormat());
    }

}
