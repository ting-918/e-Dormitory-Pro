package com.neu.service;

import com.neu.domain.Student;
import com.neu.dto.StudentDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.StudentVO;

import java.util.List;

public interface StudentService {
    public ResultModel<List<StudentVO>> findValidStudent();
    public ResultModel<List<StudentVO>> findByBid(String bid);
    public ResultModel<List<StudentVO>> findAll();
    public ResultModel<List<Student>> addStudent(List<StudentDTO> studentList);
    public ResultModel changeDid(StudentDTO studentDTO);
    public ResultModel checkIn(String id);
    public ResultModel checkOut(String id, String reason);
}
