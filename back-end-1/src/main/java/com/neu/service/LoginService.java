package com.neu.service;

import com.neu.dto.AccountDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.BuildingVO;
import com.neu.vo.StudentVO;

public interface LoginService {
    public ResultModel<StudentVO> StudentLogin(AccountDTO student);
    public ResultModel<BuildingVO> ManagerLogin(AccountDTO manager);
    public ResultModel AdminLogin(AccountDTO admin);
    public ResultModel MaintainLogin(AccountDTO maintain);
}
