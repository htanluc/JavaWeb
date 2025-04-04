package com.mytech.shopmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;

public class Dbconnector {
    static String jdbcURL = "jdbc:mysql://localhost:3306/t3shopjpa?useSSL=false";
    static String jdbcUsername = "root";
    static String jdbcPassword = "123456";
    @PersistenceUnit
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ShopManager");
    public static EntityManager getEntityManager () {
    	return entityManagerFactory.createEntityManager();
    }
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
