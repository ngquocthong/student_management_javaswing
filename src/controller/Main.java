package controller;

import view.GWMS;
import view.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame loginPanel = new LoginView("Login GWMS");
        loginPanel.setResizable(false);
        loginPanel.setVisible(true);
        loginPanel.pack();



    }
}
