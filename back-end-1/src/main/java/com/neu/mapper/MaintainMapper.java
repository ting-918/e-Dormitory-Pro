package com.neu.mapper;

import com.neu.domain.Maintain;
import com.neu.vo.MaintainVO;

import java.util.List;

public interface MaintainMapper {
    List<MaintainVO> findAll();
    List<MaintainVO> findByDid(String did);
    List<MaintainVO> findByBid(String bid);
    void add(Maintain maintain);
    void complete(Maintain maintain);
    void deleteByDid(String did);
}
