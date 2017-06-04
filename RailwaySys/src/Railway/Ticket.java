package Railway;


import SqlDateBase.JDBCUtil;
import org.json.JSONException;
import org.json.JSONStringer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;




/**
 * Created by Betamee on 2017/5/28.
 */
@WebServlet(name = "Ticket")
public class Ticket extends HttpServlet {
    public Ticket() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("text/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONStringer stringer = new JSONStringer(); // json字符串处理
        try {
            stringer.object().
                    key("tID").value("44").
                    key("name").value("sds").
                    key("tourList").value("sdf").
                    key("tourIDList").value("ss").
                    endObject();
        }catch (JSONException je){
            je.printStackTrace();
        }

        out.println(stringer);

//        out.println();
//        //执行查询
//        String sql;
//        sql = "SELECT * FROM `websites` WHERE 1";
//        try {
//            ResultSet rs = this.stmt.executeQuery(sql);
//            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                int id  = rs.getInt("id");
//                String train_name = rs.getString("train_name");
//                String start_date = rs.getString("start_date");
//                String start_time = rs.getString("start_time");
//                String start_city = rs.getString("start_city");
//                String end_city = rs.getString("end_city");
//                Integer tickets = rs.getInt("tickets");
//
//
//                // 输出数据
//                out.println("ID: " + id);
//                out.println(", train_name: " + train_name);
//                out.println(", start_date: " + start_date);
//                out.println(", start_time: " + start_time);
//                out.println(", start_city: " + start_city);
//                out.println(", end_city: " + end_city);
//                out.println(", tickets: " + tickets);
//                out.println("<br />");
//            }
//            out.println("</body></html>");
//        }catch (SQLException se) {
//            se.printStackTrace();
//        }
    }
}
