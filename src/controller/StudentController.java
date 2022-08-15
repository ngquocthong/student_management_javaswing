package controller;

import lib.XFile;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private static String fStudentPath = "CanTho/student.dat";
    static List<Student> students = new ArrayList<>();
    public static void addToList(Student student) {
        students.add(student);
        System.out.println(students.toString());
    }
    public static void saveStudent() {
        XFile.writeObject(fStudentPath, students);
        List<Student> newLst = (List<Student>) XFile.readObject(fStudentPath);
        for (Student o: newLst) {
            System.out.println(o.getName());
        }
    }

}
