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
import java.util.List;

public class SelectTeacherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        OperationResult<List<Teacher>> result = null;
        try {
            result = new TeacherServiceImpl().selectAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        for (Teacher teacher : (List<Teacher>)result.getData()) {
//            System.out.println(teacher.getCno());
//        }
        response.getWriter().write(JSON.toJSONString(result));
        response.getWriter().flush();
    }
}
