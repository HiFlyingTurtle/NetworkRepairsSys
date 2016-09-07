package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.model.User;
import com.scut.wwh.sys.util.StringUtil;

public class TaskHandInDao {
	//显示所有维修中任务信息，以及查询功能
	public ResultSet taskList(Connection con,PageBean pageBean, Task task,User user) throws Exception{
		//and repairer='"+task.getRepairer()+"'   order by publishTime desc
		//StringBuffer sb=new StringBuffer("select * from t_repair where state = '待维修' and repairer like '%"+user.getMyName()+"%'");
		
		StringBuffer sb=new StringBuffer("select * from t_repair where state = '待维修' and repairer='"+user.getName()+"'");
		//StringBuffer sb=new StringBuffer("select * from t_repair where state = '待维修'");
		
		//如果维修人员，报修时间，故障地点都为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" order by publishTime desc");
		}
		
		//如果维修人员不为空，其他为空，则查询满足该维修人员的记录，
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc");
		}
		//大屏位置不为空，则查询满足该 大屏位置的记录，其他为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//如果用户报修时间不为空，则查询满足该记录，其他为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果维修人员和故障地点都不为空的时候，报修时间为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//如果维修人员和报修时间都不为空，故障地点为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果大屏位置和报修时间都不为空，维修人员为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果维修人员，大屏地址，维修时间都不为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//统计维修中的任务记录数
	public int taskHandInDaoCount(Connection con, Task task,User user) throws Exception{
		//and repairer like '%"+user.getMyName()+"%'
		//and repairer='"+user.getName()+"' 
		StringBuffer sb=new StringBuffer("select count(*) as total from t_repair where state = '待维修' and repairer='"+user.getName()+"' ");
		//如果维修人员，报修时间，故障地点都为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
		   sb.append(" order by publishTime desc");
		}
				
		//如果维修人员不为空，其他为空，则查询满足该维修人员的记录，
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
		   sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc");
		}
		//大屏位置不为空，则查询满足该 大屏位置的记录，其他为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//如果用户报修时间不为空，则查询满足该记录，其他为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果维修人员和故障地点都不为空的时候，报修时间为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//如果维修人员和报修时间都不为空，故障地点为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果大屏位置和报修时间都不为空，维修人员为空
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//如果维修人员，大屏地址，维修时间都不为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}
		else{
			return 0;
		}
	}
	
	//上交报修单事件，相当于修改完成时间，报修状态和处理方法
	public synchronized int  taskHandIn(Connection con,Task task) throws Exception{
		String sql="update t_repair set finishTime=?,state=?,dealWay=? where taskId=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, task.getFinishTime());
		ps.setString(2, task.getState());
		ps.setString(3, task.getDealWay());
		ps.setInt(4, task.getTaskId());
		return ps.executeUpdate();
	}
}
