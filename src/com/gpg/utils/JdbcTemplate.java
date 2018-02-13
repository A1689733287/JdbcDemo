package com.gpg.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcTemplate {
	public static <T> List<T> query(String sql, RowCallBackHandler<T> handler) {
		return query(sql, null, handler);
	}

	private static <T> List<T> query(String sql, PreparedStatementSetter setter, RowCallBackHandler<T> handler) {
		ResultSet rs = null;
		List<T> list = null;
		try {
			rs = query(sql, setter);
			if (handler != null) {
				list = new ArrayList<>();
				while (rs.next()) {
					list.add(handler.processRow(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs);
		}
		return list;
	}

	public static <T> T singleQuery(String sql, RowCallBackHandler<T> handler) {
		return singleQuery(sql, null, handler);
	}

	private static <T> T singleQuery(String sql, PreparedStatementSetter setter, RowCallBackHandler<T> handler) {
		ResultSet rs = null;
		try {
			rs = query(sql, setter);
			if (handler != null && rs.next()) {
				return handler.processRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs);
		}
		return null;
	}

	private static ResultSet query(String sql, PreparedStatementSetter setter) throws SQLException {
		PreparedStatement pstmt = ConnectManager.getConnection().prepareStatement(sql);
		if (null != setter) {
			setter.setValues(pstmt);
		}
		return pstmt.executeQuery();
	}
	
	public static int update(String sql, PreparedStatementSetter setter) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			pstmt= ConnectManager.getConnection().prepareStatement(sql);
			if (setter != null) {
				setter.setValues(pstmt);
			}
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(pstmt);
		}
		return 0;
	}

}
