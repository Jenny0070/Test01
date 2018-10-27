package com.bluemsun.dbutils;

import java.sql.*;
import java.util.ResourceBundle;



public class DBUtils {
	private static String URL="jdbc:mysql://localhost:3306/10_10bluenet";
	private static String USERNAME="root";
	private static String PASSWORD="root";
	private static String DRIVER="com.mysql.jdbc.Driver";
	
	public DBUtils() {
	}
	//使用静态块加载驱动程序
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//定义一个获取数据库连接的方法
	
	public static Connection getConnection () {
		Connection conn=null;
		try {
			conn= DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		return conn;
		
	}
	
	//关闭数据库连接
	
	public static void closeConnection(ResultSet rs, Statement st,Connection conn){
		try {
			if (rs!=null) {
				rs.close();
			}
			if (st!=null){
				st.close();
			}
			if (conn!=null){
				conn.close();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
