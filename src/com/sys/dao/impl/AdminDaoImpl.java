package com.sys.dao.impl;

import com.sys.bean.Admin;
import com.sys.dao.interfaces.AdminDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {
    public List<Admin> checkAdmin(Admin  admin) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from admin where aaccount = ? and apwd = ?";
        List<Admin> list = null;
        list = JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, admin.getAaccount(), admin.getApwd()), Admin.class);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }
}
