package com.neu.controller;

import com.neu.dto.AccountDTO;
import com.neu.service.LoginService;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;
import com.neu.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//登入
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/student")
    public ResultModel<StudentVO> StudentLogin(@RequestBody AccountDTO accountDTO) {
        return loginService.StudentLogin(accountDTO);
    }
    @PostMapping("/manager")
    public ResultModel<BuildingVO> ManagerLogin(@RequestBody AccountDTO accountDTO) {
        return loginService.ManagerLogin(accountDTO);
    }
    @PostMapping("/admin")
    public ResultModel AdminLogin(@RequestBody AccountDTO accountDTO) {
        return loginService.AdminLogin(accountDTO);
    }
    @PostMapping("/maintain")
    public ResultModel MaintainLogin(@RequestBody AccountDTO accountDTO) {
        return loginService.MaintainLogin(accountDTO);
    }
}

