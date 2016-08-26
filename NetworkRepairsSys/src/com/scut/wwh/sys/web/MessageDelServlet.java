
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
		//从前台获取选择删除的记录
		String delIds=request.getParameter("delIds");
		//System.out.println(delIds);
		Connection con=null;
		try{
			con=dbUtil.getCon();//连接数据库
			JSONObject result=new JSONObject();
			int delNums=messageDao.messageDel(con,delIds);//获取删除的数量
			//删除数量大于0，才说明删除成功
			if(delNums>0){
				result.put("success","true");
				result.put("delNums", delNums);
			}
			else{
				result.put("erroeMsg", "数据删除失败！");
			}
			ResponseUtil.write(response, result);
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();//关闭数据库
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
