package controller;

import lib.XFile;
import model.Account;

import java.util.ArrayList;
import java.util.List;

public class LoginController {
    private static String fAccountPath = "accounts.txt";
    public static boolean loginProcess(String id, String password, int role) {
        boolean flag = false;
        try {
            List<Account> accountsRead = new ArrayList<>();
            accountsRead = (List<Account>) XFile.readObject(fAccountPath);

            for (Account ac: accountsRead) {
                if(ac.getId().equals(id) && ac.getPassword().equals(password) && ac.getRole() == role) {
                    flag = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(flag) {return true;} else return false;
    }
}
