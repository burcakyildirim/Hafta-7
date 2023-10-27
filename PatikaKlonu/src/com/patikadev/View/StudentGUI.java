package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Patika;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JPanel pnl_patika_list;
    private JTable tbl_stu_patika_list;
    private JScrollPane scrl_patika_list;

    DefaultTableModel mdl_patika_list = new DefaultTableModel();
    private Object[] row_patika_list;

    public StudentGUI() {
        add(wrapper);
        setSize(400, 400);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        mdl_patika_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_patika_list = {"ID", "Patika Adı"};
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        loadPatikaModel();
        tbl_stu_patika_list.setModel(mdl_patika_list);
        tbl_stu_patika_list.getTableHeader().setReorderingAllowed(false);
        tbl_stu_patika_list.getColumnModel().getColumn(0).setMaxWidth(100);
    }

    private void loadPatikaModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_patika_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Patika obj : Patika.getList()){
            i = 0;
            row_patika_list[i++] = obj.getId();
            row_patika_list[i++] = obj.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public static void main(String[] args) {
        Helper.setLayout();
        StudentGUI student = new StudentGUI();
    }
}
