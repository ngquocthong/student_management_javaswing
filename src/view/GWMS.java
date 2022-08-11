package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GWMS extends JFrame {

    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton loginButton;
    private JCheckBox rememberMeCheckBox;
    private JButton forgotPasswordButton;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField txtName;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField6;
    private JButton button1;
    private JTable table1;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JCheckBox ITCheckBox;
    private JCheckBox artsCheckBox;
    private JCheckBox businessCheckBox;
    private JCheckBox eventCheckBox;
    private JPanel JPDate;
    JDateChooser dateChooser = new JDateChooser();

    public GWMS(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);

        this.setLocationRelativeTo(null);
        this.setSize(600, 600); // replace setsize
        JPDate.add(dateChooser);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> memOfClass = new ArrayList<String>();

                String gender;
                if (maleRadioButton.isSelected()) {
                    gender = maleRadioButton.getActionCommand();
                } else {
                    gender = femaleRadioButton.getActionCommand();
                }
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dayOfBirth = sdf.format(dateChooser.getDate());
                if (ITCheckBox.isSelected()) memOfClass.add(ITCheckBox.getActionCommand());
                if (artsCheckBox.isSelected()) memOfClass.add(artsCheckBox.getActionCommand());
                if (businessCheckBox.isSelected()) memOfClass.add(businessCheckBox.getActionCommand());
                if (eventCheckBox.isSelected()) memOfClass.add(eventCheckBox.getActionCommand());


                String msg = String.format("Successfully \n Added model.Student:%s; Gender:%s; Attented class: %s Date Birth: %s", txtName.getText(), gender, memOfClass, dayOfBirth);


                showMessage(msg);
                resetForm();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uncheck();
            }
        });


    }

    private void uncheck() {
        ButtonGroup bg = maleRadioButton.getModel().getGroup();
        bg.clearSelection();
    }

    private void resetForm() {
        ITCheckBox.setSelected(false);
        artsCheckBox.setSelected(false);
        businessCheckBox.setSelected(false);
        eventCheckBox.setSelected(false);

//        ButtonGroup genderGroup = new ButtonGroup();
//        genderGroup.add(maleRadioButton);
//        genderGroup.add(femaleRadioButton);
//        genderGroup.clearSelection();

        txtName.setText("");
    }
    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        JFrame fr = new GWMS("Greenwich Manage model.Student");
        fr.setResizable(false);
        fr.setVisible(true);


    }

}
