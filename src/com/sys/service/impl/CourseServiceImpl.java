package com.sys.service.impl;

import com.sys.bean.Course;
import com.sys.bean.Student;
import com.sys.dao.impl.CourseDaoImpl;
import com.sys.dao.impl.StudentDaoImpl;
import com.sys.service.interfaces.CourseService;
import com.sys.util.OperationResult;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    public OperationResult<List<Course>> selectAll(String text) {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        try {
            return new OperationResult<List<Course>>(0, "查询成功",courseDao.selectAll(text));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<List<Course>>(1, "查询失败");
        }
    }

    public OperationResult<Integer> addCourse(Course course) {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        try {
            return new OperationResult<>(0, "修改成功", courseDao.addCourse(course));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "修改失败");
        }
    }

    public OperationResult<Integer> delCourse(String cno) {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        try {
            return new OperationResult<>(0, "删除成功", courseDao.delCourse(cno));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "删除失败");
        }
    }

    public OperationResult<Integer> editCourse(Course course) {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        try {
            return new OperationResult<>(0, "修改成功", courseDao.editCourse(course));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "修改失败");
        }
    }



}
