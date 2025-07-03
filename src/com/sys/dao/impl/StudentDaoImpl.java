package com.sys.dao.impl;

import com.sys.bean.Student;
import com.sys.bean.Teacher;
import com.sys.dao.interfaces.StudentDao;
import com.sys.util.JdbcUtils;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
//    public List<Student> selectAll() throws SQLException, IllegalAccessException, InstantiationException
//    {
//    	String sql = "select * from student";
//    	return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Student.class);
//    }

    public List<Student> selectAll(String text) throws SQLException, IllegalAccessException, InstantiationException {

        String sql = "select * from student ";
        if (text != "") {
            sql += "where sname like '%" + text + "%'";
            System.out.print(sql);
        }
        for (Student student: JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Student.class)) {
            System.out.println(student.getSname());
            System.out.println(student.getSno());
        }
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Student.class);

    }
}
