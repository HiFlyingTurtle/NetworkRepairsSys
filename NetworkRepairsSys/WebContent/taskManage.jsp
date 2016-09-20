<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
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

$(function(){
	//s_repairer
	//故障维修人员下拉框
	$('#s_repairer').combobox({
	url:'loadRepairer',
	valueField:'name',
	textField:'name',
	panelHeight:200
});	
})

//查询的javascript方法
function taskSearch(){
	$('#dg').datagrid('load',{
		repairer:$("#s_repairer").combobox("getValue"), //维修者
		state:$("#s_state").combobox("getValue"),//状态
	});
}

//删除的javascript方法
function taskDelete(){
	var selectedRows=$('#dg').datagrid('getSelections');
	//判断是否选择了要删除的数据
	if(selectedRows.length==0){
		$.messager.alert("温馨提示","请选择您要删除的数据！","warning");
		return;
	}
	//获取选择的所有id
	var strIds=[];
	for(var i=0;i<selectedRows.length;i++){
		strIds.push(selectedRows[i].taskId);
	}
	var ids=strIds.join(",");
	$.messager.confirm("温馨提示", "您确定要删除这<font color=red size=4>"+selectedRows.length+"</font>条数据吗？",function(r){
		if(r){
			$.post("taskDel",{delIds:ids},function(result){
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

function loadingLocation(){
	$("#userAddress").html("");
	var str="所有地点";
	 $.ajax({
		 type:"post",
		 dataType:"json",
		 url:"loadLoaction",
		 success:function(data){
			 var jsonData=data.info;
			//alert("data"+data.info);
			 for(var i=0,n=jsonData.length;i<n;i++){
				 $("#userAddress").append("<option  value='"+jsonData[i].location+"'>"+jsonData[i].location+"</option>");
			 }
		 }
	 });
}

function loadingRepairer(){
	$("#repairer").html("");
	 $.ajax({
		 type:"post",
		 dataType:"json",
		 url:"loadRepairer",
		 success:function(data){
			 var jsonData=data.info;
			//alert("data"+data.info);
			 for(var i=0,n=jsonData.length;i<n;i++){
				 $("#repairer").append("<option  value='"+jsonData[i].name+"'>"+jsonData[i].name+"</option>");
			 }
		 }
	 });
}


//添加按钮事件，打开添加数据对话框
var url
function taskAddDialog(){
	$("#dlg").dialog("open").dialog("setTitle","新建报修任务");
	    //故障报修地点下拉框
		$('#userAddress').combobox({
		url:'loadLoaction',
		valueField:'location',
		textField:'location',
		panelHeight:200
	});
	   //故障维修人员下拉框
		$('#repairer').combobox({
		url:'loadRepairer',
		valueField:'name',
		textField:'name',
		panelHeight:200
	});
	   
	   //故障类型下拉框  type
		$('#tp').combobox({
		url:'loadType',
		valueField:'type',
		textField:'type',
		panelHeight:200
	});
	   
	url="taskSave";
}

//关闭对话框后要清除文本框里面的数据
function resetDialogValue(){
	$("#userAddress").val("");
	$("#troubleDesc").val("");
	$("#type").val("");
	$("#form").form('clear');
	$("#publishTime").datetimebox("getValue")=="";//这个不知应该用什么方法，这样不能清除
}

//对话框上面的保存按钮事件
function saveTask() {
	//获取输入的电话号码的值
	//var phone = document.getElementById("phone").value;
	
	//定义电话的正则表达式表达式,/^表示正则表达式的开始，$/表示正则表达式的结束，\d表示匹配整数字符，{n}表示匹配多少位。
	//该正则表达式可匹配000-12345678,0000-1234567,12346678,1234567和12345678901电话号码格式。
	//但一般只用到0000-1234567,1234567和12345678901
	//var objExp = /^(\d{3})-(\d{8})|(\d{4})-(\d{7})|\d{7}|\d{8}|\d{11}$/;
	var obj = /^\d{4}-\d{7}|\d{7}|\d{11} &/;
	
	/*
	if(obj.test(phone) == false){
		alert("电话格式不正确");
		return;
	}
	**/
	
	//获取输入的日期
	//var date = document.getElementById("publishTime").value;
	var date = $("#publishTime").datetimebox("getValue")
	
	//日期1993-02-22 ([\s\S]*)表示匹配任意字符
	//var obj = /^(\d{4}|\d{2})-((0?([1-9]))|(1[0|1|2]))-((0?[1-9])|([12]([1-9]))|(3[0|1]))$/;
	var obj1 = /^(\d{4}|\d{2})-((0?([1|3-9]))|(1[0|1|2]))-((0?[1-9])|([1-2]([0-9]))|(3[0|1]))([\s\S]*)$/;//匹配除了二月的的年份格式
	var obj2 = /^(\d{4}|\d{2})-(0?2)-(0?[1-9]|[1-2][0-9])([\s\S]*)$/;//匹配2月的年份格式，就是说没有30,31号
	
	if((obj1.test(date)) == false && (obj2.test(date)) == false){
		alert("日期格式不正确");
		return;
	}
	
	//var userAddress=$('#ua').combobox('getText');
	$("#form").form("submit",{
		url:url,
		success:function(result){
			if(result.errorMsg){
				$.messager.alert("温馨提示",result.errorMsg,"error");
				return;
			}
			else{
				$.messager.alert("温馨提示","发布报修任务成功！","info");
				$("#dlg").dialog("close");//同时关闭对话框
				$("#dg").datagrid("reload");//刷新数据
				resetDialogValue();//保存成功之后要清除对话框里面的文本框里面的数据
			}
		},
	});
}

//对话框上面的取消按钮,即关闭对话框
function closeDialog(){
	$("#dlg").dialog("close")
	resetDialogValue();
}
//导出报修记录的js，打开一个新窗口
function taskExport(){
	window.open('exportTask');
}


</script>
</head>
<body style="margin: 5px">
    <!-- field的名字要与数据库表的列的字段名保持一致 -->
	<table id="dg" title="任务信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="taskManage" toolbar="#tb" fit="true" nowrap="false" >
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th align="center" field="taskId" width="15">任务编号</th>
				<th align="center" field="publishTime" width="40">报修时间</th>
				<th align="center" field="userAddress" width="30">故障地点</th>
				<th align="center" field="type" width="30">故障类型</th>
				<th align="center" field="troubleDesc" width="50" nowrap="false">故障描述</th>
				<th align="center" field="repairer" width="20">维修人员</th>
			    <th align="center" field="finishTime" width="40">维修时间</th>
				<th align="center" field="dealWay" width="70" nowrap="false">处理方法</th>
				<th align="center" field="state" width="20">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>任务管理>>任务维护</div><hr><br>
		<div style="color: black">相关操作：</div><br>
		<div title="相关操作">
			<a title="添加" href="javascript:taskAddDialog()" class="easyui-linkbutton" iconCls="icon-add">新建报修</a>
			<a title="删除" href="javascript:taskDelete()" class="easyui-linkbutton" iconCls="icon-cancel">删除报修</a>
			<a title="导出" href="javascript:taskExport()" class="easyui-linkbutton" iconCls="icon-export">导出报修</a>
		</div>
		<div title="查询条件" style="padding-top: 5px">
           <!--  维修人员：&nbsp;<input type="text" name="s_repairer" id="s_repairer"/>&nbsp; -->    
                              维修人员:&nbsp;<input  name="s_repairer" id="s_repairer" class="easyui-combobox">&nbsp;                 
			状态：&nbsp;<select class="easyui-combobox" name="s_state" id="s_state" editable="false" panelHeight="auto">
				<option value="">--请选择--</option>
				<option value="待维修">待维修</option>
				<option value="已维修">已维修</option>
			</select> &nbsp;
			<a title="查询" href="javascript:taskSearch()" class="easyui-linkbutton" iconCls="icon-search" plain="">查询</a>
		</div>
	</div>
	<!-- 对话框，添加，修改时弹出的对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 600px;height: 450px" buttons="#dlg-button" title="操作对话框" closed="true">
		<form method="post" id="form" name="form">
			<table align="center" style="padding-top: 30px;padding-bottom: 30px;">
				<tr>
					<td >报修时间：</td>
					<td><input type="text" name="publishTime" id="publishTime" class="easyui-datetimebox" required="true"/></td>
					<td><font color="blue">***大屏报修时间***</font></td>
				</tr>
				<tr height="10px"></tr>
				<tr>
					<td >故障地点：</td>
					<td><input  name="userAddress" id="userAddress" class="easyui-combobox" value="--请选择报修地点--"></td>
					<td><font color="blue">***大屏的故障地点***</font></td>
				</tr>
				<tr height="10px"></tr>
				
				
				 <tr>
					<td >维修人员：</td>
					<td><input  name="repairer" id="repairer" class="easyui-combobox" value="--请选择维修人员--"></td>
					<td><font color="blue">***大屏的维修人员***</font></td>
				</tr>
				<tr height="10px"></tr>
				
				<tr>
				<td>故障类型：</td>
				<td><input  name="type" id="tp" class="easyui-combobox" value="--请选择维故障类型--"></td>
				<td><font color="blue">***报修故障的类型(软件/硬件)***</font></td>
				</tr>
				<tr height="30px"></tr>
				
				<tr>
					<td valign="middle">故障描述：</td>
					<td><textarea cols="25" rows="6" name="troubleDesc" id="troubleDesc"></textarea></td>
					<td valign="middle"><font color="blue">&nbsp;&nbsp;***故障的简单描述***</font></td>
				</tr>
				<tr height="10px"></tr>
				
				 <!-- 
				 <tr>
					<td >故障地点：</td>
					<td> 
					<select id="userAddress" name="userAddress"  class="easyui-validatebox"  required="true">
					<option value="">所有地点</option>
					</select> 
					</td>
					<td><font color="red">***大屏报修的地点***</font></td>
				</tr>
			
				    <select id="repairer" name="repairer"  class="easyui-validatebox"  required="true">
					   <option value="">所有人员</option>
					</select> 
			
				<tr>
					<td >维修人员：</td>
					<td><input type="text" name="repairer" id="repairer" class="easyui-validatebox"  required="true"></td>
					<td><font color="red">***大屏维修的人员***</font></td>
				</tr>
				
				<tr>
					<td>故障类型：</td>
					<td><input type="text" name="type" id="type" class="easyui-validatebox" required="true"></td>
					<td><font color="red">***报修故障的类型(软件/硬件)***</font></td>
				</tr>
				<select class="easyui-combobox" name="type" id="type">
				<option value="">--请选择故障类型--</option>
				<option value="硬件">硬件</option>
				<option value="软件">软件</option>
				</select>
				-->
			</table>
		</form>
	</div>
	<!-- 对话框的按钮，确定和取消 -->
	<div id="dlg-button">
	    <a href="javascript:saveTask()" class="easyui-linkbutton" iconCls="icon-ok">确认</a>
		<a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
	</div>
</body>
</html>