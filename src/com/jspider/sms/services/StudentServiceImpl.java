package com.jspider.sms.services;

import com.jspider.sms.db.StudentDB;
import com.jspider.sms.dto.StudentDTO;
import com.jspider.sms.entites.StudentEntity;

import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {

    @Override
    public void addStudent(StudentDTO studentDTO) throws SQLException {
        //System.out.println(studentDTO.getName());

        StudentDTOValidator.validateStudentDto(studentDTO);

        StudentDB studentDB = new StudentDB();
        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setName(studentDTO.getName());
        studentEntity.setDept(studentDTO.getDept());
        studentEntity.setDob(studentDTO.getDob());
        studentEntity.setSkills(studentDTO.getSkills());
        studentEntity.setSid(0);//it will auto increment in db so we pass 0
        studentEntity.setActive(true);//after validate the dto layer we set it separately give data in entity layer
        studentEntity.setMobNumber(studentDTO.getMobile());
        studentEntity.setEmailID(studentDTO.getEmail());

        studentDB.save(studentEntity);

    }

    public void updateName(String name, int id, StudentDB studentDB) throws SQLException {
        if (name.length() < 4) {
            throw new IllegalArgumentException("Name should be minimum 4 chars : " + name);
        }
        System.out.println(studentDB.getNameById(id) + " updated to " + name);
        studentDB.updateNameById(id, name);
    }

    public void updateDeptNo(int deptNo, int id, StudentDB studentDB) throws SQLException {
        if (deptNo <= 0) {
            throw new IllegalArgumentException("Invalid Dept No : " + deptNo);
        }
        System.out.println(studentDB.getDeptnoById(id) + " updated to " + deptNo);
        studentDB.updateDeptnoById(id, deptNo);
    }

}
