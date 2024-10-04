package com.neu.service;

import com.neu.utils.ResultModel;

public interface DormitoryService {
    public ResultModel findRemain(int gender);
    public ResultModel findByDid();
}
