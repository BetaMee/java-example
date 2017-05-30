package Railway;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Betamee on 2017/5/28.
 */
@WebServlet(name = "Ticket")
public class Ticket extends HttpServlet {
    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/railway_sys";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "";

    private Connection conn = null;
    private Statement stmt = null;


    public Ticket() {
        super();
    }

    private void connectToDB() {
        try {
            // 注册JDBC驱动器
            Class.forName("com.mysql.jdbc.Driver");
            //打开一个链接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // 执行 SQL 查询
            stmt = conn.createStatement();

        }catch (SQLException se) {
            se.printStackTrace();
        }catch (Exception e){
            //处理 Class.forName 错误
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 连接数据库
        connectToDB();
        // 获取参数

        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "Servlet Mysql 测试 - 菜鸟教程";
        String docType = "<!DOCTYPE html>\n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n");
        //执行查询
        String sql;
        sql = "SELECT * FROM `websites` WHERE 1";
        try {
            ResultSet rs = this.stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String train_name = rs.getString("train_name");
                String start_date = rs.getString("start_date");
                String start_time = rs.getString("start_time");
                String start_city = rs.getString("start_city");
                String end_city = rs.getString("end_city");
                Integer tickets = rs.getInt("tickets");


                // 输出数据
                out.println("ID: " + id);
                out.println(", train_name: " + train_name);
                out.println(", start_date: " + start_date);
                out.println(", start_time: " + start_time);
                out.println(", start_city: " + start_city);
                out.println(", end_city: " + end_city);
                out.println(", tickets: " + tickets);
                out.println("<br />");
            }
            out.println("</body></html>");
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
