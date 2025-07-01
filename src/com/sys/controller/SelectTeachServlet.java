package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Teach;
import com.sys.service.impl.TeachServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectTeachServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        OperationResult<List<Teach>> result = null;

        try {
            TeachServiceImpl teachService = new TeachServiceImpl();
            result = teachService.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }

}
