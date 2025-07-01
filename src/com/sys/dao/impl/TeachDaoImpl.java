package com.sys.dao.impl;

import com.sys.bean.Teach;
import com.sys.dao.interfaces.TeachDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class TeachDaoImpl implements TeachDao {
    public List<Teach> selectAll() throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from teach";
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teach.class);
    }
}