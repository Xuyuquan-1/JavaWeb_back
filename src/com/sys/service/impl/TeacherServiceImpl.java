package com.sys.service.impl;

import com.sys.bean.Teacher;
import com.sys.dao.impl.TeacherDaoImpl;
import com.sys.dao.interfaces.TeacherDao;
import com.sys.service.interfaces.TeacherService;
import com.sys.util.OperationResult;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    public OperationResult<List<Teacher>> selectAll(String text) throws SQLException, IllegalAccessException, InstantiationException {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
//        teacherDao.getCnoByCname("数据结构");
        try {

            return new OperationResult<> (0, "查询成功", teacherDao.selectAllwithCourseName(text));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "查询失败");
        }
    }

    public OperationResult<Integer> addTeacher(Teacher teacher)
    {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        try {
            return new OperationResult<> (0, "添加成功", teacherDao.addTeacher(teacher));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "添加失败");
        }
    }
}
