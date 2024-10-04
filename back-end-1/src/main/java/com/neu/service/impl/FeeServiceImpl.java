package com.neu.service.impl;

import com.neu.domain.Fee;
import com.neu.dto.FeeDTO;
import com.neu.mapper.DormitoryMapper;
import com.neu.mapper.FeeMapper;
import com.neu.service.FeeService;
import com.neu.utils.ResultModel;
import com.neu.vo.FeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeServiceImpl implements FeeService {
    @Autowired
    FeeMapper feeMapper;

    @Autowired
    DormitoryMapper dormitoryMapper;

    private java.sql.Date findFeeDate(int minus) {
        Calendar calendar = Calendar.getInstance();
        //設置到當月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //設置為0點0分0秒
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        //設置年月份
        int month = calendar.get(Calendar.MONTH)+1-minus;
        if(month == 1-minus) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            calendar.set(Calendar.MONTH, 11);
        }
        else {
            calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-1-minus);
        }
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    private List<String> checkStatus() {
        Fee fee = new Fee();
        fee.setFeeDate(findFeeDate(0));
        List<String> didList = dormitoryMapper.findValid();
        List<FeeVO> fees = feeMapper.findAll(fee);
        List<String> existList = fees.stream().map(FeeVO::getDid).collect(Collectors.toList());
        //求差集(過濾已存在繳費單的宿舍)
        didList.removeAll(existList);

        return didList;
    }

    //查詢本季所有繳費信息
    @Override
    public ResultModel<List<FeeVO>> findAll() {
        ResultModel<List<FeeVO>> resultModel = new ResultModel();

        Fee fee = new Fee();
        fee.setFeeDate(findFeeDate(0));
        resultModel.setData(feeMapper.findAll(fee));
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查詢指定did的所有繳費記錄
    @Override
    public ResultModel<List<FeeVO>> findByDid(String did) {
        ResultModel<List<FeeVO>> resultModel = new ResultModel();

        resultModel.setData(feeMapper.findByDid(did));
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查詢指定bid的所有繳費記錄
    @Override
    public ResultModel<List<FeeVO>> findByBid(String bid) {
        ResultModel<List<FeeVO>> resultModel = new ResultModel();
        Fee fee = new Fee();
        fee.setBid(bid);
        if(checkStatus().isEmpty())
            fee.setFeeDate(findFeeDate(0));
        else
            fee.setFeeDate(findFeeDate(1));

        resultModel.setData(feeMapper.findByBid(fee));
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //檢查是否未完成水電費用通知工作
    @Override
    public ResultModel<List<String>> check() {
        ResultModel<List<String>> resultModel = new ResultModel<List<String>>();
        List<String> didList = checkStatus();
        //檢查是否為空,空則已完成,反之則尚未完成
        if(!(didList.isEmpty())) {
            resultModel.setData(didList);
            resultModel.setCode(201);
        }
        else {
            resultModel.setCode(200);
        }
        resultModel.setMsg("檢查成功");
        return resultModel;
    }

    //批量增加新紀錄
    @Override
    public ResultModel addInBatch(List<String> didList) {
        ResultModel resultModel = new ResultModel();
        java.sql.Date myDate = findFeeDate(0);
        //didList轉為List<Fee>
        List<Fee> addList = didList.stream().map(id -> new Fee(id, myDate, 0)).collect(Collectors.toList());
        feeMapper.addInBatch(addList);
        resultModel.setCode(200);
        resultModel.setMsg("添加成功");
        return resultModel;
    }

    @Override
    public ResultModel updateStatus(FeeDTO feeDTO) {
        ResultModel resultModel = new ResultModel();
        Fee fee = new Fee();
        fee.setFeeDate(feeDTO.getFeeDate());
        fee.setDid(feeDTO.getDid());
        fee.setStatus(1);   //設為已繳清
        feeMapper.updateStatus(fee);
        resultModel.setCode(200);
        resultModel.setMsg("繳費成功");
        return resultModel;
    }


}
