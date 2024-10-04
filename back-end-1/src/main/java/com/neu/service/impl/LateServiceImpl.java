package com.neu.service.impl;


import com.neu.domain.Late;
import com.neu.dto.LateDTO;
import com.neu.mapper.LateMapper;
import com.neu.service.LateService;
import com.neu.utils.ResultModel;
import com.neu.vo.LateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LateServiceImpl implements LateService {
    @Autowired
    LateMapper lateMapper;

    private java.sql.Date findLateDate() {
        Calendar calendar = Calendar.getInstance();
//        if(calendar.get(Calendar.HOUR_OF_DAY) <5)
//            calendar.set(Calendar.DAY, Calendar.DAY_OF_MONTH-1);
        //設置為0點0分0秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        System.out.println(date);
        return sqlDate;
    }

    //查詢指定學生所有夜歸紀錄
    @Override
    public ResultModel<List<LateVO>> findById(String studentid) {
        ResultModel<List<LateVO>> resultModel = new ResultModel();

        resultModel.setData(lateMapper.findById(studentid));
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //登記夜歸
    @Override
    public ResultModel add(LateDTO lateDTO) {
        ResultModel resultModel = new ResultModel();
        Late late = new Late();
        late.setStudentid(lateDTO.getStudentid());
        late.setLateDate(findLateDate());
        late.setRecordTime();
        late.setReason(lateDTO.getReason());
        lateMapper.add(late);
        resultModel.setCode(200);
        resultModel.setMsg("登記完成");
        return resultModel;
    }

    public ResultModel<List<String>> findByDate() {
        ResultModel resultModel = new ResultModel();
        Late late = new Late();
        late.setLateDate(findLateDate());
        resultModel.setData(lateMapper.findByDate(late));
        resultModel.setCode(200);
        resultModel.setMsg("登記完成");
        return resultModel;
    }
}
