package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.MessageDao;
import com.scut.wwh.sys.model.Message;
import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.JsonUtil;
import com.scut.wwh.sys.util.ResponseUtil;

public class MessageServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	MessageDao messageDao=new MessageDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page=request .getParameter("page");
		String rows=request.getParameter("rows");
		//从前台获取查询条件,通知的发布时间，通知对象等条件
		String time=request.getParameter("time");
		String who=request.getParameter("who");
		Message message=new Message();
		//将前台传来的用户姓名，维修者，状态等条件传到Message
		message.setTime(time);
		message.setWho(who);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();//连接数据库
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(messageDao.messageList(con,pageBean,message));
			int total=messageDao.messageCount(con,message);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
