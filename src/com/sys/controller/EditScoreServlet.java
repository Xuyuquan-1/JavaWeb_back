package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Learn;
import com.sys.service.impl.LearnServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditScoreServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
        Learn learn = JSON.parseObject(jsonString, Learn.class);

        // 打印 Teacher 对象信息

        if (learn != null) {
//            System.out.println("学号：" + learn.getSno());
//            System.out.println("课程号：" + learn.getCno());
            System.out.println("score：" + learn.getScore());
            System.out.println("sname：" + learn.getSname());
            System.out.println("cname：" + learn.getCname());
        }

        LearnServiceImpl learnService = new LearnServiceImpl();
        OperationResult<Integer> result = null;
        try {
            result = learnService.editLearn(learn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("添加结果：" + result.getStatuscode());

        OperationResult<List<Learn>>  res = null;
        // 返回响应
        try {
            res = learnService.selectAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(JSON.toJSONString(res));
        response.getWriter().flush();
    }
}
