package controller;

import lib.XFile;
import model.Account;
import view.GWMS;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fAccountPath = "accounts.txt";
        Account manager1 = new Account("manager1", "12341234", 0);
        Account student1 = new Account("student1", "12341234", 1);

        List<Account> accountsWrite = new ArrayList<>();
        accountsWrite.add(manager1);
        accountsWrite.add(student1);
        XFile.writeObject(fAccountPath, accountsWrite);




        //JFrame fr = new LoginView("Login");
        JFrame fr = new GWMS("Greenwich Management Student");
        fr.setResizable(false);
        fr.setVisible(true);



    }
}
