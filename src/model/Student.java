package model;

import controller.SubjectController;
import lib.XUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    private String id;
    private String name, email;
    private String className;
    private Major major;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                ", major=" + major +
                ", clubs=" + clubs +
                ", dob=" + dob +
                ", gender=" + gender +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    List<String> clubs;
    private Date dob;
    private Boolean gender;
    private String imageUrl;


    public Student()    {
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getGender() {
        return gender;
    }


    public Student(String id, String name, String email, Major major, List<String> clubs, String dob, String imageUrl, String className, Boolean gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.className = className;
        this.clubs = clubs;
        this.imageUrl = imageUrl;
        this.major = major;
        this.dob = XUtil.convertStringToDate(dob);
        this.gender = gender;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public Major getMajor() {
        return major;
    }

    public List<String> getClubs() {
        return clubs;
    }

    public Date getDob() {
        return dob;
    }
}
