package com.jspider.sms.db;

import com.jspider.sms.entites.StudentEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDB {
    private final String url = "jdbc:mysql://localhost:3306/studentdb";
    private final String username = "root";
    private final String password = "Run@0";


    Connection con ;
    public StudentDB() {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("diver connected");
        } catch (SQLException e) {
            System.out.println( e.getMessage());
        }
    }

    public void save(StudentEntity studentEntity) throws SQLException {
        PreparedStatement pst = con.prepareStatement("insert into student values(?,?,?,?,?,?,?,?)");
        pst.setInt(1, studentEntity.getSid());
        pst.setString(2, studentEntity.getName());
        pst.setInt(3, studentEntity.getDept());
        pst.setString(4, studentEntity.getDob());
        pst.setString(5, studentEntity.getSkills());
        pst.setBoolean(6, studentEntity.isActive());
        pst.setString(7,studentEntity.getMobNumber());
        pst.setString(8,studentEntity.getEmailID());

        pst.execute();
        System.out.println("Student data added to db");
    }
}
