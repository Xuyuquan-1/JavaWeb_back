package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Teacher;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;


public class SerialTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 提取所有参数
        OperationResult<List<Teacher>> result = null;
        TeacherServiceImpl teacherService = new TeacherServiceImpl();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            try {
                result = teacherService.selectAll(paramValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println(paramName + " = " + paramValue);
        }
//        if (result!=null && result.getData() != null) {
//            for (Teacher teacher : (List<Teacher>) result.getData()) {
//                System.out.println(teacher.toString());
//            }
//        }else {
//            System.out.println("result is null");
//        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        response.getWriter().write("Parameters received.");

//        PrintWriter out = response.getWriter();
//        out.println("<h1>Hello from Servlet!</h1>");
//        String jsonResponse = "{\"message\":\"Hello from server\",\"data\":[1,2,3]}";
//        PrintWriter out = response.getWriter();
//        out.print(jsonResponse);
//        out.flush();

        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();

    }
}

    //需要处理option请求，不然只能跨域发送不能跨域接收

