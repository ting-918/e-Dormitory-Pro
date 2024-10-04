package com.neu.mapper;

import com.neu.domain.Fee;
import com.neu.vo.FeeVO;

import java.util.List;

public interface FeeMapper {
    List<FeeVO> findAll(Fee fee);
    List<FeeVO> findByDid(String did);
    List<FeeVO> findByBid(Fee fee);
    void addInBatch(List<Fee> feeList);
    void updateStatus(Fee fee);
    void deleteByDid(String did);
}
