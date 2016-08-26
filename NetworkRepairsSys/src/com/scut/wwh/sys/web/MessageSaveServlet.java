package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.MessageDao;
import com.scut.wwh.sys.model.Message;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.ResponseUtil;
import com.scut.wwh.sys.util.StringUtil;

public class MessageSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUitl=new DbUtil();
	MessageDao messageDao=new MessageDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		//异步提交处理中文乱码
		request.setCharacterEncoding("UTF-8");
		//从前台获取信息
		String title="通知";
		String who=request.getParameter("who");
		String content=request.getParameter("content");
		String time=request.getParameter("time");
		String infoment=request.getParameter("infoment");
		String messId=request.getParameter("messId");
		//将前台的数据传到实体类里面去
		Message message=new Message(title,who,content,time,infoment);
		
		//如果前台传来的ID不为空，将ID转为int数据类型
		if(StringUtil.isNotEmpty(messId)){
			message.setMessId(Integer.parseInt(messId));
		}
		Connection con=null;
		try {
			con=dbUitl.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			//如果传来的id不为空，则说明是修改事件
			if(StringUtil.isNotEmpty(messId)){
				saveNums=messageDao.messageUpdate(con, message);
			}
			//那么就是，添加事件
			else{
				saveNums=messageDao.messageAdd(con, message);
			}
			//如果从添加或者删除返回的数大于零，则说明添加或者修改成功
			if(saveNums>0){
				result.put("success", "true");
			}
			else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败!");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUitl.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
