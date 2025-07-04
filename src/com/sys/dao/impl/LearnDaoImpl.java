package com.sys.dao.impl;

import com.sys.bean.Learn;
import com.sys.bean.Student;
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

    public List<Learn> selectAllByCname(String text) throws SQLException, IllegalAccessException, InstantiationException {
        String sql = "select * from teachwithname where cname like '%"+text+"%'";
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Learn.class);
    }

    public int addLearn(Learn learn) throws SQLException, IllegalAccessException, InstantiationException
    {
    	String sql = "insert into learn(sno, cno, score) values(?,?,?)";
    	return JdbcUtils.update(sql, learn.getSno(), learn.getCno(), learn.getScore());
    }

    public int editLearn(Learn learn)
    {
    	String sql = "update learn set score=? where sno=? and cno=?";
    	return JdbcUtils.update(sql, learn.getScore(), learn.getSno(), learn.getCno());
    }

    public int delLearn(Learn learn)
    {
    	String sql = "delete from learn where sno=? and cno=?";
        return JdbcUtils.update(sql, learn.getSno(), learn.getCno());
    }

    public List<Learn> selectAllByPersonal(String account) throws SQLException, IllegalAccessException, InstantiationException {
        String name = JdbcUtils.convertResultSetToList(JdbcUtils.query("select * from student where saccount="+ "\'"+account+"\'"), Student.class).get(0).getSname();
        String sql = "select * from teachwithname where sname="+ "\'"+name+"\'";
        System.out.println("Daoname: "+ name);
        return JdbcUtils.convertResultSetToList(JdbcUtils.query(sql), Learn.class);
    }

}
