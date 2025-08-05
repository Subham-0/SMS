package com.jspider.sms.services;

import com.jspider.sms.dto.StudentDTO;

import java.sql.SQLException;

public interface StudentService {
    void addStudent(StudentDTO studentDTO) throws SQLException;
}
