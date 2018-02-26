package com.gpg.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectManager {
	
	//使用局部线程 高并发
	private final static ThreadLocal<Connection> LOCAL = new ThreadLocal<>();
	private static DataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource("oracle");
	}
	
	/**
	 * 得到连接对象
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{
		Connection conn = LOCAL.get();
		try {
			if (null == conn || conn.isClosed()) {
				conn = dataSource.getConnection();
			}
			LOCAL.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放连接
	 */
	public static void release() {
		Connection conn = LOCAL.get();
		if (null != conn) {
			DBUtil.release(conn);
			LOCAL.remove();
		}
	}
}
