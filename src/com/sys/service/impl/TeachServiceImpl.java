package com.sys.service.impl;

import com.sys.bean.Teach;
import com.sys.dao.impl.TeachDaoImpl;
import com.sys.service.interfaces.TeachSerivice;
import com.sys.util.OperationResult;

import java.sql.SQLException;
import java.util.List;

public class TeachServiceImpl implements TeachSerivice {
    public OperationResult<List<Teach>> selectAll() {
        TeachDaoImpl teachDao = new TeachDaoImpl();
        try {
            return new OperationResult<>(0, "操作成功",teachDao.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<>(1, "操作失败");

        }
    }
}
