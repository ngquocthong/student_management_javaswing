package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JTextField txtID;
    private JPanel loginMainPanel;
    private JTextField txtPassword;
    private JComboBox cmbRole;
    private JCheckBox cbRememberMe;
    private JButton LOGINButton;

    public LoginView(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginMainPanel);
        this.setLocationRelativeTo(null);
        this.setSize(500, 300);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = txtID.getText();
                    boolean check = LoginController.loginProcess(txtID.getText(), txtPassword.getText(), cmbRole.getSelectedIndex());
                    if (check) {
                        setVisible(false);
                        JFrame fr = new GWMS("Greenwich Management Student", cmbRole.getSelectedItem().toString() + ": " + user);
                        fr.setResizable(false);
                        fr.setVisible(true);
                    } else {
                        showMessage("Login Failed");
                    }
            }
        });
    }
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

}
