package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Teacher;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.OperationResult;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

public class SelectTeacherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 提取所有参数
        OperationResult<List<Teacher>> result = null;
        TeacherServiceImpl teacherService = new TeacherServiceImpl();

        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName = "";
        String paramValue = "";
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            paramValue = request.getParameter(paramName);

            System.out.println(paramName + " = " + paramValue);
        }

        try {
            result = teacherService.selectAll(paramValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        for (Teacher teacher : (List<Teacher>)result.getData()) {
//            System.out.println(teacher.getCno());
//        }
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
