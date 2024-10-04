package com.neu.controller;

import com.neu.dto.FeeDTO;
import com.neu.service.FeeService;
import com.neu.utils.ResultModel;
import com.neu.vo.FeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    private FeeService feeService;

    //查詢當月繳費信息
    @GetMapping("")
    public ResultModel<List<FeeVO>> findAll() {
        return feeService.findAll();
    }

    //檢查是否未完成本年度費用通知工作
    @GetMapping("/check")
    public ResultModel<List<String>> check() {
        return feeService.check();
    }

    //根據did查詢所有繳費信息
    @GetMapping("/{did}")
    public ResultModel<List<FeeVO>> findByDid(@PathVariable("did") String did) { return feeService.findByDid(did); }

    //根據bid查詢所有繳費信息
    @GetMapping("/building/{bid}")
    public ResultModel<List<FeeVO>> findByBid(@PathVariable("bid") String bid) { return feeService.findByBid(bid); }

    //下發繳費信息
    @PostMapping("")
    public ResultModel addInBatch(@RequestBody List<String> didList) { return feeService.addInBatch(didList); }

    //完成繳費
    @PutMapping("")
    public ResultModel updateStatus(@RequestBody FeeDTO feeDTO) {
        return feeService.updateStatus(feeDTO);
    }
}
