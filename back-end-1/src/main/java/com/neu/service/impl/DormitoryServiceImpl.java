package com.neu.service.impl;

import com.neu.mapper.DormitoryMapper;
import com.neu.service.DormitoryService;
import com.neu.utils.ResultModel;
import com.neu.vo.DormitoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Autowired
    DormitoryMapper dormitoryMapper;

    @Override
    public ResultModel findRemain(int gender) {
        ResultModel<List<DormitoryVO>> resultModel = new ResultModel<List<DormitoryVO>>();
        List<DormitoryVO> dormitory = dormitoryMapper.findRemain(gender);
        resultModel.setCode(200);
        resultModel.setMsg("查找成功");
        resultModel.setData(dormitory);
        return resultModel;
    }

    @Override
    public ResultModel findByDid() {
        ResultModel<DormitoryVO> resultModel = new ResultModel<DormitoryVO>();
        DormitoryVO dormitory = dormitoryMapper.findByDid("TT12202");
        resultModel.setCode(200);
        resultModel.setMsg("查找成功");
        resultModel.setData(dormitory);
        return resultModel;
    }
}
