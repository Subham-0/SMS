package com.jspider.sms.services;

import com.jspider.sms.dto.StudentDTO;

public class StudentServiceImpl implements StudentService {

    @Override
    public void addStudent(StudentDTO studentDTO) {
        System.out.println(studentDTO.getName());
        System.out.println("student added to db");
    }
}
