package controller;

import lib.XFile;
import lib.XUtil;
import model.Classer;
import model.Major;
import model.Student;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentController {
    private static String fStudentPath = "students.dat";

    private static List<Student> studentList = new ArrayList<Student>();;
    private static DefaultComboBoxModel modelMajorCombo;

    private static List<Major> majorsList;

    static List<Student> students = new ArrayList<>();



    public static void saveStudent() {
        XFile.writeObject(fStudentPath, students);
        List<Student> newLst = (List<Student>) XFile.readObject(fStudentPath);
        for (Student o: newLst) {
            System.out.println(o.getName());
        }
    }

    public static String getfStudentPath() {
        return fStudentPath;
    }


    public static void setComboBoxMajor(JComboBox majorCombo) {
        if(modelMajorCombo!=null) modelMajorCombo.removeAllElements();
        modelMajorCombo = (DefaultComboBoxModel) majorCombo.getModel();
        majorsList = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
        //if(majorsList.size() == 0) {}
        for (Major m: majorsList) {
            modelMajorCombo.addElement(m.getName());
        }
    }

    public static void addNewStudent(String ID, String name, int indexMajor, int indexClass, List<String> clubs, String date, String imgUrl) {
        List<Classer> classesList = ClassController.readAllClass();
        List<Major> majorList = MajorController.readAllMajor();
        Student newStudent = new Student(ID, name, ID+"@fpt.edu.vn", majorList.get(indexMajor),clubs, date, imgUrl, classesList.get(indexClass).getName(), true);
        studentList.add(newStudent);
        ClassController.addStudentToClass(newStudent, indexClass);
        XFile.writeObject(fStudentPath, studentList);
    }



    public static void fillToTable(DefaultTableModel modelTable) {
        studentList = (List<Student>) XFile.readObject(fStudentPath);
        modelTable.setRowCount(0);
        for (Student s: studentList) {
            Object[] row = new Object[] {
                    s.getId(), s.getName(), s.getGender()?"Male":"Female", s.getEmail(), s.getMajor().getName(), s.getClassName(),
                    s.getClubs(), XUtil.convertDateToString(s.getDob())
            };
            modelTable.addRow(row);
        }
    }

    public static List<Student> getStudentList() {
        return studentList;
    }

    public static void editStudent(int row, String ID, String name, int indexMajor, int indexClass, List<String> clubs, String s) {




    }

    public static void delete(int row, int indexClass) {
        if(row > -1) {
            int re = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete:", "Delete",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(re == JOptionPane.YES_OPTION) {
                //remove he selected cadidate
                //fill to table
               // fillToTable(tbStudentView);
                //save file
                ClassController.studentIsRemoved(indexClass, studentList.get(row),0);
                studentList.remove(row);
                XFile.writeObject(fStudentPath, studentList);
                //clear form

            }
        }
    }




    public static String img(JLabel imgLabel) {
        String userDir = System.getProperty("user.dir");
        JFileChooser fc = new JFileChooser();
        String fileAbsPath = "";
        if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            fileAbsPath = f.getAbsolutePath();
        }
        return fileAbsPath;
    }
    public static void showImg(String fileAbsPath, JLabel imgLabel) {
        BufferedImage img = null;
        try { // DOC FILE VO
            // duong dan tuong doi ./ duong dan tuyet doi
            img = null;
            img = ImageIO.read(new File(fileAbsPath));
        } catch (IOException e) {
            System.err.print(e);
        }
        //DISPLAY
        Image i = img.getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(i);
        imgLabel.setIcon(icon);
    }

    public static void main(String[] args) {
        Major mj = new Major("IT", "Information Technology");
        Classer cla = new Classer("GCC0901");
        List<String> clubs = new ArrayList<String>();
        Student newStudent = new Student("GCC", "Thong", "@gmail.com", mj,clubs, "2002-01-01", "photos/default.png", "GCC0902", true);
        studentList.add(newStudent);
        try {
            XFile.writeObject(fStudentPath, studentList);
        } catch (Exception err) {
            System.err.println(err.getMessage());
        }
    }

    public static void fillForm(int row, JTextField txtStudentID, JTextField txtStudentName, JRadioButton maleRadioButton, JRadioButton femaleRadioButton, JDatePickerImpl jDatePickerImpl, JCheckBox itCheckBox, JCheckBox artsCheckBox, JCheckBox businessCheckBox, JCheckBox eventCheckBox, JComboBox studentMajorCompo, JComboBox studentClassCompo, JLabel imageLabel) {

        Student student = studentList.get(row);
        txtStudentID.setText(student.getId());
        txtStudentName.setText(student.getName());
        maleRadioButton.setSelected(student.getGender());
        femaleRadioButton.setSelected(!student.getGender());
        Date date = student.getDob();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        jDatePickerImpl.getJFormattedTextField().setValue(c);



        itCheckBox.setSelected(false);
        businessCheckBox.setSelected(false);
        artsCheckBox.setSelected(false);
        eventCheckBox.setSelected(false);
        for(String club: student.getClubs()) {
            if(club.equals(itCheckBox.getActionCommand())) itCheckBox.setSelected(true);
            if(club.equals(businessCheckBox.getActionCommand())) businessCheckBox.setSelected(true);
            if(club.equals(artsCheckBox.getActionCommand())) artsCheckBox.setSelected(true);
            if(club.equals(eventCheckBox.getActionCommand())) eventCheckBox.setSelected(true);
        }

        for(Classer classer: ClassController.readAllClass()){
            if(classer.getName().equals(student.getClassName())) {
                studentClassCompo.setSelectedItem(classer.getName());
            }
        }

        for(Major major : MajorController.readAllMajor()){
            if(major.getName().equals(student.getMajor().getName())) { // TAI SAO KHONG SO SANH 2 SUBJECT DUOC
                studentMajorCompo.setSelectedItem(major.getName());
            }
        }

        showImg(student.getImageUrl(), imageLabel);

    }



    public static List<Student> readAllStudent() {
        studentList = (List<Student>) XFile.readObject(fStudentPath);
        return studentList;
    }


    public static void updateStudent(int row, String name, Boolean gender, String date, int indexMajor, int indexClass, List<String> clubs, String imgUrl) {
        Student student = studentList.get(row);
        student.setName(name);
        student.setGender(gender);
        student.setDob(XUtil.convertStringToDate(date));
        student.setMajor(MajorController.readAllMajor().get(indexMajor));
        student.setClassName(ClassController.readAllClass().get(indexClass).getName());
        student.setClubs(clubs);
        student.setImageUrl(imgUrl);
        ClassController.studentIsRemoved(indexClass, student, 0);
        ClassController.addStudentToClass(student, indexClass);
        XFile.writeObject(fStudentPath, studentList);
    }

    public static boolean checkDuplicateStudent(String id) {
        for(Student s : readAllStudent()) {
            if(s.getId().equals(id)) return false;
        } return true;
    }
}
