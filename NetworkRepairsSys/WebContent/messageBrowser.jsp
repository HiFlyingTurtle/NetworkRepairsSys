<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息管理</title>
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
function messageSearch(){
	$('#dg').datagrid('load',{
		time:$("#s_time").datebox("getValue"),       //通知发布时间
		who:$("#s_who").val(),						//通知对象
	});
}
//查看详细信息事件，打开数据对话框
function messageShowDialog(){
	var selectedRows = $('#dg').datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择要查看的通知！","warning");
		return;
	}
	if(selectedRows.length>1){
		$.messager.alert("温馨提示","请选择一条数据进行查看！","warning");
		return;
	}
	var row = selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","查看通知详细信息");
	$("#form").form("load",row);
}

//关闭对话框后要清除文本框里面的数据
function resetDialogValue(){
	$("#form").form('clear');
}

//对话框上面的取消按钮
function closeDialog(){
	$("#dlg").dialog("close")
	resetDialogValue();
}

</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="通知信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="message" toolbar="#tb" fit="true">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th align="center" field="messId" width="20">信息编号</th>
				<th align="center" field="title" width="30">标题</th>
				<th align="center" field="who" width="25">通知对象</th>
				<th align="center" field="content" width="100">具体内容</th>
				<th align="center" field="time" width="35">通知时间</th>
				<th align="center" field="infoment" width="20">通知人</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>消息管理>>查看通知</div><hr><br>
		说明：
		<div>1、当前页面是查看所有通知,支持多值查询和模糊查询,没有条件则显示所有<br>
		2、查看详细信息只能选择一条记录</div><br>
		<div style="color: black">相关操作：</div>
		<div title="相关操作">
			<a title="查看" href="javascript:messageShowDialog()" class="easyui-linkbutton" iconCls="icon-tip" plain="">查看详细信息</a>
		</div>
		<div title="查询条件" style="padding-top: 5px">
			&nbsp;通知对象：&nbsp;<input type="text" name="s_who" id="s_who"/>&nbsp;
			通知发布时间：&nbsp;<input type="text" name="s_time" id="s_time" class="easyui-datebox"/>&nbsp;
			<a title="查询" href="javascript:messageSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- 对话框，添加，修改时弹出的对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 430px;height: 340px" buttons="#dlg-button" title="操作对话框" closed="true">
		<form method="post" id="form" name="form">
			<table align="center">
				
				<tr>
					<td colspan="3" align="center"><label style="font-size: 20px">通知</label></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td colspan="3"><input type="text" name="who" id="who" readonly="readonly"></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td colspan="3"><textarea style="text-indent: 2em;" cols="44" rows="7" name="content" id="content" readonly="readonly"></textarea></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td width="150px"></td>
					<td width="120px" align="right">通知人：</td>
					<td width="60px"><input type="text" name="infoment" id="infoment" readonly="readonly" size="15"></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td width="150px"></td>
					<td width="120px" align="right">通知时间：</td>
					<td width="60px"><input type="text" name="time" id="time" readonly="readonly" size="15"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 对话框的按钮，确定和取消 -->
	<div id="dlg-button">
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>