<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上交任务</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.2/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$(function(){
	$("#dlg").dialog("open").dialog("setTitle","上交维修任务");
});
function saveTask(){
	window.close();
}
</script>
</head>
<body>
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