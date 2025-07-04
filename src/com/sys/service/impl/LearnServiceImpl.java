package com.sys.service.impl;

import com.sys.bean.Course;
import com.sys.bean.Learn;
import com.sys.dao.impl.CourseDaoImpl;
import com.sys.dao.impl.LearnDaoImpl;
import com.sys.dao.impl.StudentDaoImpl;
import com.sys.service.interfaces.LearnService;
import com.sys.util.OperationResult;

import java.sql.SQLException;
import java.util.List;

public class LearnServiceImpl implements LearnService {
    public OperationResult<List<Learn>> selectAll() {
        LearnDaoImpl learnDao = new LearnDaoImpl();
        try {
            return new OperationResult<List<Learn>>(0, "查询成功", learnDao.selectAllWithName());
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<List<Learn>>(1, "查询失败");
        }
    }

    public OperationResult<List<Learn>> selectAllByCname(String text) {
        LearnDaoImpl learnDao = new LearnDaoImpl();
        try {
            return new OperationResult<List<Learn>>(0, "查询成功", learnDao.selectAllByCname(text));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<List<Learn>>(1, "查询失败");
        }
    }

    public OperationResult<Integer> addLearn(Learn learn) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CourseDaoImpl courseDao = new CourseDaoImpl();
        LearnDaoImpl learnDao = new LearnDaoImpl();
        if (learn ==  null) {
            return new OperationResult<Integer>(1, "添加失败,learn为null");
        }

        try {


            System.out.println("1:"+learn.getCno());
            System.out.println("2:"+learn.getSno());
            System.out.println("3:"+learn.getScore());
            System.out.println("4:"+learn.getCname());
            System.out.println("5:"+learn.getSname());

            String sno = studentDao.getStudentBySname(learn.getSname()).getSno();
            String cno = courseDao.getCourseByCno(learn.getCname()).getCno();

            learn.setCno(cno);
            learn.setSno(sno);


            return new OperationResult<Integer>(0, "添加成功", learnDao.addLearn(learn));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<Integer>(1, "添加失败，未知错误");
        }
    }

    public OperationResult<Integer> editLearn(Learn  learn) throws SQLException, IllegalAccessException, InstantiationException {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CourseDaoImpl courseDao = new CourseDaoImpl();
        LearnDaoImpl learnDao = new LearnDaoImpl();
        if (learn ==  null) {
            return new OperationResult<Integer>(1, "修改失败,learn为null");
        }

        try {
            String sno = studentDao.getStudentBySname(learn.getSname()).getSno();
            String cno = courseDao.getCourseByCno(learn.getCname()).getCno();
            learn.setSno(sno);
            learn.setCno(cno);
        } catch (Exception e) {
            return new OperationResult<Integer>(1, "修改失败,学号或课程不存在");
        }
        return new OperationResult<Integer>(0, "修改成功", learnDao.editLearn(learn));
    }

    public OperationResult<Integer> delLearn(Learn  learn) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        CourseDaoImpl courseDao = new CourseDaoImpl();
        LearnDaoImpl learnDao = new LearnDaoImpl();

        try {
            String sno = studentDao.getStudentBySname(learn.getSname()).getSno();
            String cno = courseDao.getCourseByCno(learn.getCname()).getCno();
            learn.setSno(sno);
            learn.setCno(cno);

            if (cno == null || sno == null) {
                return new OperationResult<Integer>(1, "删除失败,学号或课程不存在");
            }
        } catch (Exception e) {
            return new OperationResult<Integer>(1, "删除失败,学号或课程不存在");
        }
        return new OperationResult<Integer>(0, "删除成功", learnDao.delLearn(learn));
    }



}
