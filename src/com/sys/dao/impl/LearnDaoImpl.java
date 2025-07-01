package com.sys.dao.impl;

import com.sys.bean.Learn;
import com.sys.bean.Teach;
import com.sys.dao.interfaces.LearnDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class LearnDaoImpl implements LearnDao {
    public List<Learn> selectAll() throws SQLException, IllegalAccessException, InstantiationException
    {
    	String sql = "select * from learn";
    	return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Learn.class);
    }

    public List<Learn> selectAllWithName() throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from teachwithname";
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Learn.class);
    }

}
