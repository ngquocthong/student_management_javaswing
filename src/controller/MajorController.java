package controller;

import lib.XFile;
import model.Major;
import model.Subject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MajorController {
    private static DefaultComboBoxModel modelComboMajor;
    private static DefaultListModel modelMajorsList = new DefaultListModel();
    private static String fMajorPath = "majors.dat";
    private static List<Major> listMajors;

    public static String getfMajorPath() {
        return fMajorPath;
    }

    public static void main(String[] args) {
        List<Subject> subjectsComputer = new ArrayList<Subject>();
        Subject s1 = new Subject("Prog", "C Programming Language");
        Subject s2 = new Subject("Web", "Project Web");
        Subject s3 = new Subject("Soft", "Soft Skills");
        subjectsComputer.add(s1);
        subjectsComputer.add(s2);
        subjectsComputer.add(s3);
        Major mj1 = new Major("SE","Information Technology", subjectsComputer);


        List<Subject> subjectsBussiness = new ArrayList<Subject>();
        Subject s4 = new Subject("CA", "Caculating");
        Subject s5 = new Subject("SR", "Social relations");
        Subject s6 = new Subject("ME", "Money Management");
        subjectsBussiness.add(s4);
        subjectsBussiness.add(s5);
        subjectsBussiness.add(s6);


        Major mj2 = new Major("BA-BM","Business Management", subjectsBussiness);


        List<Major> majors = new ArrayList<Major>();
        majors.add(mj1);
        majors.add(mj2);

        XFile.writeObject(fMajorPath, majors);

        for(Major m : (List<Major>) XFile.readObject(fMajorPath)) {
            System.out.println(m.getID() + " " + m.getName() + " " + m.getSubjects());
        }


    }

    public static void setComboBox(JComboBox majorCombo) {
        //listSubjects = listMajors.get(0).getSubjects(); // LIST CÁC MÔN TRONG NGÀNH | DEFAULT IS  0
        readAllMajor();
        modelComboMajor = (DefaultComboBoxModel) majorCombo.getModel();
            for (Major m: listMajors) {
                modelComboMajor.addElement(m.getName());
       }
    }

    public static void addNewMajor(String ID, String name, JList listMajorView) { // LÀM SAO ĐỂ CÂP NHẬT LẠI KHI THOÁT CỬA SỔ
        Major newMajor = new Major(ID, name);
        listMajors.add(newMajor);
        XFile.writeObject(MajorController.getfMajorPath(), listMajors);
        syncMajor(listMajorView);
    }
    public static void syncMajor(JList listMajorView) {
        modelMajorsList.removeAllElements(); //CLEAR DATA
        listMajors = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
        for (Major major : listMajors) {
            modelMajorsList.addElement(major.toString());
        }
        listMajorView.setModel(modelMajorsList);
    }

    public static void setMajorsList(JList listMajorView) {
        syncMajor(listMajorView);
    }

    public static void updateMajor(String ID, String name, int majorIndex) {
        listMajors.get(majorIndex).setID(ID);
        listMajors.get(majorIndex).setName(name);
        XFile.writeObject(fMajorPath, listMajors);
    }


    public static void setToTextField(JTextField txtIDMajor, JTextField txtNameMajor, int majorIndex) {
        txtIDMajor.setText(listMajors.get(majorIndex).getID());
        txtNameMajor.setText(listMajors.get(majorIndex).getName());
    }

    public static List<Major> getListMajors() {
        return listMajors;
    }

    public static List<Major> getAllFile() {
        return (List<Major>) XFile.readObject(fMajorPath);
    }

    public static void deleteMajor(int majorIndex) {
        listMajors.remove(majorIndex);
        XFile.writeObject(fMajorPath, listMajors);
    }

    public static List<Major> readAllMajor() {
        listMajors = (List<Major>) XFile.readObject(fMajorPath);
        return listMajors;
    }


}
