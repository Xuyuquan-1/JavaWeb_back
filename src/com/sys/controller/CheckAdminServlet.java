package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Admin;
import com.sys.bean.Teacher;
import com.sys.service.impl.AdminServiceImpl;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CheckAdminServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        // 读取 JSON 请求体
        StringBuilder jsonBody = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            System.out.println("line: " + line);
            jsonBody.append(line);
        }

        String jsonString = jsonBody.toString();
//        System.out.println("Received JSON: " + jsonString);

        // 将 JSON 转换为 admin 对象
        Admin admin = JSON.parseObject(jsonString, Admin.class);

        // 打印 admin 对象信息
        if (admin != null) {
            System.out.println("管理员姓名：" + admin.getAname());
            System.out.println("管理员账号：" + admin.getAaccount());
        }
        AdminServiceImpl adminService = new AdminServiceImpl();
        OperationResult<List<Admin>> result = null;
        try {
            result = adminService.checkAdmin(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(result.getStatuscode() == 0){
            System.out.println("登录成功");
        }


        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
