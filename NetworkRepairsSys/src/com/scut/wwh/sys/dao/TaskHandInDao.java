package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.model.Task;
import com.scut.wwh.sys.model.User;
import com.scut.wwh.sys.util.StringUtil;

public class TaskHandInDao {
	//��ʾ����ά����������Ϣ���Լ���ѯ����
	public ResultSet taskList(Connection con,PageBean pageBean, Task task,User user) throws Exception{
		//and repairer='"+task.getRepairer()+"'   order by publishTime desc
		//StringBuffer sb=new StringBuffer("select * from t_repair where state = '��ά��' and repairer like '%"+user.getMyName()+"%'");
		
		StringBuffer sb=new StringBuffer("select * from t_repair where state = '��ά��' and repairer='"+user.getName()+"'");
		//StringBuffer sb=new StringBuffer("select * from t_repair where state = '��ά��'");
		
		//���ά����Ա������ʱ�䣬���ϵص㶼Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" order by publishTime desc");
		}
		
		//���ά����Ա��Ϊ�գ�����Ϊ�գ����ѯ�����ά����Ա�ļ�¼��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc");
		}
		//����λ�ò�Ϊ�գ����ѯ����� ����λ�õļ�¼������Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//����û�����ʱ�䲻Ϊ�գ����ѯ����ü�¼������Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//���ά����Ա�͹��ϵص㶼��Ϊ�յ�ʱ�򣬱���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//���ά����Ա�ͱ���ʱ�䶼��Ϊ�գ����ϵص�Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//�������λ�úͱ���ʱ�䶼��Ϊ�գ�ά����ԱΪ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//���ά����Ա��������ַ��ά��ʱ�䶼��Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	
	//ͳ��ά���е������¼��
	public int taskHandInDaoCount(Connection con, Task task,User user) throws Exception{
		//and repairer like '%"+user.getMyName()+"%'
		//and repairer='"+user.getName()+"' 
		StringBuffer sb=new StringBuffer("select count(*) as total from t_repair where state = '��ά��' and repairer='"+user.getName()+"' ");
		//���ά����Ա������ʱ�䣬���ϵص㶼Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
		   sb.append(" order by publishTime desc");
		}
				
		//���ά����Ա��Ϊ�գ�����Ϊ�գ����ѯ�����ά����Ա�ļ�¼��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
		   sb.append(" and repairer='"+task.getRepairer()+"' order by publishTime desc");
		}
		//����λ�ò�Ϊ�գ����ѯ����� ����λ�õļ�¼������Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//����û�����ʱ�䲻Ϊ�գ����ѯ����ü�¼������Ϊ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//���ά����Ա�͹��ϵص㶼��Ϊ�յ�ʱ�򣬱���ʱ��Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and userAddress='"+task.getUserAddress()+"' order by publishTime desc");
		}
		//���ά����Ա�ͱ���ʱ�䶼��Ϊ�գ����ϵص�Ϊ��
		if(StringUtil.isNotEmpty(task.getRepairer()) && StringUtil.isEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and repairer='"+task.getRepairer()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//�������λ�úͱ���ʱ�䶼��Ϊ�գ�ά����ԱΪ��
		if(StringUtil.isEmpty(task.getRepairer()) && StringUtil.isNotEmpty(task.getUserAddress()) && StringUtil.isNotEmpty(task.getPublishTime())){
			sb.append(" and userAddress='"+task.getUserAddress()+"' and publishTime like '%"+task.getPublishTime()+"%' order by publishTime desc");
		}
		//���ά����Ա��������ַ��ά��ʱ�䶼��Ϊ��
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
	
	//�Ͻ����޵��¼����൱���޸����ʱ�䣬����״̬�ʹ�����
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
