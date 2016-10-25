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
$(function(){
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
	 //故障类型下拉框
	  //故障类型下拉框  type
	$('#tp').combobox({
	url:'loadType',
	valueField:'type',
	textField:'type',
	panelHeight:200
});
})

/**
$(function(){
   var curr_time = new Date();
   var endDate = curr_time.getFullYear()+"-";
   strDate += curr_time.getMonth()+1+"-";
   strDate += curr_time.getDate()+"-";
   strDate += curr_time.getHours()+":";
   strDate += curr_time.getMinutes()+":";
   strDate += curr_time.getSeconds();
   alert(endDate);
   $("#s_repairTime_end").datebox("setValue", endDate); 
  });
  **/
//查询的javascript方法
//需要导出的条件查询参数
var exportCondition=null;
function taskSearch(){
	var queryData = {
			repairer:$("#s_repairer").combobox("getValue"), //维修人员
			repairTime:$("#s_repairTime").datebox("getValue"),//大屏报修开始时间
			repairTimeEnd:$("#s_repairTime_end").datebox("getValue"),//大屏报修开始时间
			userAddress:$("#s_userAddress").combobox("getValue"),  //故障地点
			state:$("#s_state").combobox("getValue"),//维修状态
			faultType:$("#tp").combobox("getValue")//故障类型
	}
	$('#dg').datagrid('load',{
            repairer:$("#s_repairer").combobox("getValue"), //维修人员
			repairTime:$("#s_repairTime").datebox("getValue"),//大屏报修开始时间
			repairTimeEnd:$("#s_repairTime_end").datebox("getValue"),//大屏报修开始时间
			userAddress:$("#s_userAddress").combobox("getValue"),  //故障地点
			state:$("#s_state").combobox("getValue"),//维修状态
			faultType:$("#tp").combobox("getValue")//故障类型
	});
	exportCondition=queryData;
}

//导出报修记录的js，打开一个新窗口
  function taskExport(){
   //alert(postdata);
   /**
    //使用ajax的方式实现excel表的导出，有时间进行实现 
	 $.ajax({
		 type: "POST",
		 url:'exportExcelWithCondition',
		 data: postData,
         success: function(data){	 
        	 window.location.href="";
         }
	 })
	**/
  // alert(exportCondition);
   if(exportCondition==null){
	   exportCondition={
				repairer:"", //维修人员
				repairTime:"",//大屏报修开始时间
				repairTimeEnd:"",//大屏报修开始时间
				userAddress:"",  //故障地点
				state:"",//维修状态
				faultType:""//故障类型
		}
   }
   var postdata=JSON.stringify(exportCondition);;
   postdata=encodeURI(encodeURI(postdata));
   var url="exportExcelWithCondition?postdata="+postdata;
   //这种打开方式需要对参数进行编码，否则会出现乱码
   window.open(url);
  }
</script>
</head>
<body style="margin: 5px">
	<table id="dg" title="任务信息" class="easyui-datagrid" fitColumns="true"
	  pagination="true" rownumbers="true" url="task" toolbar="#tb" fit="true" nowrap="false">
		<thead>
			<tr>
				<th align="center" field="taskId" width="15">任务编号</th>
				<th align="center" field="publishTime" width="35">报修时间</th>
				<th align="center" field="userAddress" width="25">故障地点</th>
				<th align="center" field="type" width="30">故障类型</th>
				<th align="center" field="troubleDesc" width="70">故障描述</th>
				<th align="center" field="repairer" width="20">维修人员</th>
				<th align="center" field="finishTime" width="35">维修时间</th>
				<th align="center" field="dealWay" width="35" nowrap="false">解决方法</th>
				<th align="center" field="state" width="20">状态</th>
			</tr>
		</thead>
	</table>
	<!-- 工具条，基本操作 -->
	<div id="tb" style="padding-top: 10px;padding-bottom: 10px;padding-left: 10px">
		<div title="您的位置">您的位置：导航菜单>>任务管理>>历史记录</div><hr><br>
		<div style="color: black;padding:">相关操作：</div>
		<div title="相关操作">
		<a title="导出" href="javascript:taskExport()" class="easyui-linkbutton" iconCls="icon-export">导出报修</a>
		</div>
		<div title="查询条件">
			维修人员：&nbsp;<input type="text" name="s_repairer" id="s_repairer" class="easyui-combobox"  />&nbsp;&nbsp;
			开始时间：<input type="text" name="s_repairTime" id="s_repairTime" class="easyui-datebox"/>&nbsp;&nbsp;
		          结束时间：<input type="text" name="s_repairTime_end" id="s_repairTime_end" class="easyui-datebox"/>&nbsp;&nbsp;
			故障地点：<input type="text" name="s_userAddress" id="s_userAddress" class="easyui-combobox"/>&nbsp;
			状态：&nbsp;<select class="easyui-combobox" name="s_state" id="s_state" editable="false" panelHeight="auto">
				<option value="">--请选择--</option>
				<option value="待维修">待维修</option>
				<option value="已维修">已维修</option>  
			</select> &nbsp;
			故障类型：<input  name="type" id="tp" class="easyui-combobox" value="--请选择维故障类型--"></td>
			<a title="查询" href="javascript:taskSearch()" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>