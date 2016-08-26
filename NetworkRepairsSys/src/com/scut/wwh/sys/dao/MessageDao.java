package com.scut.wwh.sys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scut.wwh.sys.model.Message;
import com.scut.wwh.sys.model.PageBean;
import com.scut.wwh.sys.util.StringUtil;

public class MessageDao {
	//显示所有通知信息，以及查询功能
	public ResultSet messageList(Connection con,PageBean pageBean, Message message) throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_message where title ='通知'");
		//如果时间不为空,对象为空
		if(StringUtil.isNotEmpty(message.getTime()) && StringUtil.isEmpty(message.getWho())){
			sb.append(" and time like '%"+message.getTime()+"%'");
		}
		//如果通知对象不为空，时间为空
		if(StringUtil.isNotEmpty(message.getWho()) && StringUtil.isEmpty(message.getTime())){
			sb.append(" and who like '%"+message.getWho()+"%'");
		}
		//如果时间不为空和对象都不为空
		if(StringUtil.isNotEmpty(message.getTime()) && StringUtil.isNotEmpty(message.getWho())){
			sb.append(" and time like '%"+message.getTime()+"%' and who like '%"+message.getWho()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	//统计任务记录数
	public int messageCount(Connection con, Message message) throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_message where title ='通知'");
		//如果时间不为空,对象为空
		if(StringUtil.isNotEmpty(message.getTime()) && StringUtil.isEmpty(message.getWho())){
			sb.append(" and time like '%"+message.getTime()+"%'");
		}
		//如果通知对象不为空，时间为空
		if(StringUtil.isNotEmpty(message.getWho()) && StringUtil.isEmpty(message.getTime())){
			sb.append(" and who like '%"+message.getWho()+"%'");
		}
		//如果时间不为空和对象都不为空
		if(StringUtil.isNotEmpty(message.getTime()) && StringUtil.isNotEmpty(message.getWho())){
			sb.append(" and time like '%"+message.getTime()+"%' and who like '%"+message.getWho()+"%'");
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
	//删除通知事件
	public int messageDel(Connection con, String delIds) throws Exception {
		String sql="delete from t_message where messId in("+delIds+")";
		PreparedStatement ps=con.prepareStatement(sql);
		return ps.executeUpdate();
	}
	//添加通知事件
	public int messageAdd(Connection con,Message message) throws Exception{
		String sql="insert into t_message() value(null,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, message.getTitle());
		ps.setString(2, message.getWho());
		ps.setString(3, message.getContent());
		ps.setString(4, message.getTime());
		ps.setString(5, message.getInfoment());
		return ps.executeUpdate();
	}
	//修改通知事件
	public int messageUpdate(Connection con, Message message) throws Exception{
		String sql="update t_message set who=?, content=?, time=?, infoment=? where messId=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, message.getWho());
		ps.setString(2, message.getContent());
		ps.setString(3, message.getTime());
		ps.setString(4, message.getInfoment());
		ps.setInt(5, message.getMessId());
		return ps.executeUpdate();
	}
}
