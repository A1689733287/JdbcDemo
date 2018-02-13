package com.gpg.utils;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * JDBC事物管理类
 * @author G
 *
 */
public final class TransactionManager {
	
	public void beginTransction(Connection conn) {
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void commit(Connection conn) {
		try {
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void rollBack(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
