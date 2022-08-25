package view;

import controller.ClassController;
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

import java.text.ParseException;
import java.util.*;
import java.util.List;


public class GWMS extends JFrame {
    private JPanel mainPanel;
    private JButton loginButton;
    private JTextField txtStudentID;
    private JTextField txtStudentName;
    private JButton addStudentButton;
    private JButton updateStudentButton;
    private JButton deleteStudentButton;
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
    private JEditorPane editorPane1;
    private JMenuItem openMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem newMenuItem;
    private JComboBox studentMajorCompo;
    private JTable tbStudentView;
    private JDatePickerImpl JDatePickerImpl;
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
    public JLabel user; // TẠI SAO KHÔNG CHO ĐẶT BIẾN LÀ STATIC ????
    private JPanel subjectcard;
    private JPanel homecard;
    private JPanel studentcard;
    private JPanel classcard;
    private JPanel photo;
    private JLabel imageLabel;
    private JButton uploadPictureButton;
    private JList lstStudentInClass;
    private JComboBox classesCombo;
    private JButton modifyClassButton;
    private JButton removeStudentButton;
    private JComboBox studentClassCompo;
    private JLabel lblNumOfStudent;

    CardLayout cardLayout;

    private int presentClicked = 0;
    private String imgUrl = "photos/default.png";


    DefaultComboBoxModel modelComboMajor;
    String fPath = "testStudent.dat";
    DefaultTableModel modelTable;
    List<Student> listStTable;
    List<Major> listMajors;
    List<String> clubs;

    DefaultListModel modelSubjects;
    List<Subject> listSubjects;
    List<String> card;
    List<JLabel> sideMenu;
    int row; // T
    int indexMajor = 0;
    public GWMS(String title, String userLogin) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(1500, 800);
        this.setLocationRelativeTo(null);

        user.setText(userLogin);

        //KHỞI TẠO GIÁ TRỊ CHO CÁC COMPONENTS -------------------------------------------------
        // BANG TABLE
        tbStudentView.setModel(new DefaultTableModel(
                new Object[] []{}, new String[] {
                "ID", "Name", "Gender", "Email", "Major", "Class", "Club", "Date"
        }
        ));
        modelTable = (DefaultTableModel) tbStudentView.getModel();
        tbStudentView.setDefaultEditor(Object.class, null);



        //SIDE MENU
        sideMenu = new ArrayList<JLabel>();
        sideMenu.add(lblHome);
        sideMenu.add(lblManageStudent);
        sideMenu.add(lblManageClass);
        sideMenu.add(lblManageSubject);

        //CARD
        card = new ArrayList<>();
        card.add("HomeCard");
        card.add("StudentCard");
        card.add("ClassCard");
        card.add("SubjectCard");
        cardLayout = (CardLayout) contentSide.getLayout();
        setContentPant();

        //SUBJECT LAYOUT
        MajorController.setComboBox(majorCombo);
        SubjectController.setSubjects(listSubjectsView, 0);


        //STUDENT
        //  TABLE STUDENT
        StudentController.fillToTable(modelTable);
        StudentController.setComboBoxMajor(studentMajorCompo);
//        fillToTable();
//        showDetail(row);


        //---------------------------------------------------------------------------------------------------------------------------------------------------
        // EVENT HANDLING
        // MENU ITEM
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

