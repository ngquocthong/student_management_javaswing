package model;

import java.util.Date;

public class Student {
    private byte id, age;
    private String name, email, className;
    private Date dob;

    public Student() {

    }

    public Student(byte id, byte age, String name, String email, String className, Date dob) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.email = email;
        this.className = className;
        this.dob = dob;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public void setAge(byte age) {
        this.age = age;
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

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public byte getId() {
        return id;
    }

    public byte getAge() {
        return age;
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

    public Date getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "model.Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", className='" + className + '\'' +
                ", dob=" + dob +
                '}';
    }
}
