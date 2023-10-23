package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private DefaultTableModel mdl_user_list;
    private Object[] row_user_list;
    private final Operator operator;

    public OperatorGUI(Operator operator) {

        this.operator = operator;
        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenterPoint("x", getSize());
        int y =Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);


        lbl_welcome.setText("Hoş Geldin! " + operator.getName());

        //ModelUserList
        mdl_user_list = new DefaultTableModel();
        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);

        for(User obj : User.getList()) {
            Object[] row = new Object[col_user_list.length];
            row[0]= obj.getId();
            row[1]= obj.getName();
            row[2]= obj.getUname();
            row[3]= obj.getPass();
            row[4]= obj.getType();
            mdl_user_list.addRow(row);
        }

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
    }

    public static void main(String[] args) {
        Helper.setLayout();
        Operator op = new Operator();
        op.setId(1);
        op.setName("Burçak Yıldırım");
        op.setUname("Burcak");
        op.setPass("1234");
        op.setType("operator");
        OperatorGUI operatorGUI = new OperatorGUI(op);
    }
}
