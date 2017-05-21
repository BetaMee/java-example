package Railway;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Created by Betamee on 2017/5/21.
 */
public class Customer extends javax.servlet.http.HttpServlet {
    public void init() throws ServletException{

    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        // 设置响应的数据
        response.setContentType("text/html");
        // 处理逻辑
        PrintWriter out = response.getWriter();
        out.println("<h1>jjj</h1>");
    }
    public void destroy() {

    }
}
