package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scut.wwh.sys.dao.PersonInfoModifyDao;
import com.scut.wwh.sys.model.User;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.ResponseUtil;
import com.scut.wwh.sys.util.StringUtil;

import net.sf.json.JSONObject;



public class PersonPWDSaveServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil=new DbUtil();
	PersonInfoModifyDao psersonInfoModify=new PersonInfoModifyDao();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�첽������������
		request.setCharacterEncoding("UTF-8");
		//��ǰ̨��ȡ�����޸� ��������
		String password=request.getParameter("newPassword");
		System.out.println("newPassword:"+password);
		String id =request.getParameter("id");
		User user=null;
		try {
			user=new User(null,password,null,null,null,null,null,null,null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//���ǰ̨������ID��Ϊ�գ���IDתΪint��������
		if(StringUtil.isNotEmpty(id)){
			user.setUserId(Integer.parseInt(id));
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			saveNums=psersonInfoModify.personPWDModify(con, user);
			//����޸ĵ��������㣬��Ȼֻ���ܵ�����
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
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
