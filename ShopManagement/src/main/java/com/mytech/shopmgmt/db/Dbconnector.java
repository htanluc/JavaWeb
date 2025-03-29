package com.mytech.shopmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dbconnector {
    static String jdbcURL = "jdbc:mysql://localhost:3306/t3shop?useSSL=false";
    static String jdbcUsername = "root";
    static String jdbcPassword = "123456";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            // Không tìm thấy driver MySQL
            e.printStackTrace();
        } catch (SQLException e) {
            // Lỗi khi kết nối cơ sở dữ liệu
            e.printStackTrace();
        }
        return connection;
    }
}
