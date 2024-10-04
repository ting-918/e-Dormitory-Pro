package com.neu.controller;

import com.neu.dto.BuildingDTO;
import com.neu.service.BuildingService;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    //查找所有樓棟信息
    @GetMapping("")
    public ResultModel<List<BuildingVO>> findAll(){
        return buildingService.findAll();
    }


    //新增樓棟
    @PostMapping("")
    public ResultModel addBuilding(@RequestBody BuildingDTO buildingDTO) {
        return buildingService.addBuilding(buildingDTO.getBid(), buildingDTO.getFloor(), buildingDTO.getRoom(), buildingDTO.getBed());
    }

    //删除樓棟
    @DeleteMapping("/delete/{id}")
    public ResultModel deleteBuilding(@PathVariable("id") String id){
        return buildingService.deleteBuilding(id);
    }

    //更新指定宿管的密码
    @PutMapping("/edit")
    public ResultModel updateByBid(@RequestBody BuildingDTO buildingDTO){
        return buildingService.updateByBid(buildingDTO);
    }
}
