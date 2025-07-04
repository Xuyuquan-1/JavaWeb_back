package com.sys.service.impl;

import com.sys.bean.Admin;
import com.sys.dao.impl.AdminDaoImpl;
import com.sys.service.interfaces.AdminService;
import com.sys.util.OperationResult;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    public OperationResult<List<Admin>> checkAdmin(Admin admin) throws Exception {
        AdminDaoImpl adminDao = new AdminDaoImpl();
        List<Admin> list = null;
        try {
            list = adminDao.checkAdmin(admin);
            if(!list.isEmpty()) {
                return new OperationResult<List<Admin>>(0, "check success", list);
            }
            else {
                return new OperationResult<List<Admin>>(1, "check fail", list);
            }
        } catch (Exception e) {
            return new OperationResult<List<Admin>>(1, "未知错误");
        }
    }
}
