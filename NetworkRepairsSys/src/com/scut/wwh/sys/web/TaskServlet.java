package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.TaskDao;
import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.DateUtil;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.JsonUtil;
import com.scut.wwh.sys.util.ResponseUtil;

public class TaskServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	TaskDao allTaskDao=new TaskDao();

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
		
		//��ǰ̨��ȡ��ѯ������ά����Ա������ʱ�䣬���ϵص㣬״̬������ �����ĸ���ѯ�ؼ���
		String repairer=request.getParameter("repairer");
	    //System.out.println("repairer"+repairer);
		String repairtime=request.getParameter("repairTime");
		String repairTimeEnd=request.getParameter("repairTimeEnd");
		//��ʱ��ת��Ϊ:yyyy-MM-dd
		if(repairTimeEnd==null||repairTimeEnd.equals("")){
			Date nowDate=new Date();
			try {
				repairTimeEnd=DateUtil.formaDate(nowDate, "yyyy-MM-dd");
				System.out.println("repairTimeEnd:"+repairTimeEnd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String userAddress=request.getParameter("userAddress");
		String state=request.getParameter("state");
		String type=request.getParameter("faultType");//faultType ��������
//		System.out.println("��ʼʱ��---"+repairtime+"--->����ʱ��"+repairTimeEnd);
//		System.out.println("��������---"+type);
		//��ǰ̨������ά����Ա��״̬����������Task
		Task task=new Task();
		task.setRepairer(repairer);
		task.setPublishTime(repairtime);
		task.setUserAddress(userAddress);
		task.setState(state);
		task.setType(type);
        System.out.println("ǰ̨����Ĳ�ѯ����----"+task);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();//�������ݿ�
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(allTaskDao.taskList(con,pageBean,task,repairTimeEnd));
			int total=allTaskDao.allTaskCount(con,task,repairTimeEnd);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			System.out.println("task Browser---"+jsonArray.toString());
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
