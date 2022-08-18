package model;

import java.io.Serializable;

public class Subject implements Serializable {
    private String ID, name;
    private Double grade;

    public Subject(String ID, String name) {
        this.ID = ID;
        this.name = name;
        this.grade = 0.0;
    }

    public Subject() {

    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Double getGrade() {
        return grade;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return ID + " --- " + name;
    }
}
