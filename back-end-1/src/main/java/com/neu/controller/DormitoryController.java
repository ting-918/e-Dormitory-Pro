package com.neu.controller;

import com.neu.service.DormitoryService;
import com.neu.utils.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;

    //查找有效宿舍
    @GetMapping("/{gender}")
    public ResultModel findRemain(@PathVariable("gender") int gender) {
        return dormitoryService.findRemain(gender);
    }

//    @GetMapping("")
//    public ResultModel findByDid() {
//        return dormitoryService.findByDid();
//    }
}
