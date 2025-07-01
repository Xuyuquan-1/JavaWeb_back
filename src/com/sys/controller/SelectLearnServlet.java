package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Learn;
import com.sys.service.impl.LearnServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectLearnServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        OperationResult<List<Learn>> result = null;
        try {
            result = new LearnServiceImpl().selectAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }

}
