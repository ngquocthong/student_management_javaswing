package controller;

import lib.XFile;
import model.Major;
import model.Subject;
import view.GWMS;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubjectController { 

    private static DefaultListModel modelSubjectsList = new DefaultListModel();
    private static List<Major> majorsList;
    private static List<Subject> subjectsList;

    public static List<Subject> loadSubjects(int index) {
        try {
            List<Major> majors = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
            List<Subject> subjects = majors.get(index).getSubjects();
            return subjects;
        } catch (Exception e) {
            System.out.println("Error loading subjects");
            return Collections.emptyList();
        }
    }


    public static void updateSubject(List<Subject> subjects) {
        XFile.writeObject("fSubjectPath.txt", subjects);
    }
    
    public static void main(String[] args) {
//        List<Subject> subjects = new ArrayList<>();
//        Subject sb = new Subject("1631", "SDLC");
//        subjects.add(sb);
//        XFile.writeObject(fSubjectPath, subjects);

//        System.out.println(XFile.readObject(fSubjectPath));
    }

    public static void setSubjects(JList listSubjectsView, int indexMajor) {
        try {
            modelSubjectsList.removeAllElements(); // DELETE TO NOT DUPLICATE VALUE
            majorsList = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
            subjectsList = majorsList.get(indexMajor).getSubjects();
            modelSubjectsList.addAll(subjectsList);
            listSubjectsView.setModel(modelSubjectsList);
        } catch (Exception e) {
            //GWMS.showState(e.toString());
            System.out.println(e.getMessage());
        }

    }

    public static void setToTextField(JTextField txtSubjectID, JTextField txtSubjectName, int indexSubject) {
        txtSubjectID.setText(subjectsList.get(indexSubject).getID());
        txtSubjectName.setText(subjectsList.get(indexSubject).getName());
    }

    public static void addSubject(String ID, String name, int indexMajor) {
        Subject newSubject = new Subject(ID, name);
        subjectsList.add(newSubject);
        // LAY HET DU LIEU CUA MAJOR
        List<Major> majors = MajorController.getAllFile();
        majors.get(indexMajor).setSubjects(subjectsList); // GHI ĐÈ VAO LIST SUBJECT TRONG MAJOR OBJECT
        XFile.writeObject(MajorController.getfMajorPath(), majors);
        // THEM OBJECT VAO

    }
}
