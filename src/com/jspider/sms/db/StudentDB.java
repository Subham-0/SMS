package com.jspider.sms.db;

import com.jspider.sms.entites.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private final String url = "jdbc:mysql://localhost:3306/studentdb";
    private final String username = "root";
    private final String password = "Run@0";

    Connection con;

    public StudentDB() {
        try {
            con = DriverManager.getConnection(url, username, password);
//            System.out.println("diver connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        pst.setString(7, studentEntity.getMobNumber());
        pst.setString(8, studentEntity.getEmailID());

        pst.execute();
        System.out.println("Student data added to db");
    }

    public List<StudentEntity> getAll() throws SQLException {

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from student");
        List<StudentEntity> studentList = new ArrayList<StudentEntity>();
        while (rs.next()) {

            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setSid(rs.getInt("idstudent"));
            studentEntity.setName(rs.getString("studentname"));
            studentEntity.setDept(rs.getInt("studentdeptno"));
            studentEntity.setDob(rs.getString("studentdob"));
            studentEntity.setSkills(rs.getString("studentskill"));
            studentEntity.setActive(rs.getBoolean("studentactivity"));
            studentEntity.setMobNumber(rs.getString("studentmobilenumber"));
            studentEntity.setEmailID(rs.getString("studentemailid"));
            studentList.add(studentEntity);
        }
        return studentList;
    }

    public StudentEntity getById(int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select * from student where idstudent = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        //check  if the result is empty
        if (rs.next()) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setSid(rs.getInt("idstudent"));
            studentEntity.setName(rs.getString("studentname"));
            studentEntity.setDept(rs.getInt("studentdeptno"));
            studentEntity.setDob(rs.getString("studentdob"));
            studentEntity.setSkills(rs.getString("studentskill"));
            studentEntity.setActive(rs.getBoolean("studentactivity"));
            studentEntity.setMobNumber(rs.getString("studentmobilenumber"));
            studentEntity.setEmailID(rs.getString("studentemailid"));
            return studentEntity;
        } else {
            throw new IllegalArgumentException("Invalid sid :" + id);
        }
    }

    public void deleteDataById(int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("delete from student where idstudent=?");
        pst.setInt(1, id);
        pst.execute();
        System.out.println("Student data deleted successfully");
    }

    public void updateNameById(int id, String name) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update student set studentname =? where idstudent =?");
        pst.setString(1, name);
        pst.setInt(2, id);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Student data updated successfully ✔️");
        }

    }

    public String getNameById(int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select studentname from student where idstudent =?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        String name = "";
        while (rs.next()) {
            name = rs.getString("studentname");
        }
        return name;
    }

    public boolean validateId(int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select idstudent from student where idstudent = ?");
        pst.setInt(1, id);
        System.out.println("Checking your ID......");
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    public int getDeptnoById(int id) throws SQLException {
        PreparedStatement pst = con.prepareStatement("select studentdeptno from student where idstudent =?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        int deptNo = 0;
        while (rs.next()) {
            deptNo = rs.getInt("studentdeptno");
        }
        return deptNo;
    }

    public void updateDeptnoById(int id, int deptNo) throws SQLException {
        PreparedStatement pst = con.prepareStatement("update student set studentdeptno =? where idstudent =?");
        pst.setInt(1, deptNo);
        pst.setInt(2, id);
        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Student data updated successfully ✔️");
        }
    }
}
