package model;

import java.io.Serializable;

public class Account implements Serializable {
    String id;
    String password;
    int role = -1; // 0 manager || 1 student || -1 not set

    public Account() {

    }

    public Account(String id, String password, int role) {
        this.id = id;
        this.password = password;
        this.role = role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
}
