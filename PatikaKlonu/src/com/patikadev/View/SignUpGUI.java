package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Student;
import com.patikadev.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fld_sign_name;
    private JTextField fld_sign_uname;
    private JButton btn_sign;
    private JTextField fld_sign_pass;
    private JComboBox cmb_sign_type;

    public SignUpGUI() {
        add(wrapper);
        setSize(400,400);
        int x = Helper.screenCenterPoint("x", getSize());
        int y =Helper.screenCenterPoint("y", getSize());
        setLocation(x,y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_sign.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_sign_name) || Helper.isFieldEmpty(fld_sign_uname)||Helper.isFieldEmpty(fld_sign_pass)) {
                Helper.showMessage("fill");
            } else {
                String name = fld_sign_name.getText();
                String uname = fld_sign_uname.getText();
                String pass = fld_sign_pass.getText();
                String type = cmb_sign_type.getSelectedItem().toString();
                if(Student.add(name,uname,type,pass)) {
                    Helper.showMessage("done");
                }
            }
        });
    }


    public static void main(String[] args) {
        Helper.setLayout();
        SignUpGUI sign = new SignUpGUI();
    }

}
