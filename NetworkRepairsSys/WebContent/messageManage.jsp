<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>任务维护</title>
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
//删除的javascript方法
function messageDelete(){
	var selectedRows=$('#dg').datagrid('getSelections');
	//判断是否选择了要删除的数据
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择您要删除的数据！","warning");
		return;
	}
	//获取选择的所有id
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].messId);
	}
	var ids=strIds.join(",");
	$.messager.confirm("温馨提示", "您确定要删除这<font color=red size=4>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("messageDel",{delIds:ids},function(result){
				if(result.success){
					$.messager.alert("温馨提示","您已成功删除<font color=red size=4>"+result.delNums+"</font>条数据！","info");
					$("#dg").datagrid("reload");//删除成功后刷新数据
				}
				else{
					$.messager.alert("温馨提示",result.errorMsg,"error");
				}
			},"json");
		}
	});
}
//添加按钮事件，打开添加数据对话框
var url
function messageAddDialog(){
	$("#dlg").dialog("open").dialog("setTitle","添加通知");
	url="messageSave";
}
//修改按钮事件，打开修改数据对话框
function messageUpdateDialog(){
	var selectedRows = $('#dg').datagrid('getSelections');
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择要修改的数据！","warning");
		return;
	}
	if(selectedRows.length>1){
		
		$.messager.alert("温馨提示","请选择一条数据进行修改！","warning");
		return;
	}
	var row = selectedRows[0];
	$("#dlg").dialog("open").dialog("setTitle","修改通知");
	$("#form").form("load",row);
	url="messageSave?messId="+row.messId;
}
//关闭对话框后要清除文本框里面的数据
function resetDialogValue(){
	$("#who").val("");
	$("#content").val("");
	$("#form").form('clear');
	$("#time").datetimebox("getValue")=="";//这个不知应该用什么方法，这样不能清除
}


//对话框上面的保存按钮事件
function saveMessage() {
	
	//获取输入的日期
	//var date = document.getElementById("publishTime").value 这样获取不了;
	var date = $("#time").datetimebox("getValue")
	
	var obj1 = /^(\d{4}|\d{2})-((0?([1|3-9]))|(1[0|1|2]))-((0?[1-9])|([1-2]([0-9]))|(3[0|1]))([\s\S]*)$/;//匹配除了二月的的年份格式
	var obj2 = /^(\d{4}|\d{2})-(0?2)-(0?[1-9]|[1-2][0-9])([\s\S]*)$/;//匹配2月的年份格式，就是说没有30,31号
	
	if((obj1.test(date)) == false && (obj2.test(date)) == false){
		alert("日期格式不正确");
		return;
	}
	
	
	$("#form").form("submit",{
		url:url,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("温馨提示",result.errorMsg,"error");
				return;
			}
			else{
				$.messager.alert("温馨提示","发布通知成功！","info");
				$("#dlg").dialog("close");//同时关闭对话框
				$("#dg").datagrid("reload");//刷新数据
				resetDialogValue();//保存成功之后要清除对话框里面的文本框里面的数据
			}
		},
	});
}
//对话框上面的取消按钮,即关闭对话框
function closeDialog(){
	$("#dlg").dialog("close");
	$("#form").form('clear');
}
</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="通知" class="easyui-datagrid" fitColumns="true"
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
		<div title="您的位置">您的位置：导航菜单>>任务管理>>消息管理</div><hr><br>
		说明：
		<div>1、支持多值查询和模糊查询,没有条件则显示所有<br>
		2、删除至少选择一条记录,修改只能选择一条记录<br></div><br>
		<div style="color: black">相关操作：</div>
		<div title="相关操作">
			<a title="添加" href="javascript:messageAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="">添加</a>
			<a title="删除" href="javascript:messageDelete()" class="easyui-linkbutton" iconCls="icon-cancel" plain="">删除</a>
			<a title="修改" href="javascript:messageUpdateDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="">修改</a>
		</div>
		<div title="查询条件" style="padding-top: 5px">
			&nbsp;通知对象：&nbsp;<input type="text" name="s_who" id="s_who"/>&nbsp;
			通知发布时间：&nbsp;<input type="text" name="s_time" id="s_time" class="easyui-datebox"/>&nbsp;
			<a title="查询" href="javascript:messageSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<!-- 对话框，添加，修改时弹出的对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 450px;height: 375px" buttons="#dlg-button" title="操作对话框" closed="true">
		<form method="post" id="form" name="form">
			<table border="0" align="center">
				
				
				<tr>
					<td colspan="3" align="center"><label style="font-size: 20px">通知</label></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td>通知对象：</td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td><input type="text" name="who" id="who" class="easyui-validatebox" required="true"></td>
					<td colspan="2"><font color="red">***全体职员或者某个职员***</font></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td colspan="3">内容：</td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td colspan="3"><textarea style="text-indent: 2em;" cols="44" rows="7" name="content" id="content" class="easyui-validatebox" required="true"></textarea></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td width="150px"></td>
					<td width="120px" align="right">通&nbsp;知&nbsp;人：</td>
					<td width="60px"><input type="text" name="infoment" id="infoment" size="15" value="${myName}" class="easyui-validatebox" required="true"></td>
				</tr>
				<tr height="3px"></tr>
				<tr>
					<td width="150px"></td>
					<td width="120px" align="right">通知时间：</td>
					<td width="60px"><input type="text" name="time" id="time" size="15" class="easyui-datetimebox" class="easyui-validatebox" required="true"></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 对话框的按钮，确定和取消 -->
	<div id="dlg-button">
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
		<a href="javascript:saveMessage()" class="easyui-linkbutton" iconCls="icon-ok">确认</a>
	</div>
</body>
</html>