package com.scut.wwh.sys.web;
import com.scut.wwh.sys.dao.TaskHandInDao;
import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.model.User;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.JsonUtil;
import com.scut.wwh.sys.util.ResponseUtil;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TaskHandInServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	TaskHandInDao taskHandInDao=new TaskHandInDao();
	DbUtil dbUtil=new DbUtil();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//从前台获取查询条件值,维修者,维修时间，用户地址
		String repairer=request.getParameter("repairer");
		String repairtime=request.getParameter("repairTime");
		String userAddress=request.getParameter("userAddress");
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		
		//从session中获取登录的userName
		HttpSession session=request.getSession();
		String currentUser=(String) session.getAttribute("myName");
		String name=(String) session.getAttribute("name");
		
//		System.out.println("task handle servlet---"+name);
		//将session中获取的userName传到user里面
		User user=new User();
		user.setMyName(currentUser);
		user.setName(name);
		
		Task task=new Task();
		//将前台传来的查询条件值,维修人员，用户报修时间，故障地点传到task里面
		task.setRepairer(repairer);
		task.setPublishTime(repairtime);
		task.setUserAddress(userAddress);
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();//连接数据库
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(taskHandInDao.taskList(con, pageBean, task,user));
			int total=taskHandInDao.taskHandInDaoCount(con, task, user);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			System.out.println("task HandIn----"+jsonArray.toString());
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
