package controller;

import lib.XFile;
import model.Classer;
import model.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ClassController {
    private static DefaultComboBoxModel modelClassCombo;
    private static DefaultListModel modelClassList;
    private static DefaultListModel modelStudentInClassList;
    private static String fClassPath = "classes.dat";
    private static List<Classer> classesList = new ArrayList<Classer>();
    private static List<Student> listStudentInClassList = new ArrayList<Student>();

    private static int test = 0;


    public static List<Classer> readAllClass() {
        classesList = (List<Classer>) XFile.readObject(fClassPath);
        return classesList;
    }

    public static void addStudentToClass(Student student, int indexClass) {
        readAllClass();
        List<Student> students;
        if (classesList.get(indexClass).getStudentEnrolled() != null) {
            students = classesList.get(indexClass).getStudentEnrolled();
        } else students = new ArrayList<Student>();
        students.add(student);
        classesList.get(indexClass).setStudentEnrolled(students);
        XFile.writeObject(fClassPath, classesList);
    }

    public static void setComboBoxClass(JComboBox studentClassCompo) {
        readAllClass();
        if (modelClassCombo != null) modelClassCombo.removeAllElements(); // RESET COMBO BOX
        modelClassCombo = (DefaultComboBoxModel) studentClassCompo.getModel();
        for (Classer c : classesList) {
            modelClassCombo.addElement(c.getName());
        }
    }

    public static void main(String[] args) {
        Classer c1 = new Classer("GCC0901");
        Classer c2 = new Classer("GCC0902");
        Classer c3 = new Classer("GCC0903");
        ClassController.classesList.add(c1);
        ClassController.classesList.add(c2);
        ClassController.classesList.add(c3);
        XFile.writeObject(fClassPath, classesList);

    }

    public static void setListClass(JList classList) {
        readAllClass();
        if (modelClassList == null) modelClassList = new DefaultListModel();
        else modelClassList.removeAllElements();
        for (Classer c : classesList) {
            modelClassList.addElement(c.getName());
        }
        classList.setModel(modelClassList);
    }

    public static void fillToForm(JTextField txtClassName, int selectedIndex) {
        txtClassName.setText(classesList.get(selectedIndex).getName());
    }

    public static List<Student> getListStudentInClassList() {
        return listStudentInClassList;
    }

    public static void setListStudentInClass(JList lstStudentInClass, int index) {
        readAllClass();
        listStudentInClassList = classesList.get(index).getStudentEnrolled();
        if (modelStudentInClassList == null) {
            modelStudentInClassList = new DefaultListModel();
        } else modelStudentInClassList.removeAllElements();
        int count = 1;
        for (Student st : listStudentInClassList) {
            modelStudentInClassList.addElement(count++ + ". " + st.getName());
        }
        lstStudentInClass.setModel(modelStudentInClassList);
    }

    public static void studentIsRemoved(int indexClass, Student studentToRemove, int type) {
        int index = -1;
        // type 1 is remove the class name in the Student
        List<Student> students = classesList.get(indexClass).getStudentEnrolled(); // Remove from student panel to class panel
        for (Student st : students) {
            index++;
            if (st.getId().equals(studentToRemove.getId())) {
                break;
            }
        }
        if (index > -1) {
            students.remove(index);
        }
        classesList.get(indexClass).setStudentEnrolled(students);
        XFile.writeObject(fClassPath, classesList);
        if (type == 1) {
            students = StudentController.getStudentList();
            for (Student st : students) {
                if (st.getId().equals(studentToRemove.getId())) {
                    st.setClassName("");
                }
            }
            XFile.writeObject(StudentController.getfStudentPath(), students);
        }
    }

    public static void addNewClass(String className) {
        Classer newClass = new Classer(className);
        classesList.add(newClass);
        XFile.writeObject(fClassPath, classesList);
    }

    public static void updateClass(int index, String newName) {
        Classer cla = classesList.get(index);
        cla.setName(newName);
        XFile.writeObject(fClassPath, classesList);
    }

    public static void deleteClass(int index) {
        classesList.remove(index);
        XFile.writeObject(fClassPath, classesList);
    }
}