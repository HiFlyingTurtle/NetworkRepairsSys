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
		//�첽�ύ������������
		request.setCharacterEncoding("UTF-8");
		//��ǰ̨��ȡ��Ϣ
		String title="֪ͨ";
		String who=request.getParameter("who");
		String content=request.getParameter("content");
		String time=request.getParameter("time");
		String infoment=request.getParameter("infoment");
		String messId=request.getParameter("messId");
		//��ǰ̨�����ݴ���ʵ��������ȥ
		Message message=new Message(title,who,content,time,infoment);
		
		//���ǰ̨������ID��Ϊ�գ���IDתΪint��������
		if(StringUtil.isNotEmpty(messId)){
			message.setMessId(Integer.parseInt(messId));
		}
		Connection con=null;
		try {
			con=dbUitl.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			//���������id��Ϊ�գ���˵�����޸��¼�
			if(StringUtil.isNotEmpty(messId)){
				saveNums=messageDao.messageUpdate(con, message);
			}
			//��ô���ǣ�����¼�
			else{
				saveNums=messageDao.messageAdd(con, message);
			}
			//�������ӻ���ɾ�����ص��������㣬��˵����ӻ����޸ĳɹ�
			if(saveNums>0){
				result.put("success", "true");
			}
			else{
				result.put("success", "true");
				result.put("errorMsg", "����ʧ��!");
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
