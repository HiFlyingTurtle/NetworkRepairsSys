<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新视界传媒在线报修系统&nbsp;-&nbsp;用户登录</title>
<LINK href="jquery-easyui-1.4.2/js_css/de.css" type=text/css rel=stylesheet>
<LINK href="jquery-easyui-1.4.2/js_css/xtree.css" type=text/css rel=stylesheet>
<LINK href="jquery-easyui-1.4.2/js_css/User_Login.css" type=text/css rel=stylesheet>

<script type="text/javascript">

	//如果窗口被嵌套，则刷新上一级窗口
	if(window.parent!=window){
		window.parent.location.reload();
	}
	
	function ResetValue() {
		document.getElementById("userName").value="";
		document.getElementById("password").value="";
	}

</script>
</head>
<BODY id=userlogin_body>
<DIV style="height:100px"></DIV>

<DIV id=user_login>

<form action="login" method="post">
<DL>
  <DD id=user_top>
  <UL>
    <LI class=user_top_l></LI>
    <LI class=user_top_c></LI>
    <LI class=user_top_r></LI>
  </UL>
  <DD id=user_main>
  <UL>
    <LI class=user_main_l></LI>
    <LI class=user_main_c>
    <DIV class=user_main_box>
    <UL>
      <LI class=user_main_text>用户名： </LI>
      <LI class=user_main_input><INPUT type="text" class=TxtUserNameCssClass id=userName name=userName> </LI>
    </UL>
    <UL>
      <LI class=user_main_text>密&nbsp;&nbsp;&nbsp;&nbsp;码： </LI>
      <LI class=user_main_input><INPUT type="password" class=TxtPasswordCssClass id=password name=password> </LI>
    </UL>
    <UL>
      <LI class=user_main_text>权&nbsp;&nbsp;&nbsp;&nbsp; 限： </LI>
      <LI class=user_main_input style="margin-top: 7px">
      	<select name="level" id="level">
			<option value="管理员">管理员</option>
			<option value="职员">职员</option>
		</select>
      </LI>
      <li><input type="button" onclick="ResetValue()" value="重置" style="margin-top: 7px;margin-left: 50px"></li>
      </UL>
      <UL>
	     <LI><font color="red" size="3">${error}</font></LI>
      </UL>
      </DIV>
      </LI>
    <LI class=user_main_r>
    <INPUT  type="submit" style="background-image: url('./images/log.png');width: 80px;height: 80px;" value="">
    </LI>
  </UL>
  <DD id=user_bottom>
  <UL>
    <LI class=user_bottom_l></LI>
    <LI class=user_bottom_c>
    ©Research Institute of Computer Systems of SCUT
    	<SPAN style="MARGIN-TOP: 40px">©Research Institute of Computer Systems of SCUT</SPAN>
    </LI>
    <LI class=user_bottom_r></LI>
   </UL>
   <ul>
   	<li style="margin-top: 20px;padding-left: 60px"><font size="2" color="white">推荐使用IE9及以上版本的浏览器，其他浏览器可能由于分辨率带来布局混乱问题</font></li>
   </ul>
   </DD>
</DL>
</form>
</DIV>
</BODY>
</html>