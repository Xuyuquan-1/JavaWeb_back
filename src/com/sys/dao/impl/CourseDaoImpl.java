package com.sys.dao.impl;

import com.sys.bean.Course;
import com.sys.dao.interfaces.CourseDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    public List<Course> selectAll() throws SQLException, IllegalAccessException, InstantiationException
    {
        String sql = "select * from course";
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Course.class);
    }
}
