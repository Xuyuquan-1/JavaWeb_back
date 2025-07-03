package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.sys.bean.Course;
import com.sys.service.impl.CourseServiceImpl;
import com.sys.util.JsonDateUtil;
import com.sys.util.OperationResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

public class SelectCourseServlet extends HttpServlet {

//    private static final SerializeConfig config = new SerializeConfig();
//    static {
//        System.setProperty("fastjson.serializer.dateFormat", "yyyy-MM-dd");
//    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        // 提取所有参数
        OperationResult<List<Course>> result = null;
        CourseServiceImpl courseService = new CourseServiceImpl();

        Enumeration<String> parameterNames = request.getParameterNames();
        String paramName = "";
        String paramValue = "";
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            paramValue = request.getParameter(paramName);

            System.out.println(paramName + " = " + paramValue);
        }

        try {
            result = courseService.selectAll(paramValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        config.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
        //存在bug，date格式不对
        String json = JsonDateUtil.toJson(result);
        response.getWriter().write(json);
        response.getWriter().flush();
    }


}
