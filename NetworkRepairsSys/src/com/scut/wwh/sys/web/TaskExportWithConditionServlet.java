package com.scut.wwh.sys.web;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import com.scut.wwh.sys.dao.TaskDao;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.DateUtil;
import com.scut.wwh.sys.util.DbUtil;
import com.scut.wwh.sys.util.ExcelUtil;
import com.scut.wwh.sys.util.ResponseUtil;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class TaskExportWithConditionServlet
 */
public class TaskExportWithConditionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DbUtil dbUtil = new DbUtil();
	TaskDao taskDao = new TaskDao();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   this.doPost(request, response);
	}  

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /**
		Object[] objects=request.getParameter("postdata");
	    System.out.println("post data---"+postDate);
		StringBuffer json=new StringBuffer();
		BufferedReader reader=request.getReader();
		String line=null;
		while((line=reader.readLine())!=null){
			json.append(line);
		}
		System.out.println("json data"+json.toString());
		**/
//		System.out.println("post data---"+java.net.URLDecoder.decode(postData,"UTF-8"));
		Connection con = null;
		request.setCharacterEncoding("UTF-8");
		
		String postData=request.getParameter("postdata");
		System.out.println("postData"+postData);
     	Task task=new Task();
     	String repairTimeEnd=null;
		if(postData!=null){
			postData=java.net.URLDecoder.decode(postData,"UTF-8");
			JSONObject jsonObject=JSONObject.fromObject(postData);
	   	    String repairer=jsonObject.get("repairer").toString();
	   	    String repairTime=jsonObject.get("repairTime").toString();
	   	    repairTimeEnd=jsonObject.get("repairTimeEnd").toString();
	   	    String userAddress=jsonObject.get("userAddress").toString();
	     	String state=jsonObject.get("state").toString();
	     	String type=jsonObject.get("faultType").toString();
	    
			task.setRepairer(repairer);
			task.setPublishTime(repairTime);
			task.setUserAddress(userAddress);
			task.setState(state);
			task.setType(type);
		}else{
			task.setRepairer(null);
			task.setPublishTime(null);
			task.setUserAddress(null);
			task.setState(null);
			task.setType(null);
		}
		//如果查询的截止时间为空，则默认为截止时间为当前时间
		if(repairTimeEnd==null||repairTimeEnd.equals("")){
			Date nowDate=new Date();
			try {
				repairTimeEnd=DateUtil.formaDate(nowDate, "yyyy-MM-dd");
				System.out.println("repairTimeEnd:"+repairTimeEnd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			 con = dbUtil.getCon();
			 Workbook wb =new HSSFWorkbook();
			 String headers[] = {"报修编号","报修用户姓名","用户报修时间","报修地址","故障类型","故障简单描述","维修人员","维修时间","完成时间","处理方法简单描述","状态"};
			 ResultSet rs = taskDao.exportTaskListWithCondition(con,task,repairTimeEnd);
			 ExcelUtil.fillExcelDate(rs, wb, headers);
			 String fileName=DateUtil.formaDate(new Date(), "yyyyMMddhhmmss");
			 ResponseUtil.exportTask(response, wb, fileName+".xls");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    System.out.println("-----Export End--");
	}
}
