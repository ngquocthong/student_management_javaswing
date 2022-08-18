package controller;

import lib.XFile;
import model.Subject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubjectController { 
    private static String fSubjectPath = "subjects.dat";
    
    public static List<Subject> loadSubjects() {
        List<Subject> subjects = (List<Subject>) XFile.readObject(fSubjectPath);
        return subjects;
    }
    public static void addSubject(List<Subject> subjects) {
        XFile.writeObject(fSubjectPath, subjects);
    }

    public static void updateSubject(List<Subject> subjects) {
        XFile.writeObject(fSubjectPath, subjects);
    }
    
    public static void main(String[] args) {
//        List<Subject> subjects = new ArrayList<>();
//        Subject sb = new Subject("1631", "SDLC");
//        subjects.add(sb);
//        XFile.writeObject(fSubjectPath, subjects);

//        System.out.println(XFile.readObject(fSubjectPath));
    }
}   
