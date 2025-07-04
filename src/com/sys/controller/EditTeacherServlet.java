package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Teacher;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class EditTeacherServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 提取所有参数
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
        if (teacher != null) {
            System.out.println("工号：" + teacher.getTno());
            System.out.println("电话：" + teacher.getTtel());
            System.out.println("账号：" + teacher.getTaccount());
            System.out.println("密码：" + teacher.getTpwd());
        }
        else {
            System.out.println("edit 参数为空!!!");
            return;
        }
        TeacherServiceImpl teacherService = new TeacherServiceImpl();
        teacherService.editTeacher(teacher);
//        OperationResult<Integer> result = teacherService.editTeacher(teacher);
//        System.out.println("edit结果：" + result.getStatuscode());

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
