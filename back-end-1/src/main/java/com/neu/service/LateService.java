package com.neu.service;

import com.neu.dto.LateDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.LateVO;

import java.util.List;

public interface LateService {
    public ResultModel<List<LateVO>> findById(String studentid);
    public ResultModel<List<String>> findByDate();
    public ResultModel add(LateDTO lateDTO);
}
