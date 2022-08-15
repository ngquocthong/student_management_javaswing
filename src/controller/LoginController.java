package controller;

public class LoginController {
    public boolean loginProcess(String id, String password, boolean role) {
        //data from file admin file and student file
        String idTest = "GCC200003";//
        String passwordTest = "12341234"; //
        boolean admin = true; // admin
        System.out.println(idTest + " " + id);
        try {
            if(id.equals(idTest) && password.equals(passwordTest) && admin == role) {
                return true;
            } else return false;
        } catch (Exception e) {

        }
        return true;
    }
}
