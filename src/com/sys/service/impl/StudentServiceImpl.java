package com.sys.service.impl;

import com.sys.bean.Student;
import com.sys.dao.impl.StudentDaoImpl;
import com.sys.service.interfaces.StudentService;
import com.sys.util.OperationResult;

import java.sql.SQLException;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    public OperationResult<List<Student>>  selectAll(String text) throws SQLException, IllegalAccessException, InstantiationException {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        try {
            return new OperationResult<>(0, "查询成功", studentDao.selectAll(text));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "查询失败");
        }

    }
}
