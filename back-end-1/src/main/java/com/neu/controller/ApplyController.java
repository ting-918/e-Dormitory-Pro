package com.neu.controller;

import com.neu.dto.ApplyDTO;
import com.neu.service.ApplyService;
import com.neu.service.StudentService;
import com.neu.utils.ResultModel;
import com.neu.vo.CheckInVO;
import com.neu.vo.CheckOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;
    @Autowired
    private StudentService studentService;

    //查詢所有待審核的入住申請
    @GetMapping("/checkin")
    public ResultModel<List<CheckInVO>> findAllCheckIn() {
        return applyService.findAllCheckIn();
    }

    //查詢所有待審核的入住申請
    @GetMapping("/checkout")
    public ResultModel<List<CheckOutVO>> findAllCheckOut() {
        return applyService.findAllCheckOut();
    }

    //查找入宿申請資格
    @GetMapping("/checkin/{id}")
    public ResultModel<Integer> IsValid_checkIn(@PathVariable("id") String id) {
        return applyService.ValidApply(id, 0);
    }

    //查找入宿申請資格
    @GetMapping("/checkout/{id}")
    public ResultModel<Integer> IsValid_checkOut(@PathVariable("id") String id) {
        return applyService.ValidApply(id, 1);
    }

    //提交申請
    @PostMapping("")
    public ResultModel addApply(@RequestBody ApplyDTO applyDTO) {
        System.out.println();
        if(applyDTO.getType() == 0)
            return applyService.AddcheckIn(applyDTO.getStudentid());
        if(applyDTO.getType() == 1)
            return applyService.AddcheckOut(applyDTO.getStudentid(), applyDTO.getReason());
        ResultModel failMsg = new ResultModel();
        failMsg.setCode(350);
        failMsg.setMsg("申請失敗, 請重新操作");
        return failMsg;
    }

    //處理申請
    @PutMapping("/handle")
    public ResultModel handleApply(@RequestBody ApplyDTO applyDTO) {
        if(applyDTO.getType() == 0)
            return studentService.checkIn(applyDTO.getStudentid());
        if(applyDTO.getType() == 1)
            return studentService.checkOut(applyDTO.getStudentid(), applyDTO.getReason());
        ResultModel failMsg = new ResultModel();
        failMsg.setCode(350);
        failMsg.setMsg("處理失敗, 請重新操作");
        return failMsg;
    }

    //駁回申請
    @PutMapping("/refuse")
    public ResultModel RefuseApply(@RequestBody ApplyDTO applyDTO) {
        return applyService.Refuse(applyDTO.getStudentid(), applyDTO.getType());
    }

}
