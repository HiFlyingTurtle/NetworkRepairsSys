<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>所有任务浏览</title>
<!-- 判断权限，是否登陆 -->
    <!-- 判断权限，是否登陆 -->
    <%@ include file="WEB-INF/right.jsp"%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//查询的javascript方法
function taskSearch(){
	$('#dg').datagrid('load',{
		userName:$("#s_userName").val(),       //大屏名称
		repairer:$("#s_repairer").val(), 		//维护人员
		state:$("#s_state").combobox("getValue"),//维修状态
	});
}
</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="任务信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="task" toolbar="#tb" fit="true">
		<thead>
			<tr>
				<th align="center" field="taskId" width="15">任务编号</th>
				<th align="center" field="userName" width="30">大屏名称</th>
				<th align="center" field="publishTime" width="35">报修时间</th>
				<th align="center" field="userAddress" width="25">大屏地址</th>
				<th align="center" field="type" width="30">故障类型</th>
				<th align="center" field="troubleDesc" width="70">故障描述</th>
				<th align="center" field="repairer" width="20">维修人员</th>
				<th align="center" field="finishTime" width="35">维修时间</th>
				<th align="center" field="dealWay" width="35">解决方法</th>
				<th align="center" field="state" width="20">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>任务管理>>所有任务浏览</div><hr><br>
		<div style="color: black;padding:">查询：</div>
		<div title="查询条件">
			大屏名称：&nbsp;<input type="text" name="s_userName" id="s_userName"/>&nbsp;
			&nbsp;维修人员：&nbsp;<input type="text" name="s_repairer" id="s_repairer"/>&nbsp;
			&nbsp;状态：&nbsp;<select class="easyui-combobox" name="s_state" id="s_state" editable="false" panelHeight="auto">
				<option value="">--请选择--</option>
				<option value="待维修">待维修</option>
				<option value="已维修">已维修</option>
			</select> &nbsp;
			<a title="查询" href="javascript:taskSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>