package com.jspider.sms.runner;

import com.jspider.sms.db.StudentDB;
import com.jspider.sms.dto.StudentDTO;
import com.jspider.sms.entites.StudentEntity;
import com.jspider.sms.services.StudentService;
import com.jspider.sms.services.StudentServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainRunner {
    public static void main(String[] args) {
        System.out.println("main starts.........");
        testGetAll();
//      testAdd();
//      testGetById();

        try {
            testUpdateById();
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }

//       testDeleteDataById();


    }

    private static void testDeleteDataById() {
        System.out.println("To Delete the Enter StudentId");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        try {
            StudentDB studentDB = new StudentDB();
            studentDB.deleteDataById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testUpdateById() throws SQLException {
        System.out.println("To update the data Enter StudentId");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        StudentDB studentDB = new StudentDB();
        if (studentDB.validateId(id)) {
            System.out.println("Validated ✔️");
            StudentServiceImpl studentService = new StudentServiceImpl();
            System.out.println("enter the updated name");
            String name = input.next();
            studentService.updateName(name, id, studentDB);
//            System.out.println("enter the updated deptno");
//            int deptNo = input.nextInt();
//            studentService.updateDeptNo(deptNo,id,studentDB);

        } else {
            System.err.println("Invalid student ID");
        }


    }


    private static void testGetById() {
        System.out.println("Enter StudentId");
        Scanner input = new Scanner(System.in);
        int id = input.nextInt();
        StudentDB studentDB = new StudentDB();
        try {
            StudentEntity studentEntity = studentDB.getById(id);
            System.out.println(studentEntity);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void testAdd() {
        System.out.println("Enter Student data");
        Scanner input = new Scanner(System.in);

        StudentDTO studentDTO = new StudentDTO();

        System.out.println("Enter Student's name");
        studentDTO.setName(input.next());
        System.out.println("Enter student's dept");
        studentDTO.setDept(input.nextInt());
        System.out.println("Enter student's DOB");
        studentDTO.setDob(input.next());
        System.out.println("Enter student's Skill");
        studentDTO.setSkills(input.next());
        System.out.println("Enter student's Mobile Number");
        studentDTO.setMobile(input.next());
        System.out.println("Enter student's Email ID");
        studentDTO.setEmail(input.next());

        try {
//            StudentDTOValidator.validateStudentDto(studentDTO);
            StudentService studentService = new StudentServiceImpl();
            studentService.addStudent(studentDTO);
        } catch (IllegalArgumentException | SQLException e) {
            System.err.println("Error : " + e.getMessage());
        }

    }

    private static void testGetAll() {
        StudentDB studentDB = new StudentDB();
        try {
            List<StudentEntity> studentEntities = studentDB.getAll();
            studentEntities.forEach(studentEntity -> System.out.println(studentEntity));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
