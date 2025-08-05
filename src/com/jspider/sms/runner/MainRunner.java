package com.jspider.sms.runner;

import com.jspider.sms.dto.StudentDTO;
import com.jspider.sms.services.StudentDTOValidator;
import com.jspider.sms.services.StudentService;
import com.jspider.sms.services.StudentServiceImpl;

import java.sql.SQLException;
import java.util.Scanner;

public class MainRunner {
    public static void main(String[] args) {
        System.out.println("main starts.........");

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
}
