package com.patikadev.View;

import com.patikadev.Helper.*;
import com.patikadev.Model.Operator;
import com.patikadev.Model.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperatorGUI extends JFrame {
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField fld_user_name;
    private JTextField fld_user_uname;
    private JTextField fld_user_pass;
    private JComboBox cmb_user_type;
    private JButton btn_user_add;
    private JTextField fld_user_id;
    private JButton btn_user_delete;
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
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_user_list = {"ID", "Ad Soyad", "Kullanıcı Adı", "Şifre", "Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_user_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                fld_user_id.setText(select_user_id);
            } catch (Exception exception) {

            }
        });

        btn_user_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_name) || Helper.isFieldEmpty(fld_user_uname)|| Helper.isFieldEmpty(fld_user_pass)) {
                Helper.showMessage("fill");
            } else {
                String name = fld_user_name.getText();
                String uname = fld_user_uname.getText();
                String pass = fld_user_pass.getText();
                String type = cmb_user_type.getSelectedItem().toString();
                if(User.add(name,uname,pass,type)) {
                    Helper.showMessage("done");
                    loadUserModel();
                    fld_user_name.setText(null);
                    fld_user_uname.setText(null);
                    fld_user_pass.setText(null);
                }
            }
        });
        btn_user_delete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fld_user_id)) {
                Helper.showMessage("fill");
            } else {
                int user_id = Integer.parseInt(fld_user_id.getText());
                if(User.delete(user_id)) {
                    Helper.showMessage("done");
                    loadUserModel();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }
    public void loadUserModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_user_list.getModel();
        clearModel.setRowCount(0);

        for(User obj : User.getList()) {
            int i = 0;
            row_user_list[i++]= obj.getId();
            row_user_list[i++]= obj.getName();
            row_user_list[i++]= obj.getUname();
            row_user_list[i++]= obj.getPass();
            row_user_list[i++]= obj.getType();
            mdl_user_list.addRow(row_user_list);
        }
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
