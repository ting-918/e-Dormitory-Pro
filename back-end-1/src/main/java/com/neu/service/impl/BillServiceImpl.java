package com.neu.service.impl;

import com.neu.domain.Bill;
import com.neu.dto.BillDTO;
import com.neu.mapper.BillMapper;
import com.neu.mapper.StudentMapper;
import com.neu.service.BillService;
import com.neu.utils.ResultModel;
import com.neu.vo.BillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillMapper billMapper;

    @Autowired
    StudentMapper studentMapper;

    private String getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        if(calendar.get(Calendar.MONTH)+1 < 7)
            return String.valueOf(calendar.get(Calendar.YEAR)-1);
        else
            return String.valueOf(calendar.get(Calendar.YEAR));
    }

    //查詢當年度所有繳費信息
    @Override
    public ResultModel<List<BillVO>> findAll() {
        ResultModel resultModel = new ResultModel();

        List<BillVO> billVOList = billMapper.findAll(getCurrentYear());
        resultModel.setData(billVOList);
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查詢指定id的所有繳費記錄
    @Override
    public ResultModel<List<BillVO>> findById(String id) {
        ResultModel<List<BillVO>> resultModel = new ResultModel<List<BillVO>>();
        Bill bill = new Bill();
        bill.setStudentid(id);
        resultModel.setData(billMapper.findById(bill));
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }
    //檢查是否未完成本年度費用通知工作
    @Override
    public ResultModel<List<String>> check() {
        ResultModel<List<String>> resultModel = new ResultModel<List<String>>();
        List<BillVO> idList = billMapper.check(getCurrentYear());
        //檢查是否為空,空則尚未完成,反之則已完成
        if(!(idList.isEmpty())) {
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
    public ResultModel addInBatch(BillDTO billDTO) {
        ResultModel resultModel = new ResultModel();
        Bill bill = new Bill();
        bill.setYear(billDTO.getYear());
        bill.setPrice(billDTO.getPrice());
        billMapper.addInBatch(bill);
        resultModel.setCode(200);
        resultModel.setMsg("添加成功");
        return resultModel;
    }

    //繳費
    @Override
    public ResultModel updateStatus(BillDTO billDTO) {
        ResultModel resultModel = new ResultModel();
        Bill bill = new Bill();
        bill.setStudentid(billDTO.getStudentid());
        bill.setYear(billDTO.getYear());
        bill.setStatus(1); //設為已繳清
        billMapper.updateStatus(bill);
        resultModel.setCode(200);
        resultModel.setMsg("繳費成功");
        return resultModel;
    }

}
