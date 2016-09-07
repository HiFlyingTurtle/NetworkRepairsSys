<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上交任务</title>
<!-- 判断权限，是否登陆 -->
<!-- 判断权限，是否登陆 -->
<%@ include file="WEB-INF/right.jsp"%>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	//s_repairer
	//故障维修人员下拉框
	$('#s_repairer').combobox({
	url:'loadRepairer',
	valueField:'name',
	textField:'name',
	panelHeight:200
     });
	 //故障报修地点下拉框
	$('#s_userAddress').combobox({
	url:'loadLoaction',
	valueField:'location',
	textField:'location',
	panelHeight:200
});
})

//查询的javascript方法
function taskSearch(){
	$('#dg').datagrid('load',{
		repairer:$("#s_repairer").combobox("getValue"), //维修人员
		repairTime:$("#s_repairTime").datebox("getValue"),   //大屏报修时间
		userAddress:$("#s_userAddress").combobox("getValue")  //故障地点
	});
}

var url
//维修任务按钮事件，打开维修任务对话框
function taskReceiveDialog(){
	var selectedRows = $('#dg').datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择要维修的记录！","warning");
		return;
	}
	//维修任务只能一条一条的维修，不支持多条接收任务
	if(selectedRows.length>1){
		$.messager.alert("温馨提示","一次只能选择一条报修记录进行上交！","warning");
		return;
	}
	var row = selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","上交维修任务");
	$("#form").form("load",row);
	url="taskHandInSave?taskId="+row.taskId;
}

//对话框上面的保存按钮事件
function saveTask() {
	$("#form").form("submit",{
		url:url,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("温馨提示",result.errorMsg,"error");
				return;
			}
			else{
				$.messager.alert("温馨提示","任务上交成功！","info");
				$("#dlg").dialog("close");//同时关闭对话框
				$("#dg").datagrid("reload");//刷新数据
				resetDialogValue();//保存成功之后要清除对话框里面的文本框里面的数据
			}
		},
	});
}
//关闭对话框后要清除文本框里面的数据
function resetDialogValue(){
	$("#form").form('clear');
}
//对话框上面的取消按钮,即关闭对话框
function closeDialog(){
	$("#dlg").dialog("close")
	resetDialogValue();
}
</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="任务信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="taskHandIn" toolbar="#tb" fit="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th align="center" field="taskId" width="15">任务编号</th>
				<th align="center" field="publishTime" width="40">报修时间</th>
				<th align="center" field="userAddress" width="45">故障地点</th>
				<th align="center" field="type" width="30">故障类型</th>
				<th align="center" field="troubleDesc" width="80">故障描述</th>
				<th align="center" field="repairer" width="25">维修人员</th>
				<th align="center" field="finishTime" width="40">维修时间</th>
				<th align="center" field="state" width="20">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>任务管理>>待办事务</div><hr><br>
		<div style="color: black">相关操作：</div>
		<div title="相关操作">
			<a href="javascript:taskReceiveDialog()" class="easyui-linkbutton" iconCls="icon-handIn" plain="">上交任务</a>
		</div>
		<div title="查询条件" style="padding-top: 5px">
		     <!-- 维修人员：&nbsp;<input type="text" name="s_repairer" id="s_repairer"/>&nbsp;&nbsp;  --> 
			<!-- <input type="text" name="s_userAddress" id="s_userAddress"/>&nbsp;&nbsp; -->
			维修人员:&nbsp;<input  name="s_repairer" id="s_repairer" class="easyui-combobox">&nbsp;&nbsp;
			报修时间 ：<input type="text" name="s_repairTime" id="s_repairTime" class="easyui-datebox"/>&nbsp;&nbsp;
			故障地点：<input  name="s_userAddress" id="s_userAddress" class="easyui-combobox">&nbsp;&nbsp;
			
			
			<a title="维修" href="javascript:taskSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="">查询</a>
		</div>
	</div>
	<!-- 对话框，添加，修改时弹出的对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 500px;height: 440px" buttons="#dlg-button" title="操作对话框" closed="true">
		<form method="post" id="form" name="form">
			<table border="0" align="center" style="padding-top: 20px">
				<tr>
					<td >报修时间：</td>
					<td><input type="text" name="publishTime" id="publishTime" disabled="disabled"/></td>
					<td><font color="blue">***报修时间***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td >故障地点：</td>
					<td><input type="text" name="userAddress" id="userAddress" disabled="disabled"></td>
					<td><font color="blue">***报修大屏的位置***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td>故障类型：</td>
					<td><input type="text" name="type" id="type" disabled="disabled"></td>
					<td><font color="blue">***报修故障类型(硬件/软件)***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td valign="top">故障描述：</td>
					<td><textarea name="troubleDesc" id="troubleDesc" disabled="disabled"></textarea></td>
					<td valign="top"><font color="blue">***故障的简单描述***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td valign="top">维修人员：</td>
					<td><input type="text" name="repairer" id="repairer" disabled="disabled"></td>
					<td valign="top"><font color="blue">***维修者姓名***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td>维修时间：</td>
					<td><input type="text" name="finishTime" id="finishTime" class="easyui-datetimebox" required="true"/></td>
					<td><font color="blue">***维修结束的时间***</font></td>
				</tr>
				<tr height="5px"></tr>
				<tr>
					<td valign="top">处理方法：</td>
					<td><textarea id="dealWay" name="dealWay"></textarea></td>
					<td valign="top"><font color="blue">***处理方法的简单描述***</font></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 对话框的按钮，确定和取消 -->
	<div id="dlg-button">
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		<a href="javascript:saveTask()" class="easyui-linkbutton" iconCls="icon-ok">完成</a>
	</div>
</body>
</html>