package com.neu.service.impl;

import com.neu.domain.Maintain;
import com.neu.dto.MaintainDTO;
import com.neu.mapper.MaintainMapper;
import com.neu.service.MaintainService;
import com.neu.utils.ResultModel;
import com.neu.vo.MaintainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MaintainServiceImpl implements MaintainService {
    @Autowired
    MaintainMapper maintainMapper;

    //查詢所有報修紀錄
    @Override
    public ResultModel<List<MaintainVO>> findAll() {
        ResultModel<List<MaintainVO>> resultModel = new ResultModel<List<MaintainVO>>();
        List<MaintainVO> maintainVOList = maintainMapper.findAll();
        resultModel.setData(maintainVOList);
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查找指定寢室的報修紀錄
    @Override
    public ResultModel<List<MaintainVO>> findByDid(String did) {
        ResultModel<List<MaintainVO>> resultModel = new ResultModel<List<MaintainVO>>();
        List<MaintainVO> maintainVOList = maintainMapper.findByDid(did);
        resultModel.setData(maintainVOList);
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //查找指定樓棟的報修紀錄
    @Override
    public ResultModel<List<MaintainVO>> findByBid(String bid) {
        ResultModel<List<MaintainVO>> resultModel = new ResultModel<List<MaintainVO>>();
        List<MaintainVO> maintainVOList = maintainMapper.findByBid(bid);
        resultModel.setData(maintainVOList);
        resultModel.setCode(200);
        resultModel.setMsg("查詢成功");
        return resultModel;
    }

    //申請報修
    @Override
    public ResultModel Add(MaintainDTO maintainDTO) {
        ResultModel resultModel = new ResultModel();
        //獲取當前時間,並轉為sqlDate形式
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //構造實體類
        Maintain maintain = new Maintain();
        maintain.setDid(maintainDTO.getDid());
        maintain.setItem(maintainDTO.getItem());
        maintain.setReason(maintainDTO.getReason());
        maintain.setPhone(maintainDTO.getPhone());
        maintain.setApplytime(sqlDate);
        //添加至數據庫
        maintainMapper.add(maintain);
        resultModel.setCode(200);
        resultModel.setMsg("提交成功");
        return resultModel;
    }

    //完成維修
    @Override
    public ResultModel Complete(int id) {
        ResultModel resultModel = new ResultModel();
        //獲取當前時間,並轉為sqlDate形式
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        //構造實體類
        Maintain maintain = new Maintain();
        maintain.setId(id);
        maintain.setHandletime(sqlDate);
        //數據庫進行修改更新
        maintainMapper.complete(maintain);
        resultModel.setCode(200);
        resultModel.setMsg("更新成功");
        return resultModel;
    }

}
