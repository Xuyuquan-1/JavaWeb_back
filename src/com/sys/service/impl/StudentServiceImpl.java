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

    public OperationResult<Integer> addStudent(Student student) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        try {
            return new OperationResult<>(0, "添加成功", studentDao.addStudent(student));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "添加失败");
        }
    }

    public OperationResult<Integer> delStudent(String sno) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        try {
            return new OperationResult<>(0, "删除成功", studentDao.delStudent(sno));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "删除失败");
        }
    }
    public OperationResult<Integer> editStudent(Student student) {
        StudentDaoImpl studentDao = new StudentDaoImpl();
        try {
            return new OperationResult<>(0, "修改成功", studentDao.editStudent(student));
        } catch (Exception e) {
            e.printStackTrace();
            return new OperationResult<> (1, "修改失败");
        }
    }
}
