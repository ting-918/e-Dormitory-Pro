package com.neu.service;

import com.neu.dto.BuildingDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;

import java.util.List;

public interface BuildingService {
    public ResultModel<List<BuildingVO>> findAll();
    public ResultModel addBuilding(String bid, int floor, int room, int bed);
    public ResultModel deleteBuilding(String bid);
    public ResultModel updateByBid(BuildingDTO buildingDTO);
}
