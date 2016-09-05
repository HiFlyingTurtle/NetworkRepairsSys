package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.StringUtil;

public class TaskDao {
	//显示所有任务信息，以及查询功能
	public ResultSet taskList(Connection con,PageBean pageBean, Task task) throws Exception{
		//String userAddress repairer    state   publishTime   
		
		StringBuffer sb=new StringBuffer("select * from t_repair where state in('待维修','已维修') order by publishTime desc ");
		
		//如果故障地点不为空，其他为空，则查询满足该古战地点的记录
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%'");
		}
		
		//如果维护者不为空，则查询满足该维修人员的记录，其他为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer like '%"+task.getRepairer()+"%'");
		}
		
		//如果状态不为空，则查询满足该状态记录，其他为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and state like '%"+task.getState()+"%'");
		}
		
		//如果报修时间不为空，则查询满足其他该状态的记录，其他为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%'");
		}
		
		//如果故障地点和维护人员都不为空的时候，状态和报修时间为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like'%"+task.getRepairer()+"%'");
		}
		
		//如果故障地点和状态都不为空，维护人员和报修时间为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%'");
		}
		
		//如果故障地点和报修时间不为空，故障地点和报修时间
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		}
		
		
		//如果维修人员和状态不为空，故障地点和报修时间为空
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%'");
		}
		
		
		//维修人员和报修时间不为空，故障地点和状态为空
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getState())){
			sb.append(" and repairer like '%"+task.getRepairer()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
        }
		
		//状态和报修时间不为空，故障地点和维修人员
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
        }
		
		//故障地点 ，维修人员，状态不为空，报修时间为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%'");
        }
		//故障地点，维修人员，报修时间不为空，状态为空;
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
        }
		
		//故障地点 ，状态，报修时间不为空，维修人员为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
        }
		
		//维修人员，状态，报修时间不为空，故障地点为空
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
        }
		
		//如果故障点，维修人员，状态,报修时间都不为空
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//统计任务记录数
	public int allTaskCount(Connection con, Task task) throws Exception{
		
				StringBuffer sb=new StringBuffer("select count(*) as total from t_repair where state in('待维修','已维修')");
				//如果故障地点不为空，其他为空，则查询满足该姓名的记录
				if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%'");
				}
				
				//如果维护者不为空，则查询满足该维修人员的记录，其他为空
				if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and repairer like '%"+task.getRepairer()+"%'");
				}
				
				//如果状态不为空，则查询满足该状态记录，其他为空
				if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and state like '%"+task.getState()+"%'");
				}
				
				//如果报修时间不为空，则查询满足其他该状态的记录，其他为空
				if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
					sb.append(" and publishTime like '%"+task.getState()+"%'");
				}
				
				//如果故障地点和维护人员都不为空的时候，状态和报修时间为空
				if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like'%"+task.getRepairer()+"%'");
				}
				
				//如果故障地点和状态都不为空，维护人员和报修时间为空
				if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%'");
				}
				
				//如果故障地点和报修时间不为空，故障地点和报修时间
				if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
				}
				
				
				//如果维修人员和状态不为空，故障地点和报修时间为空
				if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
							sb.append(" and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%'");
				}
				
				
				//维修人员和报修时间不为空，故障地点和状态为空
				if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getState())){
					sb.append(" and repairer like '%"+task.getRepairer()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		        }
				
				//状态和报修时间不为空，故障地点和维修人员
				if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
					sb.append(" and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		        }
				
				//故障地点 ，维修人员，状态不为空，报修时间为空
				if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%'");
		        }
				//故障地点，维修人员，报修时间不为空，状态为空;
				if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		        }
				
				//故障地点 ，状态，报修时间不为空，维修人员为空
				if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		        }
				
				//维修人员，状态，报修时间不为空，故障地点为空
				if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
					sb.append(" and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
		        }
				
				//如果故障点，维修人员，状态,报修时间都不为空
				if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())){
					sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer like '%"+task.getRepairer()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%'");
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
}
