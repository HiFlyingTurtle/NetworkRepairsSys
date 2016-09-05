package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.RepairerDao;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.JsonUtil;
import com.scut.wwh.sys.util.ResponseUtil;

	/**
	 * Servlet implementation class LoadRepairerServlet
	 */
public class LoadRepairerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	RepairerDao repairerDao=new RepairerDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;
		try {
			con=dbUtil.getCon();//连接数据库
			
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(repairerDao.repairerList(con));
			ResponseUtil.write(response, jsonArray);
			System.out.println("loading repairer--"+jsonArray.toString());
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
