package com.sys.dao.impl;

import com.sys.bean.Teacher;
import com.sys.dao.interfaces.TeacherDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    public List<Teacher>  selectAll() throws SQLException, IllegalAccessException, InstantiationException {
        //不需要加分号，jdbc会帮我们处理结束符
        String sql = "select * from teacher";
//        for (Teacher teacher : JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teacher.class)) {
//            System.out.println(teacher.getTname());
//            System.out.println(teacher.getTno());
//        }
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teacher.class);
    }
}
