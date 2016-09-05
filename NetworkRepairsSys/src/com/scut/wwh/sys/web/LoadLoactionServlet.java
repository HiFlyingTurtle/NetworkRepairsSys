package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.LocationDao;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.JsonUtil;
import com.scut.wwh.sys.util.ResponseUtil;

/**
 * Servlet implementation class LoadLoactionServlet
 */
public class LoadLoactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	LocationDao locationDao=new LocationDao();
 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	System.out.println("load Location Servlet begining----");
		Connection con=null;
		try {
			con=dbUtil.getCon();//连接数据库
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(locationDao.locationList(con));
			ResponseUtil.write(response, jsonArray);
			System.out.println("loading Location--"+jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
