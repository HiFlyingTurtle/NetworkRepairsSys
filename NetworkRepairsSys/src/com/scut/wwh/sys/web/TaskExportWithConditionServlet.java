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
		//�����ѯ�Ľ�ֹʱ��Ϊ�գ���Ĭ��Ϊ��ֹʱ��Ϊ��ǰʱ��
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
			 String headers[] = {"���ޱ��","�����û�����","�û�����ʱ��","���޵�ַ","��������","���ϼ�����","ά����Ա","ά��ʱ��","���ʱ��","������������","״̬"};
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
