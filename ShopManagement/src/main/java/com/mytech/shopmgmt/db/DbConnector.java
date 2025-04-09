package com.mytech.shopmgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.persistence.EntityManager;

public class DbConnector {
	static String jdbcURL = "jdbc:mysql://localhost:3306/t3shop?useSSL=false";
	static String jdbcUsername = "appuser";
	static String jdbcPassword = "123456";

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	public static EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return null;
	}
}
