package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.Course;
import com.patikadev.Model.Educator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane pnl_content_form;
    private JPanel pnl_edu_course_list;
    private JTable tbl_edu_course_list;
    private JScrollPane scrl_edu_course;
    private JPanel pnl_edu_content_list;
    private JScrollPane scrl_edu_content_list;
    private JTable tbl_edu_content_list;
    private JScrollPane scrl_quiz;
    private JTable tbl_edu_quiz_list;
    private JButton btn_logout;
    private JTextField fld_content_topic;
    private JTextField fld_content_exp;
    private JTextField fld_content_url;
    private JTextField fld_content_id;
    private JButton ekleButton;
    private JLabel btn_add;
    private JTextField fld_quiz_que;
    private JTextField textField2;
    private JTextField textField3;
    private JButton btn_quiz_del;
    private JLabel fld_quiz_id_del;
    private JComboBox cmb_quiz_content;
    private JButton btn_quiz_que_add;
    private JLabel lbl_edu_welcome;
    private final Educator educator;
    private DefaultTableModel mdl_edu_course_list;
    private Object[] row_edu_course_list;
    private DefaultTableModel mdl_edu_content_list;
    private Object[] row_edu_content_list;
    private DefaultTableModel mdl_edu_quiz_list;
    private Object[] row_edu_quiz_list;
    ArrayList<Integer> courseID = new ArrayList<>();
    ArrayList<String> courseName = new ArrayList<>();
    ArrayList<Integer> contentID = new ArrayList<>();

    public EducatorGUI(Educator educator) {
        this.educator = educator;
        add(wrapper);
        setSize(400, 400);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);
        mdl_edu_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1 || column == 2 || column == 3 ||column == 4)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_edu_course_list = {"ID", "Dersin Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_edu_course_list.setColumnIdentifiers(col_edu_course_list);
        row_edu_course_list = new Object[col_edu_course_list.length];
        loadEduCourseModel();
        tbl_edu_course_list.setModel(mdl_edu_course_list);
        tbl_edu_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_edu_course_list.getTableHeader().setReorderingAllowed(false);
    }

    private void loadEduCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_edu_course_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Course obj : Course.getListByUser(educator.getId())){
            i = 0;
            row_edu_course_list[i++] = obj.getId();
            courseID.add(obj.getId());      //educator ın derslerine ait contentleri listelemek için courseID arraylisti kullanıldı
            row_edu_course_list[i++] = obj.getName();
            courseName.add(obj.getName());  //contentleri filtrelemek için kullanılacak
            row_edu_course_list[i++] = obj.getLang();
            row_edu_course_list[i++] = obj.getPatika().getName();
            row_edu_course_list[i++] = obj.getEducator().getName();

            mdl_edu_course_list.addRow(row_edu_course_list);

        }
    }

    public static void main(String[] args) {
        Educator edu = new Educator();
        Helper.setLayout();
        EducatorGUI educatorGUI = new EducatorGUI(edu);
    }
}
