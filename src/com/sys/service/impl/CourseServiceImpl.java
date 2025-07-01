package com.sys.service.impl;

import com.sys.bean.Course;
import com.sys.dao.impl.CourseDaoImpl;
import com.sys.service.interfaces.CourseService;
import com.sys.util.OperationResult;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    public OperationResult<List<Course>> selectAll() {
        CourseDaoImpl courseDao = new CourseDaoImpl();
        try {
            return new OperationResult<List<Course>>(0, "查询成功",courseDao.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<List<Course>>(1, "查询失败");
        }
    }

}
