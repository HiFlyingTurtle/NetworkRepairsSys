package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.util.StringUtil;

public class TaskDao {
	//��ʾ����������Ϣ���Լ���ѯ����
	public ResultSet taskList(Connection con,PageBean pageBean, Task task) throws Exception{
		//String userAddress repairer    state   publishTime   order by publishTime desc 
		//System.out.println("sql begining-----"+task.getRepairer());
		StringBuffer sb=new StringBuffer("select * from t_repair where state in('��ά��','��ά��')");
		
		//������ϵ㣬ά����Ա��״̬,����ʱ�䶼Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&& StringUtil.isEmpty(task.getPublishTime())){
		    sb.append(" order by publishTime desc");
		}
		
		//������ϵص㲻Ϊ�գ�����Ϊ�գ����ѯ����ù�ս�ص�ļ�¼
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' order by publishTime desc ");
		}
		
		//���ά���߲�Ϊ�գ����ѯ�����ά����Ա�ļ�¼������Ϊ�� repairer='"+user.getName()+"'
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//���״̬��Ϊ�գ����ѯ�����״̬��¼������Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//�������ʱ�䲻Ϊ�գ����ѯ����������״̬�ļ�¼������Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
		}
		
		//������ϵص��ά����Ա����Ϊ�յ�ʱ��״̬�ͱ���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//������ϵص��״̬����Ϊ�գ�ά����Ա�ͱ���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//������ϵص�ͱ���ʱ�䲻Ϊ�գ����ϵص�ͱ���ʱ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
		}
		
		
		//���ά����Ա��״̬��Ϊ�գ����ϵص�ͱ���ʱ��Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		
		//ά����Ա�ͱ���ʱ�䲻Ϊ�գ����ϵص��״̬Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getState())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//״̬�ͱ���ʱ�䲻Ϊ�գ����ϵص��ά����Ա
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//���ϵص� ��ά����Ա��״̬��Ϊ�գ�����ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
        }
		//���ϵص㣬ά����Ա������ʱ�䲻Ϊ�գ�״̬Ϊ��;
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"'and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//���ϵص� ��״̬������ʱ�䲻Ϊ�գ�ά����ԱΪ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//ά����Ա��״̬������ʱ�䲻Ϊ�գ����ϵص�Ϊ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//������ϵ㣬ά����Ա��״̬,����ʱ�䶼��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
		}
		
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//ͳ�������¼��
	public int allTaskCount(Connection con, Task task) throws Exception{
		 
		StringBuffer sb=new StringBuffer("select count(*) as total from t_repair where state in('��ά��','��ά��')");
		//������ϵ㣬ά����Ա��״̬,����ʱ�䶼Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&& StringUtil.isEmpty(task.getPublishTime())){
		    sb.append(" order by publishTime desc");
		}
		
		//������ϵص㲻Ϊ�գ�����Ϊ�գ����ѯ����ù�ս�ص�ļ�¼
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' order by publishTime desc ");
		}
		
		//���ά���߲�Ϊ�գ����ѯ�����ά����Ա�ļ�¼������Ϊ�� repairer='"+user.getName()+"'
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//���״̬��Ϊ�գ����ѯ�����״̬��¼������Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//�������ʱ�䲻Ϊ�գ����ѯ����������״̬�ļ�¼������Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
		}
		
		//������ϵص��ά����Ա����Ϊ�յ�ʱ��״̬�ͱ���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' order by publishTime desc ");
		}
		
		//������ϵص��״̬����Ϊ�գ�ά����Ա�ͱ���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		//������ϵص�ͱ���ʱ�䲻Ϊ�գ����ϵص�ͱ���ʱ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getState())&&StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
		}
		
		
		//���ά����Ա��״̬��Ϊ�գ����ϵص�ͱ���ʱ��Ϊ��
		if(StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&&StringUtil.isEmpty(task.getPublishTime())){
					sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
		}
		
		
		//ά����Ա�ͱ���ʱ�䲻Ϊ�գ����ϵص��״̬Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getState())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//״̬�ͱ���ʱ�䲻Ϊ�գ����ϵص��ά����Ա
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//���ϵص� ��ά����Ա��״̬��Ϊ�գ�����ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' order by publishTime desc ");
        }
		//���ϵص㣬ά����Ա������ʱ�䲻Ϊ�գ�״̬Ϊ��;
		if(StringUtil.isEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"'and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//���ϵص� ��״̬������ʱ�䲻Ϊ�գ�ά����ԱΪ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isNotEmpty(task.getUserAddress())&&StringUtil.isEmpty(task.getRepairer())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//ά����Ա��״̬������ʱ�䲻Ϊ�գ����ϵص�Ϊ��
		if(StringUtil.isNotEmpty(task.getState()) && StringUtil.isNotEmpty(task.getPublishTime()) && StringUtil.isEmpty(task.getUserAddress())&&StringUtil.isNotEmpty(task.getRepairer())){
			sb.append(" and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
        }
		
		//������ϵ㣬ά����Ա��״̬,����ʱ�䶼��Ϊ��
		if(StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getState())&& StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress like '%"+task.getUserAddress()+"%' and repairer='"+task.getRepairer()+"' and state like '%"+task.getState()+"%' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc ");
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
	//ɾ�����޼�¼�¼�
	public int taskDel(Connection con, String delIds) throws Exception {
		String sql="delete from t_repair where taskId in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	//��ӱ�������
	public int taskAdd(Connection con,Task task) throws Exception{
		String sql="insert into t_repair(taskId,userName,publishTime,userAddress,type,troubleDesc,repairer,state) value(null,?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, task.getUserName());
		ps.setString(2, task.getPublishTime());
		ps.setString(3, task.getUserAddress());
		//��������
		ps.setString(4, task.getType());
		ps.setString(5, task.getTroubelDesc());
		ps.setString(6, task.getRepairer());
		ps.setString(7, task.getState());
		return ps.executeUpdate();
	}
	
	
	//�������޼�¼�嵥��List
	public ResultSet exportTaskList(Connection con,PageBean pageBean)throws Exception{

		StringBuffer sb=new StringBuffer("select * from t_repair where state in('��ά��','ά����','��ά��')");
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
