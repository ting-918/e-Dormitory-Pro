package com.neu.service;

import com.neu.utils.ResultModel;
import com.neu.vo.CheckInVO;
import com.neu.vo.CheckOutVO;

import java.util.List;

public interface ApplyService {
    public ResultModel<List<CheckInVO>> findAllCheckIn();
    public ResultModel<List<CheckOutVO>> findAllCheckOut();
    public ResultModel<Integer> ValidApply(String id, int type);
    public ResultModel AddcheckIn(String id);
    public ResultModel AddcheckOut(String id, String reason);
    public ResultModel Refuse(String id, int type);
}
