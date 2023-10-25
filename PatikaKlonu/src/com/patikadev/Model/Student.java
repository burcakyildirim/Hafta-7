package com.patikadev.Model;

import com.patikadev.Helper.DBConnector;
import com.patikadev.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Student extends User{
    public Student() {

    }

    public Student(int id, String name, String uname, String pass, String type) {
        super(id, name, uname, pass, type);
    }
    public static boolean add(String name, String uname, String type,String pass) {
        String query = "INSERT INTO student (name, uname, type,pass) VALUES (?,?,?,?)";

        try {
            PreparedStatement pr = DBConnector.getInstance().prepareStatement(query);
            pr.setString(1,name);
            pr.setString(2,uname);
            pr.setString(3,type);
            pr.setString(4,pass);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
