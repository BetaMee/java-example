package SqlDateBase;

import java.sql.*;

/**
 * Created by Betamee on 2017/6/1.
 */
public class JDBCUtil  {

    private final static String DB_DRIVER = "com.mysql.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/railway_sys";
    private final static String DB_NAME = "root";
    private final static String DB_PWd = "";


    static {
        try {
            Class.forName(DB_DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // 连接数据库
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_NAME, DB_PWd);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return conn;
    }

    public static Statement getStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
        PreparedStatement pstmt =null;
        try {
            pstmt = conn.prepareStatement(sql);
        }catch (SQLException  se) {
            se.printStackTrace();
        }
        return  pstmt;
    }

    public static ResultSet getRs(Statement stmt, String sql) {
        ResultSet rs = null;
        try {
//            sql = "SELECT * from `websites` WHERE `start_date`='2017-11-5' AND `start_city`='北京' AND `end_city`='上海'  AND `train_name`='T101'";
            rs = stmt.executeQuery(sql);
            System.out.println();
        }catch (SQLException se){
            se.printStackTrace();
        }

        return  rs;
    }
    // 关闭资源
    public static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
