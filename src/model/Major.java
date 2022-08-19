package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Major implements Serializable {
    private String ID, name;
    private List<Subject> subjects;

    public Major(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public Major(String ID, String name, List<Subject> subjects) {
        this.ID = ID;
        this.name = name;
        this.subjects = subjects;
    }

    public Major() {

    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return this.name + " | " +this.ID;
    }
}
