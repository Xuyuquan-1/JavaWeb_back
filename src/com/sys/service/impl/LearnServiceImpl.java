package com.sys.service.impl;

import com.sys.bean.Learn;
import com.sys.dao.impl.LearnDaoImpl;
import com.sys.service.interfaces.LearnService;
import com.sys.util.OperationResult;

import java.util.List;

public class LearnServiceImpl implements LearnService {
    public OperationResult<List<Learn>> selectAll() {
        LearnDaoImpl learnDao = new LearnDaoImpl();
        try {
            return new OperationResult<List<Learn>>(0, "查询成功", learnDao.selectAllWithName());
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<List<Learn>>(1, "查询失败");
        }
    }

}
