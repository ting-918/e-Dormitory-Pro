package com.neu.mapper;

import com.neu.domain.Bill;
import com.neu.vo.BillVO;

import java.util.List;

public interface BillMapper {
    List<BillVO> findAll(String year);
    List<BillVO> check(String year);
    void add(Bill bill);
    void addInBatch(Bill bill);
    void updateStatus(Bill bill);
    List<BillVO> findById(Bill bill);
}