        //SIDE MENU EVENT
        lblHome.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblHover(lblHome);
            }
        });
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblHome);
            }
        });
        lblHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                presentClicked = 0;
                setContentPant();
            }

        });

        lblManageStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                presentClicked = 1;
                setContentPant();
                StudentController.setComboBoxMajor(studentMajorCompo); // update combobox
                ClassController.setComboBoxClass(studentClassCompo);// COMBO TRONG BẢNG MANAGE STUDENT
                StudentController.fillToTable(modelTable);
            }
        });
        lblManageStudent.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblHover(lblManageStudent);
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

                lblHover(lblManageClass);
            }
        });

        lblManageClass.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int indexC = classesCombo.getSelectedIndex();
                presentClicked = 2;
                setContentPant();
                ClassController.setComboBoxClass(classesCombo); // COMBO TRONG BẢNG MANAGE CLASS
                if (indexC > -1) {
                    ClassController.setListStudentInClass(lstStudentInClass, indexC);
                }
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
            public void mousePressed(MouseEvent e) {
                presentClicked = 3;
                setContentPant();

            }
        });



        //STUDENT
        addStudentButton.addActionListener(new ActionListener() { // Action Listener
            @Override
            public void actionPerformed(ActionEvent e) {
                DateLabelFormat getDate = new DateLabelFormat();
                try {
                    String id = txtStudentID.getText();
                    String name = txtStudentName.getText();

                    if(!id.equals("") && !name.equals("")) {
                        if (StudentController.checkDuplicateStudent(id)) {
                            indexMajor = studentMajorCompo.getSelectedIndex();
                            clubs = new ArrayList<>();
                            if (ITCheckBox.isSelected()) clubs.add(ITCheckBox.getActionCommand());
                            if (artsCheckBox.isSelected()) clubs.add(artsCheckBox.getActionCommand());
                            if (businessCheckBox.isSelected()) clubs.add(businessCheckBox.getActionCommand());
                            if (eventCheckBox.isSelected()) clubs.add(eventCheckBox.getActionCommand());
                            int indexClass = studentClassCompo.getSelectedIndex();
                            if(indexClass > -1 && indexMajor > -1) {
                                StudentController.addNewStudent(id, name, indexMajor, indexClass, clubs, getDate.valueToString(JDatePickerImpl.getJFormattedTextField().getValue()), imgUrl);
                                StudentController.fillToTable(modelTable);
                                clearForm();
                                StudentController.showImg("photos/default.png",imageLabel);
                            }
                        } else showMessage("ID already exists");
                    } else showMessage("ID or Name must be filled!");
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        gradeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = tbStudentView.getSelectedRow();
                if(row > -1) {
                    JFrame fr = new GradeView("Grade", row);
                    fr.setVisible(true);
                }
            }
        });



        //SUBJECT EVENT
        addSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexMajor = majorCombo.getSelectedIndex();
                SubjectController.addSubject(txtSubjectID.getText(),txtSubjectName.getText(), indexMajor);
                SubjectController.setSubjects(listSubjectsView, indexMajor); // CAP NHAT LAI list subject
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
                int indexSubject = listSubjectsView.getSelectedIndex();
                if (indexSubject != -1) {
                    SubjectController.updateSubject(txtSubjectID.getText(), txtSubjectName.getText(), indexMajor, indexSubject);
                    SubjectController.setSubjects(listSubjectsView, indexMajor);
                } else {
                    showMessage("Please choose a subject");
                }
            }
        });
        deleteSubjectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indexSubject = listSubjectsView.getSelectedIndex();

                if (indexSubject != -1) {
                    SubjectController.deleteSubject(indexMajor, indexSubject);
                    SubjectController.setSubjects(listSubjectsView, indexMajor);
                } else {
                    showMessage("Please choose a subject");
                }
            }
        });
        lblManageSubject.addMouseListener(new MouseAdapter() { } );
        lblManageSubject.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                lblHover(lblManageSubject);
            }
        });
        lblManageSubject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lblExited(lblManageSubject);
            }
        });



        //MAJOR EVENT
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

        tbStudentView.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                row = tbStudentView.getSelectedRow();
                if(row > -1) {
                    StudentController.fillForm(row, txtStudentID, txtStudentName,
                            maleRadioButton, femaleRadioButton, JDatePickerImpl, ITCheckBox,
                            artsCheckBox, businessCheckBox, eventCheckBox, studentMajorCompo, studentClassCompo, imageLabel);

                }
            }
        });
        deleteStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row = tbStudentView.getSelectedRow();
                if(row > -1) {
                    StudentController.delete(row, studentClassCompo.getSelectedIndex());
                    StudentController.fillToTable(modelTable);
                    clearForm();
                    StudentController.showImg("photos/default.png",imageLabel);
                }
            }
        });
        uploadPictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imgUrl = StudentController.img(imageLabel); // LAY DUONG DAN
                StudentController.showImg(imgUrl, imageLabel);
            }
        });
        modifyClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame fr = new ClassView("Class Management");
                fr.setVisible(true);
            }
        });
        classesCombo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int indexCombo = classesCombo.getSelectedIndex();
                if(indexCombo > -1) {
                    ClassController.setListStudentInClass(lstStudentInClass, indexCombo);
                    lblNumOfStudent.setText(ClassController.getListStudentInClassList().size() + "");
                }
            }
        });
        updateStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtStudentName.getText();
                indexMajor = studentMajorCompo.getSelectedIndex();
                clubs = new ArrayList<>();
                String date = XUtil.convertDateToString((Date) JDatePickerImpl.getModel().getValue());
                if (ITCheckBox.isSelected()) clubs.add(ITCheckBox.getActionCommand());
                if (artsCheckBox.isSelected()) clubs.add(artsCheckBox.getActionCommand());
                if (businessCheckBox.isSelected()) clubs.add(businessCheckBox.getActionCommand());
                if (eventCheckBox.isSelected()) clubs.add(eventCheckBox.getActionCommand());
                int indexClass = studentClassCompo.getSelectedIndex();
                Boolean gender = maleRadioButton.isSelected()? true : false;
                if (row > -1 && indexClass > -1 && indexMajor > -1) {
                    StudentController.updateStudent(row, name, gender, date, indexMajor, indexClass, clubs, imgUrl);
                    StudentController.fillToTable(modelTable);
                    clearForm();
                    StudentController.showImg("photos/default.png",imageLabel);
                }
            }
        });


        classesCombo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ClassController.setComboBoxClass(classesCombo);
            }
        });
        removeStudentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int indexStudent = lstStudentInClass.getSelectedIndex();
                int indexClass = classesCombo.getSelectedIndex();
                if (indexStudent > -1) {
                    Student student = ClassController.readAllClass().get(indexClass).getStudentEnrolled().get(indexStudent);
                    ClassController.studentIsRemoved(indexClass, student, 1);
                    ClassController.setListStudentInClass(lstStudentInClass, indexClass);
                }
            }
        });
    }

    private void clearForm() {
        ITCheckBox.setSelected(false);
        artsCheckBox.setSelected(false);
        businessCheckBox.setSelected(false);
        eventCheckBox.setSelected(false);
        txtStudentID.setText("");
        txtStudentName.setText("");
        studentMajorCompo.setSelectedIndex(0);
        txtStudentID.enable(true);
    }




    //FUNCTION -------------------------------------------------
    //  STUDENT

    private void uncheck() {
        ButtonGroup bg = maleRadioButton.getModel().getGroup();
        bg.clearSelection();
    }

    private void createUIComponents() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl = new JDatePickerImpl(datePanel, new DateLabelFormat());
    }

    //LAYOUT
    private void lblClicked(JLabel label) {
        lblDefaultAll(sideMenu);
        Color fore = new Color(39, 98, 201);
        Color bg = new Color(255, 255, 255);
        label.setForeground(fore);
        label.setBackground(bg);
        label.setOpaque(true);
    }
    private void lblHover(JLabel label) {
        if(label != sideMenu.get(presentClicked)) {
            Color fore = new Color(255, 255, 255);
            Color bg = new Color(77, 125, 255);
            label.setForeground(fore);
            label.setBackground(bg);
            label.setOpaque(true);
        }

    }
    private void lblExited(JLabel label) {
        if(label != sideMenu.get(presentClicked)) {
            Color fore = new Color(255, 255, 255);
            Color bg = new Color(39, 98, 201);
            label.setForeground(fore);
            label.setBackground(bg);
            label.setOpaque(true);
        }
    }
    private void lblDefaultAll(List<JLabel> labels) {
        for(JLabel label : labels) {
            Color fore = new Color(255, 255, 255);
            Color bg = new Color(39, 98, 201);
            label.setForeground(fore);
            label.setBackground(bg);
            label.setOpaque(true);
        }
    }
    private void setContentPant() {
        cardLayout.show(contentSide, card.get(presentClicked));
        lblClicked(sideMenu.get(presentClicked));
    }


    //ITEM MENU
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
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
}
