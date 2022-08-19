package controller;

import lib.XFile;
import model.Student;
import model.Subject;

import java.util.ArrayList;
import java.util.List;

public class GradeController {
    public static void gradeStudent(Double grade, int indexStudent, int indexSubject) {
        List<Student> students = (List<Student>) XFile.readObject(StudentController.getfStudentPath());
        Student student = students.get(indexStudent);
        List<Subject> subjects = student.getSubjects();

    }
}
