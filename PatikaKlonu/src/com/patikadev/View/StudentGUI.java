package com.patikadev.View;

import com.patikadev.Helper.Config;
import com.patikadev.Helper.Helper;
import com.patikadev.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane pnl_stu_quiz_list;
    private JPanel pnl_patika_list;
    private JTable tbl_stu_patika_list;
    private JScrollPane scrl_patika_list;
    private JLabel lbl_stu_welcome;
    private JTextField fld_stu_patika_id;
    private JButton dersListeleButton;
    private JTable tbl_rgstr_course_list;
    private JTable tbl_stu_content_list;
    private JTable tbl_rgstr_content_list;
    private JTextField fld_review_content_ID;
    private JTextField fld_review_content_topic;
    private JTable tbl_stu_quiz_list;
    private JScrollPane scrl_stu_quiz_list;
    private JTable tbl_answer_quiz;
    private JPanel pnl_answer_quiz;
    private JScrollPane scrl_answer_quiz;
    private JButton btn_logout;
    private final Student student;

    DefaultTableModel mdl_patika_list = new DefaultTableModel();
    private Object[] row_patika_list;

    DefaultTableModel mdl_rgstr_course_list = new DefaultTableModel();
    private Object[] row_rgstr_course_list;
    DefaultTableModel mdl_rgstr_content_list = new DefaultTableModel();
    private Object[] row_rgstr_content_list;

    DefaultTableModel mdl_stu_quiz_list = new DefaultTableModel();
    private Object[] row_stu_quiz_list;

    DefaultTableModel mdl_answer_quiz_list = new DefaultTableModel();
    private Object[] row_answer_quiz_list;
    private String select_question;
    private int select_question_id;
    private int select_content_id;


    ArrayList<Integer> courseID = new ArrayList<>();
    ArrayList<Integer> contentID = new ArrayList<>();
    ArrayList<Integer> questionID = new ArrayList<>();

    public StudentGUI(Student student) {
        this.student = student;
        add(wrapper);
        setSize(400, 400);
        int x = Helper.screenCenterPoint("x", getSize());
        int y = Helper.screenCenterPoint("y", getSize());
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_stu_welcome.setText("Hoş Geldin " + student.getName());

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

        tbl_stu_patika_list.getSelectionModel().addListSelectionListener(e -> {
            try{
                String select_patika_id = tbl_stu_patika_list.getValueAt(tbl_stu_patika_list.getSelectedRow(),0).toString();
                fld_stu_patika_id.setText(select_patika_id);
            }
            catch (Exception exception){

            }
        });

        mdl_rgstr_course_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        mdl_rgstr_course_list = new DefaultTableModel();
        Object[] col_courseList = {"ID", "Dersin Adı", "Programlama Dili", "Patika", "Eğitmen"};
        mdl_rgstr_course_list.setColumnIdentifiers(col_courseList);
        row_rgstr_course_list = new Object[col_courseList.length];
        loadRgstrCourseModel();
        tbl_rgstr_course_list.setModel(mdl_rgstr_course_list);
        tbl_rgstr_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_rgstr_course_list.getTableHeader().setReorderingAllowed(false);

        mdl_rgstr_content_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 || column == 1)
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_edu_content_list = {"ID", "İçerik Başlığı", "Açıklama", "Ders Adı", "YouTube Linki"};
        mdl_rgstr_content_list.setColumnIdentifiers(col_edu_content_list);
        row_rgstr_content_list = new Object[col_edu_content_list.length];
        loadStuContentModel();
        tbl_stu_content_list .setModel(mdl_rgstr_content_list);
        tbl_stu_content_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_stu_content_list.getTableHeader().setReorderingAllowed(false);

        tbl_stu_content_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_content_id = tbl_stu_content_list.getValueAt(tbl_stu_content_list.getSelectedRow(),0).toString();
                fld_review_content_ID.setText(select_content_id);
            }
            catch (Exception exception){

            }
        });
        tbl_stu_content_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                String select_content_id = tbl_stu_content_list.getValueAt(tbl_stu_content_list.getSelectedRow(),1).toString();
                fld_review_content_topic.setText(select_content_id);
            }
            catch (Exception exception){

            }
        });


        mdl_stu_quiz_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 )
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_edu_quiz_list = {"ID", "Quiz Sorusu", "İçerik Başlığı", "İçerik ID"};
        mdl_stu_quiz_list.setColumnIdentifiers(col_edu_quiz_list);
        row_stu_quiz_list = new Object[col_edu_quiz_list.length];
        loadStuQuizModel();
        tbl_stu_quiz_list.setModel(mdl_stu_quiz_list);
        tbl_stu_quiz_list.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_stu_quiz_list.getColumnModel().getColumn(3).setMaxWidth(100);
        tbl_stu_quiz_list.getTableHeader().setReorderingAllowed(false);

        tbl_stu_quiz_list.getSelectionModel().addListSelectionListener(e -> {
            try {
                select_question = tbl_stu_quiz_list.getValueAt(tbl_stu_quiz_list.getSelectedRow(),1).toString();
                select_question_id = Integer.parseInt(tbl_stu_quiz_list.getValueAt(tbl_stu_quiz_list.getSelectedRow(),0).toString());
                select_content_id = Integer.parseInt(tbl_stu_quiz_list.getValueAt(tbl_stu_quiz_list.getSelectedRow(),3).toString());
            }
            catch (Exception exception){

            }
        });

        mdl_answer_quiz_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0 )
                    return false;
                return super.isCellEditable(row, column);
            }
        };

        Object[] col_stu_answer_quiz_list = {"ID", "Quiz Sorusu", "Cevaplarınız"};
        mdl_answer_quiz_list.setColumnIdentifiers(col_stu_answer_quiz_list);
        row_answer_quiz_list = new Object[col_edu_quiz_list.length];
        loadStuAnsQuizModel();
        tbl_answer_quiz.setModel(mdl_answer_quiz_list);
        tbl_answer_quiz.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_answer_quiz.getTableHeader().setReorderingAllowed(false);


        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI login = new LoginGUI();
        });
    }

    private void loadStuAnsQuizModel() {
    }

    private void loadStuQuizModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_quiz_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Quiz obj : Quiz.getList()){
            if (contentID.contains(obj.getContent_id())){
                i = 0;
                row_stu_quiz_list[i++] = obj.getId();
                questionID.add(obj.getId());
                row_stu_quiz_list[i++] = obj.getQuestion();
                row_stu_quiz_list[i++] = obj.getContent_topic();
                row_stu_quiz_list[i++] = obj.getContent_id();
                mdl_stu_quiz_list.addRow(row_stu_quiz_list);
            }
        }
    }

    private void loadStuContentModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_stu_content_list.getModel();
        clearModel.setRowCount(0);
        int i;
        for (Content obj : Content.getList()){
            if (courseID.contains(obj.getCourse_id())){
                i=0;
                row_rgstr_content_list[i++] = obj.getId();
                contentID.add(obj.getId());
                row_rgstr_content_list[i++] = obj.getTopic();
                row_rgstr_content_list[i++] = obj.getExp();
                row_rgstr_content_list[i++] = obj.getCourse().getName();
                row_rgstr_content_list[i++] = obj.getYtubeUrl();
                mdl_rgstr_content_list.addRow(row_rgstr_content_list);
            }
        }
    }

    private void loadRgstrCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_rgstr_course_list.getModel();
        clearModel.setRowCount(0);
        ArrayList<Integer> list = new ArrayList<>();
        for (Register c : Register.getList()){
            if (student.getId() == c.getStudent_id()){
                list.add(c.getCourse_id());
            }
        }
        int i;
        for (Course obj : Course.getList()){

            if (list.contains(obj.getId())){
                i = 0;
                row_rgstr_course_list[i++] = obj.getId();
                courseID.add(obj.getId());
                row_rgstr_course_list[i++] = obj.getName();
                row_rgstr_course_list[i++] = obj.getLang();
                row_rgstr_course_list[i++] = obj.getPatika().getName();
                row_rgstr_course_list[i++] = obj.getEducator().getName();
                mdl_rgstr_course_list.addRow(row_rgstr_course_list);
            }
        }
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
        Student stu = new Student();
        Helper.setLayout();
        StudentGUI student = new StudentGUI(stu);
    }
}
