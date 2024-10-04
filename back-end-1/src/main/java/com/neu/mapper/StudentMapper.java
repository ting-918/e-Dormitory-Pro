package com.neu.mapper;


import com.neu.domain.Student;
import com.neu.vo.StudentVO;

import java.util.List;

public interface StudentMapper {
    List<StudentVO> findValid(String bid);
    List<StudentVO> findAll();
    void add(Student student);
    StudentVO findById(String studentid);
    void updateById(Student student);
}
