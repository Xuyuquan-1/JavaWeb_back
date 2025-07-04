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

    public int addCourse(Course course) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "insert into course values(?,?,?,?)";
        return JdbcUtils.update(sql, course.getCno(),course.getCname(),course.getCsdate(),course.getCedate());
    }

    public int delCourse(String cno) {
        String sql = "delete from course where cno = ?";
        String deletesql = "delete from learn where cno = ?";

        JdbcUtils.update(deletesql, cno);

        return JdbcUtils.update(sql, cno);
    }

    public int editCourse(Course course) {
        String sql = "update course set cname = ?,csdate = ?,cedate = ? where cno = ?";
        return JdbcUtils.update(sql, course.getCname(),course.getCsdate(),course.getCedate(),course.getCno());
    }


    public  Course getCourseByCno(String cname) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from course where cname = ?";
        List<Course> list = JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, cname), Course.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
