package com.neu.service.impl;

import com.neu.domain.Building;
import com.neu.domain.Dormitory;
import com.neu.dto.BuildingDTO;
import com.neu.mapper.BuildingMapper;
import com.neu.mapper.DormitoryMapper;
import com.neu.mapper.MaintainMapper;
import com.neu.service.BuildingService;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    BuildingMapper buildingMapper;
    @Autowired
    DormitoryMapper dormitoryMapper;
    @Autowired
    MaintainMapper maintainMapper;

    @Override
    public ResultModel<List<BuildingVO>> findAll() {
        ResultModel<List<BuildingVO>> resultModel = new ResultModel<List<BuildingVO>>();

        List<BuildingVO> buildingVOList = buildingMapper.findAll();

        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        resultModel.setData(buildingVOList);
        return resultModel;
    }
    @Override
    public ResultModel addBuilding(String bid, int floor, int room, int bed) {
        ResultModel<String> resultModel = new ResultModel<String>();
        //系統自動預設密碼
        String defaultPWD = bid + "Manager";
        //新增樓棟紀錄(DTO一>domin)
        Building building = new Building();
        building.setBid(bid);
        building.setPwd(defaultPWD);
        buildingMapper.add(building);
        //新增屬於該樓棟的宿舍
        for(int f = 2; f <= floor; f++){
            for(int r = 1; r <= room; r++){
                //門牌號=樓層+房號
                String door = Integer.toString(f) + String.format("%02d",r);
                //新增宿舍
                Dormitory dormitory = new Dormitory();
                dormitory.setBid(bid);
                dormitory.setDoor(door);
                dormitory.setRemain(bed);
                dormitory.setDid(bid+door);
                dormitoryMapper.add(dormitory);
            }
        }
        resultModel.setCode(200);
        resultModel.setMsg("新增成功");
        resultModel.setData(defaultPWD);
        return resultModel;
    }
    @Override
    public ResultModel deleteBuilding(String bid) {
        //刪除前必須確保沒有居住在此樓棟的學生
        ResultModel resultModel = new ResultModel();
        //刪除屬於該樓棟的宿舍紀錄
        dormitoryMapper.deleteByBid(bid);
        //刪除該樓棟紀錄
        buildingMapper.deleteByBid(bid);

        resultModel.setCode(200);
        resultModel.setMsg("刪除成功");
        return resultModel;
    }
    @Override
    public ResultModel updateByBid(BuildingDTO buildingDTO) {
        ResultModel resultModel = new ResultModel();
        //更新樓棟紀錄(DTO一>domin)
        Building building = new Building();
        building.setBid(buildingDTO.getBid());
        building.setPwd(buildingDTO.getPwd());
        buildingMapper.updateByBid(building);

        resultModel.setCode(200);
        resultModel.setMsg("修改成功");
        return resultModel;
    }
}
