package com.sys.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.sys.bean.Course;
import com.sys.service.impl.CourseServiceImpl;
import com.sys.util.OperationResult;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectCourseServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        OperationResult<List<Course>> result = null;
        try {
            CourseServiceImpl courseService = new CourseServiceImpl();
            result = courseService.selectAll();
//            for (Course course : (List<Course>)result.getData()) {
//                    System.out.println(course.getCsdate());
//                    System.out.println(course.getCedate());
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SerializeConfig config = new SerializeConfig();
        config.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));

        String json = JSON.toJSONString(result, config, SerializerFeature.DisableCircularReferenceDetect);
        response.getWriter().write(json);
        response.getWriter().flush();
    }


}
