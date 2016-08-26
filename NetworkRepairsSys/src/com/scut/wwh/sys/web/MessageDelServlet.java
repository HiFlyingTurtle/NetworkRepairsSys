
package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.MessageDao;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.ResponseUtil;
public class MessageDelServlet extends HttpServlet{
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
		//��ǰ̨��ȡѡ��ɾ���ļ�¼
		String delIds=request.getParameter("delIds");
		//System.out.println(delIds);
		Connection con=null;
		try{
			con=dbUtil.getCon();//�������ݿ�
			JSONObject result=new JSONObject();
			int delNums=messageDao.messageDel(con,delIds);//��ȡɾ��������
			//ɾ����������0����˵��ɾ���ɹ�
			if(delNums>0){
				result.put("success","true");
				result.put("delNums", delNums);
			}
			else{
				result.put("erroeMsg", "����ɾ��ʧ�ܣ�");
			}
			ResponseUtil.write(response, result);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();//�ر����ݿ�
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
