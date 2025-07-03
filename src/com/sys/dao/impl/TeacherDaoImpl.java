package com.sys.dao.impl;

import com.sys.bean.Teacher;
import com.sys.dao.interfaces.TeacherDao;
import com.sys.util.JdbcUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements TeacherDao {
    public List<Teacher>  selectAll(String text) throws SQLException, IllegalAccessException, InstantiationException {
        //不需要加分号，jdbc会帮我们处理结束符
        String sql = "select * from teacher ";
        List<String> params = new ArrayList<>();

        if (text != "") {
            sql += "where tname like '%" + text + "%'";
            params.add("%" + text + "%");
        }

//        for (Teacher teacher : JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teacher.class)) {
//            System.out.println(teacher.getTname());
//            System.out.println(teacher.getTno());
//        }
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, params.toArray()), Teacher.class);
    }

    public List<Teacher> selectAllwithCourseName(String text) throws SQLException, IllegalAccessException, InstantiationException {

        String sql = "select * from teacherwithcoursename ";
        if (text != "") {
            sql += "where tname like '%" + text + "%'";
            System.out.print(sql);
        }
        for (Teacher teacher : JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teacher.class)) {
            System.out.println(teacher.getTname());
            System.out.println(teacher.getTno());
        }
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Teacher.class);

    }

    public String getCnoByCname(String cname) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "SELECT cno FROM course WHERE cname = ?";
        List<Teacher> templ = JdbcUtils.convertResultSetToList(JdbcUtils.query(sql, cname), Teacher.class);

        if (templ == null || templ.isEmpty()) {
            throw new SQLException("未找到课程名对应的cno: " + cname);
        }
        return templ.get(0).getCno();

    }


    public int addTeacher(Teacher teacher) throws SQLException, IllegalAccessException, InstantiationException {
        String tempcno = getCnoByCname(teacher.getCname());

        String sql = "insert into teacher(tno,cno,tname,taccount,tpwd,ttel) values(?,?,?,?,?,?)";

        return JdbcUtils.update(sql, teacher.getTno(),tempcno,teacher.getTname(),teacher.getTaccount(),teacher.getTpwd(),teacher.getTtel());
    }
}
