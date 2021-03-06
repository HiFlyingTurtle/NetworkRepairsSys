package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.StringUtil;

public class TaskDao {
	
	//显示所有任务信息，以及查询功能
	public ResultSet taskList(Connection con,PageBean pageBean, Task task,String repairTimeEnd) throws Exception{
		//String userAddress repairer state   publishTime   order by publishTime desc 
		//System.out.println("sql begining-----"+task.getRepairer());
		StringBuffer sb=new StringBuffer("select * from t_repair where state in('待维修','已维修')");
		sb=queryWithCondition(sb, task, repairTimeEnd);
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		System.out.println("----AllTaskDao  SQL Statement----"+sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//统计任务记录数
	public int allTaskCount(Connection con, Task task,String repairTimeEnd) throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_repair where state in('待维修','已维修')");
		sb=queryWithCondition(sb, task, repairTimeEnd);
		System.out.println("----AllTaskCount Sql Statement----"+sb.toString());
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			//total是所有的列，count(*) as total 
			return rs.getInt("total");
		}
		else{
			return 0;
		}        
		     
	}
	
	//删除报修记录事件
	public int taskDel(Connection con, String delIds) throws Exception {
		String sql="delete from t_repair where taskId in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	
	//添加报修任务
	public int taskAdd(Connection con,Task task) throws Exception{
		String sql="insert into t_repair(taskId,userName,publishTime,userAddress,type,troubleDesc,repairer,state) value(null,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, task.getUserName());
		ps.setString(2, task.getPublishTime());
		ps.setString(3, task.getUserAddress());
		//故障类型
		ps.setString(4, task.getType());
		ps.setString(5, task.getTroubelDesc());
		ps.setString(6, task.getRepairer());
		ps.setString(7, task.getState());
		return ps.executeUpdate();
	}
	
	
	//导出报修记录清单的List
	public ResultSet exportTaskList(Connection con,PageBean pageBean)throws Exception{

		StringBuffer sb=new StringBuffer("select * from t_repair where state in('待维修','维修中','已维修')");
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		
		if(pageBean!=null){
			sb.append(" limit ?,?");			
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		if(pageBean!=null){
			pstmt.setInt(1, pageBean.getStart());
			pstmt.setInt(2, pageBean.getRows());
		}
		return pstmt.executeQuery();
	}

	public ResultSet exportTaskListWithCondition(Connection con, Task task,String repairTimeEnd) throws SQLException {
		StringBuffer sb=new StringBuffer("select * from t_repair where state in('待维修','已维修')");
		sb=queryWithCondition(sb,task,repairTimeEnd);
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		return rs;
	}
	
	public static StringBuffer queryWithCondition(StringBuffer sb,Task task,String repairTimeEnd){
		
		//如果故障点，维修人员，状态,报修时间,故障类型都为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&& StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
		    sb.append(" order by publishTime desc");
		}
		
		//如果故障地点不为空，其他为空，则查询满足该古战地点的记录
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' order by publishTime desc ");
		}
		
		//如果维护者不为空，则查询满足该维修人员的记录，其他为空 repairer='"+user.getName()+"'
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//如果状态不为空，则查询满足该状态记录，其他为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//如果报修时间不为空，则查询满足其他该状态的记录，其他为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
		//	sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
			sb.append(" and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime)  order by publishTime desc ");
		}
	    //如果故障类型不为空，则查询满足其他该状态的记录，其他为空	
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and type='"+task.getType()+"' order by publishTime desc ");
		}
	
		//如果故障地点和维护人员都不为空的时候，状态和报修时间以及故障类型为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//如果故障地点和状态都不为空，维护人员和报修时间以及故障类型为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//如果故障地点和报修时间不为空，维护人员，状态和故障类型为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
		}
		
		//如果故障地点和故障类型不为空，报修时间以及状态，维修人员为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and type='"+task.getType()+"' order by publishTime desc ");
		}
		
		
		//如果维修人员和状态不为空，故障地点和报修时间为空,故障类型为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		
		//维修人员和报修时间不为空，故障地点和状态以及故障类型为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and repairer='"+task.getRepairer()+"' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
		//维修人员和故障类型不为空，故障地点和状态以及保修时间为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and repairer='"+task.getRepairer()+"' and type='"+task.getType()+"' order by publishTime desc ");
		}
		
		//状态和报修时间不为空，故障地点和维修人员、故障类型为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
		//状态和故障类型不为空，故障地点，维修人员以及报修时间为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and state like '%"+task.getState()+"%' and type='"+task.getType()+"' order by publishTime desc ");
        }
		//报修时间和故障类型不为空，故障地点，维修人员，状态为空
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and type='"+task.getType()+"' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
		
		//故障地点 ，维修人员，状态不为空，报修时间,故障类型为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
        }
		//故障地点，维修人员，报修时间不为空，状态,故障类型为空;
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
		//故障地点，维修人员，故障类型不为空，状态,报修时间为空;
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and type='"+task.getType()+"' order by publishTime desc ");
        }
		
		//故障地点 ，状态，报修时间不为空，维修人员，故障类型为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		//故障地点 ，状态，故障类型不为空，维修人员，报修时间为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and type='"+task.getType()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
        }
		
		
		
		//维修人员，状态，报修时间不为空，故障地点，故障类型为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
		//维修人员，状态，故障类型不为空，故障地点，报修时间为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and state like '%"+task.getState()+"%' and repairer='"+task.getRepairer()+"' and type='"+task.getType()+"' order by publishTime desc ");
        }
		
		//状态,报修时间,故障类型不为空，故障点，维修人员
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and type='"+task.getType()+"' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
        }
		
	
		//如果故障点，维修人员，状态,报修时间都不为空,故障类型为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())&&StringUtil.isEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
		}
		
		//维修人员，状态,报修时间,故障类型不为空，故障地点为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and type='"+task.getType()+"' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) order by publishTime desc ");
		}
		
		//如果故障点，维修人员，状态,报修时间,故障类型都不为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())&&StringUtil.isNotEmpty(task.getType())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and cast(publishTime as datetime) between cast('"+task.getPublishTime()+"' as datetime) and cast('"+repairTimeEnd+"' as datetime) and type='"+task.getType()+"' order by publishTime desc ");
		}
		return sb;
	}
}
