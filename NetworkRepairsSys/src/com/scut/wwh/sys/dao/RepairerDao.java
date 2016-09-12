package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepairerDao {
	public ResultSet repairerList(Connection con) throws SQLException{
		StringBuffer sb=new StringBuffer("select id, name from t_user where userName !='admin'");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

}
