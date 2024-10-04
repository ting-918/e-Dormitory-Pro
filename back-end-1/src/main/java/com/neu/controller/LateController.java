package com.neu.controller;

import com.neu.dto.LateDTO;
import com.neu.service.LateService;
import com.neu.utils.ResultModel;
import com.neu.vo.LateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/late")
public class LateController {
    @Autowired
    private LateService lateService;

    //查詢今天已登記過夜歸的學生名單
    @GetMapping("")
    public ResultModel<List<String>> findByDate() { return lateService.findByDate(); }

    //查詢指定學生的夜歸紀錄
    @GetMapping("/{id}")
    public ResultModel<List<LateVO>> findById(@PathVariable("id") String id) {
        return lateService.findById(id);
    }

    //登記夜歸
    @PostMapping("")
    public ResultModel add(@RequestBody LateDTO lateDTO) { return lateService.add(lateDTO); }

}
