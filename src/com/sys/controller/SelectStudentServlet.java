package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.sys.bean.Student;
import com.sys.bean.Teacher;
import com.sys.service.impl.StudentServiceImpl;
import com.sys.service.impl.TeacherServiceImpl;
import com.sys.service.interfaces.StudentService;
import com.sys.util.OperationResult;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class SelectStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        OperationResult<List<Student>> result = null;
//        try {
//            StudentServiceImpl studentService = new StudentServiceImpl();
//            result = studentService.selectAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        response.getWriter().write(JSON.toJSONString(result));
//        response.getWriter().flush();
//    }
//
//

        // 提取所有参数
        OperationResult<List<Student>> result = null;
        StudentServiceImpl studentService = new StudentServiceImpl();

        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName = "";
        String paramValue = "";
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            paramValue = request.getParameter(paramName);

            System.out.println(paramName + " = " + paramValue);
        }

        try {
            result = studentService.selectAll(paramValue);
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
