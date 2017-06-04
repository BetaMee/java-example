package Railway;


import SqlDateBase.JDBCUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Betamee on 2017/5/28.
 */
@WebServlet(name = "Ticket")
public class Ticket extends HttpServlet {
    public Ticket() {
        super();
    }
    // 通过post新增数据
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // 通过参数来获取数据库的信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求信息
        String starttime =new String(request.getParameter("starttime"));
        String startdate =new String(request.getParameter("startdate"));
        String startcity =new String(request.getParameter("startcity"));
        String endcity =new String(request.getParameter("endcity"));
        String tranname =new String(request.getParameter("tranname"));
        // 设置响应内容类型
        response.setContentType("text/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 数据库连接
        Connection conn =  JDBCUtil.getConnection();
        Statement stmt = JDBCUtil.getStatement(conn);
        //构建查询语句
        String sql = null;
        if (starttime=="" && startdate=="" &&  startcity=="" && endcity=="" &&  tranname=="") {  // 全选
            sql = "SELECT * FROM `websites` WHERE 1";
        }

        if () {
            sql = "SELECT * from `websites` WHERE starttime="+starttime+""
        }

        try {
            ResultSet rs= JDBCUtil.getRs(stmt, sql); // 获取结果
            JSONArray jsonArr = new JSONArray();
            //处理结果
            while (rs.next()){
                JSONObject jsonObj = new JSONObject(); // json对象
                int id  = rs.getInt("id");
                String train_name = rs.getString("train_name");
                String start_date = rs.getString("start_date");
                String start_time = rs.getString("start_time");
                String start_city = rs.getString("start_city");
                String end_city = rs.getString("end_city");
                Integer tickets = rs.getInt("tickets");
                try {
                    jsonObj.put("id",id)
                            .put("train_name",train_name)
                            .put("start_date",start_date)
                            .put("start_time",start_time)
                            .put("start_city",start_city)
                            .put("end_city",end_city)
                            .put("tickets",tickets);
                    // 放入数组中
                    jsonArr.put(jsonObj);
                }catch (JSONException je){
                    je.printStackTrace();
                }
            }
            // 发送json数组
            out.println(jsonArr);

        }catch (SQLException se) {
            se.printStackTrace();
        }
        System.out.printf(starttime);
    }
}
