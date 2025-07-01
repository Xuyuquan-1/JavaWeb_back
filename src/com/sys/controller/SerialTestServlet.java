package com.sys.controller;

import javax.servlet.http.*;
import java.io.*;


public class SerialTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

//        PrintWriter out = response.getWriter();
//        out.println("<h1>Hello from Servlet!</h1>");
        String jsonResponse = "{\"message\":\"Hello from server\",\"data\":[1,2,3]}";
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();

    }
    //需要处理option请求，不然只能跨域发送不能跨域接收
}
