package com.sys.service.impl;

import com.sys.bean.Teacher;
import com.sys.dao.impl.TeacherDaoImpl;
import com.sys.dao.interfaces.TeacherDao;
import com.sys.service.interfaces.TeacherService;
import com.sys.util.OperationResult;

import java.sql.SQLException;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    public OperationResult<List<Teacher>> selectAll() throws SQLException, IllegalAccessException, InstantiationException {
        TeacherDaoImpl teacherDao = new TeacherDaoImpl();
        try {

//            for (Teacher teacher : teacherDao.selectAll())
//            System.out.println(teacher.getCno());
            return new OperationResult<> (0, "查询成功", teacherDao.selectAll());
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "查询失败");
        }
    }
}
