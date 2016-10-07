package com.scut.wwh.sys.web;
import com.scut.wwh.sys.dao.PersonInfoModifyDao;
import com.scut.wwh.sys.model.PageBean;
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

public class PersonInfoModifyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	PersonInfoModifyDao personInfoModify=new PersonInfoModifyDao();
	DbUtil dbUtil=new DbUtil();
	//用一个静态常量去接收登录的用户名
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//从session中获取myName
		HttpSession session=request.getSession();
		String myName=(String) session.getAttribute("myName");

		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		User user=new User();
		user.setUserName(myName);
//		System.out.println("myName---"+myName);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Connection con=null;
		try{
			con=dbUtil.getCon();//连接数据库
			JSONObject result=new JSONObject();
			JSONArray jsonArray=JsonUtil.formatRsToJsonArray(personInfoModify.personInfoList(con, pageBean, user));
			int total=personInfoModify.personinfoCount(con, user);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
			System.out.println("personInfoModify--"+jsonArray.toString());
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
