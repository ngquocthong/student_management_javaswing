package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    public static int numberOfStudent = 0;
    private String id;
    private String name, email, className;
    List<String> clubs;
    private String dob;


    public Student()    {
        numberOfStudent++;
    }

    public Student(String name, String className) {
        this.name = name;
        this.className = className;
        numberOfStudent++;
    }

    public Student(String id, String name, String className,String dob, List<String> clubs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.className = className;
        this.dob = dob;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getClassName() {
        return className;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "model.Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                ", dob=" + dob +
                '}';
    }
}
