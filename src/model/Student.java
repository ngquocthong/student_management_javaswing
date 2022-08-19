package model;

import controller.SubjectController;
import lib.XUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    private String id;
    private String name, email, className, major;
    private List<Subject> subjects;
    List<String> clubs;
    private Date dob;


    public Student()    {
    }

    public Student(String id, String name, String email, String className, List<String> clubs, String dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.className = className;
        this.clubs = clubs;
        this.major = "IT";
        subjects = new ArrayList<>();
        for(Subject subject : SubjectController.loadSubjects(0)) {
            subjects.add(subject);
        }
        this.dob = XUtil.convertStringToDate(dob);

    }
//All setter and getter

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Student(String name, String className) {
        this.name = name;
        this.className = className;
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

    public void setMajor(String major) {
        this.major = major;
    }


    public void setClubs(List<String> clubs) {
        this.clubs = clubs;
    }

    public void setDob(Date dob) {
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

    public String getMajor() {
        return major;
    }

    public List<String> getClubs() {
        return clubs;
    }

    public Date getDob() {
        return dob;
    }
}
