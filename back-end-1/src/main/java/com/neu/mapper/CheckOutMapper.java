package com.neu.mapper;

import com.neu.domain.CheckOut;
import com.neu.vo.CheckOutVO;

import java.util.List;

public interface CheckOutMapper {
    List<CheckOutVO> findAll();
    CheckOutVO findById(String studentid);
    void add(CheckOut checkOut);
    void deleteById(String studentid);
    void updateById(CheckOut checkOut);
}
