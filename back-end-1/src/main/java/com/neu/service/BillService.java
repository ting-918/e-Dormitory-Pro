package com.neu.service;

import com.neu.dto.BillDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.BillVO;

import java.util.List;

public interface BillService {
    public ResultModel<List<BillVO>> findAll();
    public ResultModel<List<BillVO>> findById(String id);
    public ResultModel check();
    public ResultModel addInBatch(BillDTO billDTO);
    public ResultModel updateStatus(BillDTO billDTO);
}
