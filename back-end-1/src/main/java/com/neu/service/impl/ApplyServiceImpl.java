package com.neu.service.impl;

import com.neu.domain.CheckIn;
import com.neu.domain.CheckOut;
import com.neu.mapper.CheckInMapper;
import com.neu.mapper.CheckOutMapper;
import com.neu.service.ApplyService;
import com.neu.utils.ResultModel;
import com.neu.vo.CheckInVO;
import com.neu.vo.CheckOutVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    CheckInMapper checkInMapper;
    @Autowired
    CheckOutMapper checkOutMapper;

    //查詢所有待審核的入住申請
    @Override
    public ResultModel<List<CheckInVO>> findAllCheckIn() {
        ResultModel resultModel = new ResultModel();
        resultModel.setData(checkInMapper.findAll());
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查詢所有待審核的入住申請
    @Override
    public ResultModel<List<CheckOutVO>> findAllCheckOut() {
        ResultModel resultModel = new ResultModel();
        resultModel.setData(checkOutMapper.findAll());
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查詢指定學生是否符合申請資格
    @Override
    public ResultModel<Integer> ValidApply(String id, int type) {
        ResultModel<Integer> resultModel = new ResultModel<Integer>();
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        if(type == 0) {
            CheckInVO check = checkInMapper.findById(id);
            if (check == null)
                resultModel.setData(1); //可以申請
            else {
                if (check.getStatus() == 1)
                    resultModel.setData(2); //等待審批
                else
                    resultModel.setData(0); //不符合資格
            }
        }
        else {
            CheckOutVO check = checkOutMapper.findById(id);
            if (check == null)
                resultModel.setData(1); //可以申請
            else {
                if (check.getStatus() == 1)
                    resultModel.setData(2); //等待審批
                else
                    resultModel.setData(0); //不符合資格
            }
        }
        return resultModel;
    }

    //提交入住申請
    @Override
    public ResultModel AddcheckIn(String id) {
        ResultModel resultModel = new ResultModel();
        CheckIn checkIn = new CheckIn();
        checkIn.setStudentid(id);
        checkIn.setStatus(1);
        checkInMapper.add(checkIn);
        resultModel.setCode(200);
        resultModel.setMsg("提交成功,等待審核");
        return resultModel;
    }

    //提交退宿申請
    @Override
    public ResultModel AddcheckOut(String id, String reason) {
        ResultModel resultModel = new ResultModel();
        CheckOut checkOut = new CheckOut();
        checkOut.setStudentid(id);
        checkOut.setReason(reason);
        checkOut.setStatus(1);
        checkOutMapper.add(checkOut);
        resultModel.setCode(200);
        resultModel.setMsg("提交成功,等待審核");
        return resultModel;
    }

    @Override
    public ResultModel Refuse(String id, int type) {
        ResultModel resultModel = new ResultModel();
        //駁回入住申請
        if(type == 0)
            checkInMapper.deleteById(id);
        //駁回退宿申請
        if(type == 1)
            checkOutMapper.deleteById(id);
        else {
            resultModel.setCode(350);
            resultModel.setMsg("駁回失敗, 請重新操作");
            return resultModel;
        }
        resultModel.setCode(200);
        resultModel.setMsg("成功駁回");
        return resultModel;
    }
}
