package com.neu.controller;

import com.neu.dto.BillDTO;
import com.neu.service.BillService;
import com.neu.utils.ResultModel;
import com.neu.vo.BillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    //查詢當年度繳費信息
    @GetMapping("")
    public ResultModel<List<BillVO>> findAll() {
        return billService.findAll();
    }

    //檢查是否未完成本年度費用通知工作
    @GetMapping("/check")
    public ResultModel check() {
        return billService.check();
    }

    @GetMapping("/{id}")
    public ResultModel<List<BillVO>> findById(@PathVariable("id") String id) {
        return billService.findById(id);
    }

    //下發年度繳費信息
    @PostMapping("")
    public ResultModel addInBatch(@RequestBody BillDTO billDTO) {
        return billService.addInBatch(billDTO);
    }

    //完成繳費
    @PutMapping("")
    public ResultModel updateStatus(@RequestBody BillDTO billDTO) {
        return billService.updateStatus(billDTO);
    }
}
