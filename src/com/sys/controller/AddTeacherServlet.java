package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Teacher;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.JsonDateUtil;
import com.sys.util.OperationResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class AddTeacherServlet extends HttpServlet {
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
        Teacher teacher = JSON.parseObject(jsonString, Teacher.class);

        // 打印 Teacher 对象信息
//        if (teacher != null) {
//            System.out.println("教师姓名：" + teacher.getTname());
//            System.out.println("教师工号：" + teacher.getTno());
//            System.out.println("电话：" + teacher.getTtel());
//            System.out.println("账号：" + teacher.getTaccount());
//            System.out.println("密码：" + teacher.getTpwd());
//        }
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        OperationResult<Integer> result = teacherService.addTeacher(teacher);
        System.out.println("添加结果：" + result.getStatuscode());

        OperationResult<List<Teacher>>  res = null;
        // 返回响应
        try {
            res = teacherService.selectAll("");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(JSON.toJSONString(res));
        response.getWriter().flush();
    }
}
