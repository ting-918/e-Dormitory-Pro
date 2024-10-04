package com.neu.mapper;

import com.neu.domain.Building;
import com.neu.vo.BuildingVO;

import java.util.List;

public interface BuildingMapper {
    List<BuildingVO> findAll();
    BuildingVO findByBid(String bid);
    void add(Building building);
    void deleteByBid(String bid);
    void updateByBid(Building building);
}
