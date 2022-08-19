package controller;

import lib.XFile;
import model.Major;
import model.Subject;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubjectController { 

    private static DefaultListModel modelSubjectsList = new DefaultListModel();
    private static List<Major> majorsList;
    private static List<Subject> subjectsList;

    public static List<Subject> loadSubjects(int index) {
        List<Major> majors = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
        List<Subject> subjects = majors.get(index).getSubjects();

        return subjects;
    }
    public static void addSubject(List<Subject> subjects) {
        XFile.writeObject("fSubjectPath.txt", subjects);
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
        modelSubjectsList.removeAllElements(); // DELETE TO NOT DUPLICATE VALUE
        majorsList = (List<Major>) XFile.readObject(MajorController.getfMajorPath());
        subjectsList = majorsList.get(indexMajor).getSubjects();
        modelSubjectsList.addAll(subjectsList);
        listSubjectsView.setModel(modelSubjectsList);

    }
}
