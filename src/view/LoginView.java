package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JTextField txtID;
    private JPanel loginMainPanel;
    private JTextField txtPass;
    private JComboBox cmbRole;
    private JCheckBox cbRememberMe;
    private JButton lostPasswordButton;
    private JButton LOGINButton;
    private JButton btnRegister;

    public LoginView(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginMainPanel);
        this.setLocationRelativeTo(null);
        this.pack();

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController login = new LoginController();

                try {
                    boolean check = login.loginProcess(txtID.getText(), txtPass.getText(), true);
                    if (check) {
                        showMessage("Login Successfully");
                        JFrame fr = new GWMS("Greenwich Manage model.Student");
                        fr.setResizable(false);
                        fr.setVisible(true);
                    } else {
                        showMessage("Login Failed");
                    }

                } catch (Exception er) {}
            }
        });
    }
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
