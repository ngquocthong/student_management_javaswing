package controller;

import lib.XFile;
import model.Student;
import model.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;


public class GradeController {
    private static DefaultTableModel modelTable;
    private static List<Subject> subjects;

    public static void gradeStudent(Double grade, int indexStudent, int indexSubject) {
        List<Student> students = StudentController.readAllStudent();
        Student student = students.get(indexStudent);
        student.getMajor().getSubjects().get(indexSubject).setGrade(grade);
        XFile.writeObject(StudentController.getfStudentPath(), students);
    }

    public static void setTable(JTable tbGrade, int indexStudent) {
        List<Student> students = StudentController.readAllStudent();
        Student student = students.get(indexStudent);
        modelTable = (DefaultTableModel) tbGrade.getModel();
        tbGrade.setDefaultEditor(Object.class, null);
        subjects = student.getMajor().getSubjects();
        modelTable.setRowCount(0);

        for (Subject sj : subjects) {
            Object[] row = new Object[]{sj.getID(), sj.getName(), sj.getGrade()};
            modelTable.addRow(row);
        }
    }
}
