package com.gpg.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementSetter {
	//用来处理通配符？
	void setValues(PreparedStatement pstmt) throws SQLException;
}
