<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>登录</title>
<base href="<%=request.getContextPath()%>/">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<script type="text/javascript" src="js/jquery-easyui-1.5/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/login.js"></script>
</head>
<body>
	<div id="login-div">
		<div id="tip">
			<!-- 服务器验证用户非空约束 -->
			<c:if test="${user_validate_errors != null }">
				<c:forEach items="${user_validate_errors}" var="error">
					<p style="color: red;">${error.defaultMessage }</p>
				</c:forEach>
			</c:if>
			<!-- 验证码的错误提示 -->
			<c:if test="${captchca_error != null }">
				<p style="color: red;">${captchca_error}</p>
			</c:if>
			<!-- 登录的错误提示 -->
			<c:if test="${user_login_errors != null }">
				<p style="color: red;">${user_login_errors}</p>
			</c:if>
		</div>
		<form id="login-form" action="user/login" class="p30" method="post">
			<div class="h30 lh28">
				<label for="usercode">用户名:</label> <input class="easyui-validatebox" missingMessage="用户名不能为空"
					type="text" name="usercode" data-options="required:true" />
			</div>
			<div class="h30 lh28">
				<label for="userpassword">密&nbsp;码:</label> <input
					class="easyui-validatebox" type="text" name="userpassword" missingMessage="用户密码不能为空"
					data-options="required:true" />
			</div>
			<div class="h30 lh28">
				<label for="captchca">验证码:</label> <input id="captchca" class="easyui-validatebox"
					type="text" name="captchca" data-options="required:true" missingMessage="验证码不能为空" /> <img
					alt="点我，换一张" src="captchca/get" title="点我，换一张"
					onclick="this.src='captchca/get?'+Math.random()" />
			</div>
		</form>
	</div>
</body>
</html>