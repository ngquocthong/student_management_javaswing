package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classer implements Serializable {
    private String name; // GCC0902
    private List<Student> studentEnrolled;

    public Classer() {

    }
    public Classer(String name) {
        this.name = name;
        studentEnrolled = new ArrayList<Student>();
    }
    public List<Student> getStudentEnrolled() {
        return studentEnrolled;
    }

    @Override
    public String toString() {
        return "Classer{" +
                "name='" + name + '\'' +
                ", studentEnrolled=" + studentEnrolled +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentEnrolled(List<Student> studentEnrolled) {
        this.studentEnrolled = studentEnrolled;
    }
}
