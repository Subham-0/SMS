package com.jspider.sms.services;

import com.jspider.sms.dto.StudentDTO;

public class StudentDTOValidator {
    public static void validateStudentDto(StudentDTO studentDTO) {
        checkName(studentDTO);
        checkDeptono(studentDTO);
        checkDOB(studentDTO);
        checkSkill(studentDTO);
        checkEmail(studentDTO);
        checkMobileNumber(studentDTO);
    }

    private static void checkMobileNumber(StudentDTO studentDTO) {
        String mobileNumber = studentDTO.getMobile();
        if (mobileNumber.length() != 10) {
            throw new IllegalArgumentException("Mobile Number should be 10 digits");
        }
    }

    private static void checkEmail(StudentDTO studentDTO) {
        String email = studentDTO.getEmail();
        if (!email.contains("@") || !email.contains("gmail.com")) {
            throw new IllegalArgumentException("Email should have @ gmail.com");
        }
    }

    private static void checkSkill(StudentDTO studentDTO) {
        String skill = studentDTO.getSkills();
        if (skill.length() < 3) {
            throw new IllegalArgumentException("skill should minimum 3 character");
        }
    }

    private static void checkDOB(StudentDTO studentDTO) {
        String dob = studentDTO.getDob();
    }

    private static void checkName(StudentDTO studentDTO) {
        String name = studentDTO.getName();
        if (name.length() < 4) {
            throw new IllegalArgumentException("Name should be minimum 4 chars : " + name);
        }
    }

    private static void checkDeptono(StudentDTO studentDTO) {
        int deptNo = studentDTO.getDept();
        if (deptNo <= 0) {
            throw new IllegalArgumentException("Invalid Dept No : " + deptNo);
        }
    }
}
