package com.neu.service.impl;

import com.neu.dto.AccountDTO;
import com.neu.mapper.BuildingMapper;
import com.neu.mapper.StudentMapper;
import com.neu.service.LoginService;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;
import com.neu.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    BuildingMapper buildingMapper;

    @Override
    public ResultModel<StudentVO> StudentLogin(AccountDTO student) {
        ResultModel<StudentVO> resultModel = new ResultModel<StudentVO>();
        StudentVO account = studentMapper.findById(student.getId());
        if (account == null) {
            resultModel.setMsg("帳號不存在");
            resultModel.setCode(401);
            return resultModel;
        }
        if (!(account.getPwd().equals(student.getPwd()))) {
            resultModel.setMsg("密碼錯誤");
            resultModel.setCode(402);
            return resultModel;
        }
        resultModel.setData(account);
        resultModel.setCode(200);
        resultModel.setMsg("登入成功");
        return resultModel;
    }

    @Override
    public ResultModel<BuildingVO> ManagerLogin(AccountDTO manager) {
        ResultModel<BuildingVO> resultModel = new ResultModel<BuildingVO>();
        BuildingVO account = buildingMapper.findByBid(manager.getId());
        if (account == null) {
            resultModel.setMsg("帳號不存在");
            resultModel.setCode(401);
            return resultModel;
        }
        if (!(account.getPwd().equals(manager.getPwd()))) {
            resultModel.setMsg("密碼錯誤");
            resultModel.setCode(402);
            return resultModel;
        }
        resultModel.setData(account);
        resultModel.setCode(200);
        resultModel.setMsg("登入成功");
        return resultModel;
    }

    @Override
    public ResultModel AdminLogin(AccountDTO admin) {
        ResultModel resultModel = new ResultModel();
        if (!(admin.getId().equals("admin"))){
            resultModel.setMsg("帳號不存在");
            resultModel.setCode(401);
            return resultModel;
        }
        if (!(admin.getPwd().equals("jnu999"))){
            resultModel.setMsg("密碼錯誤");
            resultModel.setCode(402);
            return resultModel;
        }
        resultModel.setCode(200);
        resultModel.setMsg("登入成功");
        return resultModel;
    }

    @Override
    public ResultModel<Boolean> MaintainLogin(AccountDTO maintain) {
        ResultModel resultModel = new ResultModel();
        if (!(maintain.getId().equals("dantian"))){
            resultModel.setMsg("帳號不存在");
            resultModel.setCode(401);
            return resultModel;
        }
        if (!(maintain.getPwd().equals("dantian999"))) {
            resultModel.setMsg("密碼錯誤");
            resultModel.setCode(402);
            return resultModel;
        }
        resultModel.setCode(200);
        resultModel.setMsg("登入成功");
        return resultModel;
    }
}
