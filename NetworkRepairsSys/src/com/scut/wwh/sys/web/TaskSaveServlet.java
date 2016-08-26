package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.scut.wwh.sys.dao.TaskDao;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.ResponseUtil;

public class TaskSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUitl=new DbUtil();
	TaskDao taskDao=new TaskDao();
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
		//��ǰ̨��ȡ��Ϣ �������� ����ʱ�� ������ַ �������� �������� ά����Ա
		String userName=request.getParameter("userName");
		String publishTime=request.getParameter("publishTime");
		String userAddress=request.getParameter("userAddress");
		String type=request.getParameter("type");
		String troubleDesc=request.getParameter("troubleDesc");
		String repairer=request.getParameter("repairer");
		//String state=request.getParameter("state");
		//�������Ĺ���Ա���������ʼ״̬Ϊ����ά�ޡ�,��ͨ��ǰ̨ҳ�洫������Servlet�Զ�����Ϊ��ά�ޡ�
		String state = "��ά��";
	
		Task task=new Task(userName,publishTime,userAddress,type,troubleDesc,repairer,null,null,null,state);
		
	//	System.out.println("repairer----"+repairer);
		
		Connection con=null;
		try {
			con=dbUitl.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			saveNums=taskDao.taskAdd(con, task);
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
