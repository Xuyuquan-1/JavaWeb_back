package com.sys.dao.impl;

import com.sys.bean.Student;
import com.sys.dao.interfaces.StudentDao;
import com.sys.util.JdbcUtils;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    public List<Student> selectAll() throws SQLException, IllegalAccessException, InstantiationException
    {
    	String sql = "select * from student";
    	return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Student.class);
    }
}
