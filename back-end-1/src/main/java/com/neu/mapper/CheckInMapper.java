package com.neu.mapper;

import com.neu.domain.CheckIn;
import com.neu.vo.CheckInVO;

import java.util.List;

public interface CheckInMapper {
    List<CheckInVO> findAll();
    CheckInVO findById(String studentid);
    void add(CheckIn checkIn);
    void deleteById(String studentid);
    void updateById(CheckIn checkIn);
}
