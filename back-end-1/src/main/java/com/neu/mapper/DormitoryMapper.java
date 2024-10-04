package com.neu.mapper;

import com.neu.domain.Dormitory;
import com.neu.vo.DormitoryVO;

import java.util.List;

public interface DormitoryMapper {
    void add(Dormitory dormitory);
    void deleteByBid(String bid);
    DormitoryVO findByDid(String did);
    int findAmount(String did);
    List<String> findValid();
    List<DormitoryVO> findRemain(int gender);
    void increaseRemain(String did);
    void decreaseRemain(String did);

}
