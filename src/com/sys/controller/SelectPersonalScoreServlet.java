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
import java.util.Enumeration;
import java.util.List;

public class SelectPersonalScoreServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 提取所有参数
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        OperationResult<List<Learn>> result = null;
        LearnServiceImpl learnService = new LearnServiceImpl();

        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName = "";
        String paramValue = "";
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            paramValue = request.getParameter(paramName);

            System.out.println(paramName + " = " + paramValue);
        }

        try {
            result = learnService.selectAllByPersonal(paramValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        for (Teacher teacher : (List<Teacher>)result.getData()) {
//            System.out.println(teacher.getCno());
//
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }

}
