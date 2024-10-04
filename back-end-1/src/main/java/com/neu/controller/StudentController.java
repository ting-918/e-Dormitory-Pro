package com.neu.controller;

import com.neu.domain.Student;
import com.neu.dto.StudentDTO;
import com.neu.service.StudentService;
import com.neu.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //查找有效住宿生
    @GetMapping("")
    public ResultModel findValidStudents() {
        return studentService.findValidStudent();
    }

    //查找指定樓棟所屬住宿生
    @GetMapping("/{bid}")
    public ResultModel findValidStudents(@PathVariable("bid") String bid) {
        return studentService.findByBid(bid);
    }

    //查找所有學生
    @GetMapping("/all")
    public ResultModel findAll() {
        return studentService.findAll();
    }

    //新增學生
    @PostMapping("")
    public ResultModel<List<Student>> addStudent(@RequestBody List<StudentDTO> studentList) {
        return studentService.addStudent(studentList);
    }

    //寢室調動
    @PutMapping("/change")
    public ResultModel changeDid(@RequestBody StudentDTO studentDTO){
        return studentService.changeDid(studentDTO);
    }

}
