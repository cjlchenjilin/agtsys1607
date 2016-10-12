<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<base href="<%=request.getContextPath()%>/">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="css/reset.css">
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div id="login-div">
		<form class="p30" id="login-form" method="post">
			<div class="h30 lh28">
				<label for="usercode">用户名:</label> <input class="easyui-validatebox"
					type="text" name="usercode" data-options="required:true" />
			</div>
			<div class="h30 lh28">
				<label for="userpassword">密&nbsp;码:</label> <input class="easyui-validatebox"
					type="text" name="userpassword" data-options="required:true" />
			</div>
			<div class="h30 lh28">
				<label for="captchca">验证码:</label> <input class="easyui-validatebox"
					type="text" name="captchca" data-options="required:true" />
				<img alt="点我，换一张" src="captchca/get" title="点我，换一张" onclick="this.src='captchca/get?'+Math.random()" />
			</div>
		</form>
	</div>
</body>
</html>