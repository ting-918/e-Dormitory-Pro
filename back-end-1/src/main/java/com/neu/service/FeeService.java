package com.neu.service;

import com.neu.dto.FeeDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.BillVO;
import com.neu.vo.FeeVO;

import java.util.List;

public interface FeeService {
    public ResultModel<List<FeeVO>> findAll();
    public ResultModel<List<FeeVO>> findByDid(String did);
    public ResultModel<List<FeeVO>> findByBid(String bid);
    public ResultModel<List<String>> check();
    public ResultModel addInBatch(List<String> didList);
    public ResultModel updateStatus(FeeDTO feeDTO);
}
