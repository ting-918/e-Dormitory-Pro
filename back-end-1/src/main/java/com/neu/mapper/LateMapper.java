package com.neu.mapper;

import com.neu.domain.Late;
import com.neu.vo.LateVO;

import java.util.List;

public interface LateMapper {
    List<LateVO> findById(String studentid);
    List<String> findByDate(Late late);
    void add(Late late);
}
