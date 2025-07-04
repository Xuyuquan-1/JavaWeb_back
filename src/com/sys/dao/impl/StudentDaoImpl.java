package com.sys.dao.impl;

import com.sys.bean.Admin;
import com.sys.bean.Student;
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

    public int addStudent(Student student) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "insert into student values(?,?,?,?,?)";
        return JdbcUtils.update(sql, student.getSno(), student.getSname(), student.getStel(), student.getSaccount(), student.getSpwd());

    }

    public int delStudent(String sno) {
        String sql = "delete from student where sno = ?";
        String deleteLearnSql = "DELETE FROM learn WHERE sno = ?";
        String deleteTeachSql = "DELETE FROM teach WHERE sno = ?";

        // 先删除 teach 和 learn 表中的关联数据
        JdbcUtils.update(deleteTeachSql, sno);
        JdbcUtils.update(deleteLearnSql, sno);

        return JdbcUtils.update(sql, sno);
    }


    public int editStudent(Student student) {
        String sql = "update student set sname = ?,stel = ?,saccount = ?,spwd = ? where sno = ?";
        return JdbcUtils.update(sql, student.getSname(), student.getStel(), student.getSaccount(), student.getSpwd(), student.getSno());
    }

    public Student getStudentBySname(String sname) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from student where sname = ?";
        List<Student> list = JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, sname), Student.class);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public List<Student> checkStudent(Student  student) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from student where saccount = ? and spwd = ?";
        List<Student> list = null;
        list = JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, student.getSaccount(), student.getSpwd()), Student.class);
        if(list.isEmpty()) {
            return null;
        }
        return list;
    }
}
