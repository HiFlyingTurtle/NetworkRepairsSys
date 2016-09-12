package com.scut.wwh.sys.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.scut.wwh.sys.dao.UserDao;
import com.scut.wwh.sys.model.User;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.StringUtil;

public class LoginServlet extends HttpServlet{
															
	private static final long serialVersionUID = 1L;
	UserDao userDao=new UserDao();
	DbUtil dbUtil=new DbUtil();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//定义"职员","管理员字符串"作为权限判断，判断前台传来的权限是否和他相同
		 String currentLevel="管理员";
		
		/*从前台获取用户名，密码，权限*/
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String level=request.getParameter("level");
		
		//request.setAttribute("userName", userName);
		//request.setAttribute("password", password);
		request.setCharacterEncoding("UTF-8");
		
		/*判断用户名和密码是否为空*/
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			request.setAttribute("error", "用户名或密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	
		//将用户名，密码，权限参数传过去		
		User user=new User(userName,password,level);
//		System.out.println(userName+password+level);
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User currentUser=userDao.login(con,user);
//			System.out.println("currentUser"+currentUser);
//			System.out.println("user test"+user);
			//userDao.login(con,user)返回的User对象为null，则说明在数据库里面没有这条记录
			if(currentUser == null){
				request.setAttribute("error", "用户名或密码错误，或者权限不匹配");
				//服务器跳转，登录失败				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else{
				
				//服务器重定向，登录成功		
				HttpSession session = request.getSession();
				session.setAttribute("currentUser",currentUser);
				//将登录的用户名缓存到myName
				session.setAttribute("myName", currentUser.getUserName());
				session.setAttribute("name", currentUser.getName());
				System.out.println("用户登录用户名name---:"+currentUser.getName());
				//判断权限，如果是管理员，则跳到index.jsp
				if(currentUser.getLevel().equals(currentLevel)){
					response.sendRedirect("index.jsp");
				}
				//如果是职员，则跳到index1.jsp
				else{
					response.sendRedirect("index1.jsp");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
