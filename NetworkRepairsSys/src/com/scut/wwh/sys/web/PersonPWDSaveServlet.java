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
		//异步处理中文乱码
		request.setCharacterEncoding("UTF-8");
		//从前台获取个人修改 的新密码
		String password=request.getParameter("newPassword");
		System.out.println("newPassword:"+password);
		String id =request.getParameter("id");
		User user=null;
		try {
			user=new User(null,password,null,null,null,null,null,null,null,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果前台传来的ID不为空，将ID转为int数据类型
		if(StringUtil.isNotEmpty(id)){
			user.setUserId(Integer.parseInt(id));
		}
		Connection con=null;
		try {
			con=dbUtil.getCon();
			JSONObject result=new JSONObject();
			int saveNums=0;
			saveNums=psersonInfoModify.personPWDModify(con, user);
			//如果修改的数大于零，当然只可能等于零
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
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
