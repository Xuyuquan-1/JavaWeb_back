package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Student;
import com.sys.bean.Teacher;
import com.sys.service.impl.StudentServiceImpl;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 读取 JSON 请求体
        StringBuilder jsonBody = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonBody.append(line);
        }

        String jsonString = jsonBody.toString();
//        System.out.println("Received JSON: " + jsonString);

        // 将 JSON 转换为 Teacher 对象
        Student student = JSON.parseObject(jsonString, Student.class);

        // 打印 Teacher 对象信息

        if (student != null) {
            System.out.println("学生姓名：" + student.getSname());
            System.out.println("学生学号：" + student.getSno());
            System.out.println("电话：" + student.getStel());
            System.out.println("账号：" + student.getSaccount());
            System.out.println("密码：" + student.getSpwd());
        }

        StudentServiceImpl studentService = new StudentServiceImpl();
        OperationResult<Integer> result = studentService.addStudent(student);
        System.out.println("添加结果：" + result.getStatuscode());

        OperationResult<List<Student>>  res = null;
        // 返回响应
        try {
            res = studentService.selectAll("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(JSON.toJSONString(res));
        response.getWriter().flush();
    }
}
