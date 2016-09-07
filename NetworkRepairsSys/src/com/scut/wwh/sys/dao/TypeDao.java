package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeDao {
	public ResultSet typeList(Connection con) throws SQLException{
		StringBuffer sb=new StringBuffer("select id,type from t_type");
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

}
