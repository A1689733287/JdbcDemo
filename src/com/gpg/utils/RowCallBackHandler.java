package com.gpg.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowCallBackHandler<T> {
	T processRow(ResultSet rs) throws SQLException;
}
