package com.neu.controller;

import com.neu.dto.MaintainDTO;
import com.neu.service.MaintainService;
import com.neu.utils.ResultModel;
import com.neu.vo.MaintainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintain")
public class MaintainController {
    @Autowired
    private MaintainService maintainService;

    //查找所有報修紀錄
    @GetMapping("")
    public ResultModel<List<MaintainVO>> findAll() {
        return maintainService.findAll();
    }

    //查找指定寢室的報修紀錄
    @GetMapping("/{did}")
    public ResultModel<List<MaintainVO>> findByDid(@PathVariable("did") String did) {
        return maintainService.findByDid(did);
    }

    //查找指定樓棟的報修紀錄
    @GetMapping("/building/{bid}")
    public ResultModel<List<MaintainVO>> findByBid(@PathVariable("bid") String bid) {
        return maintainService.findByBid(bid);
    }

    //提交申請
    @PostMapping("")
    public ResultModel addApply(@RequestBody MaintainDTO maintainDTO) {
        return maintainService.Add(maintainDTO);
    }

    //完成維修
    @PutMapping("/{id}")
    public ResultModel handleApply(@PathVariable("id") int id) { return maintainService.Complete(id);}

}
