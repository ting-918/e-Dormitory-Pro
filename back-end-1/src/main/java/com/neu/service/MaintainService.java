package com.neu.service;

import com.neu.dto.MaintainDTO;
import com.neu.utils.ResultModel;
import com.neu.vo.MaintainVO;

import java.util.List;

public interface MaintainService {
    public ResultModel<List<MaintainVO>> findAll();
    public ResultModel<List<MaintainVO>> findByDid(String did);
    public ResultModel<List<MaintainVO>> findByBid(String bid);
    public ResultModel Add(MaintainDTO maintainDTO);
    public ResultModel Complete(int id);
}
