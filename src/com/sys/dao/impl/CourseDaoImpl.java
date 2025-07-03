package com.sys.dao.impl;

import com.sys.bean.Course;
import com.sys.bean.Student;
import com.sys.dao.interfaces.CourseDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
//    public List<Course> selectAll() throws SQLException, IllegalAccessException, InstantiationException
//    {
//        String sql = "select * from course";
//        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Course.class);
//    }

    public List<Course> selectAll(String text) throws SQLException, IllegalAccessException, InstantiationException {

        String sql = "select * from course ";
        if (text != "") {
            sql += "where cname like '%" + text + "%'";
            System.out.print(sql);
        }
        for (Course course: JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Course.class)) {
            System.out.println(course.getCname());
            System.out.println(course.getCno());
        }
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Course.class);

    }
}
