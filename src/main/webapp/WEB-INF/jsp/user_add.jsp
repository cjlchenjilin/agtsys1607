<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="add_user" method="post" action="user/add" class="p30">
	<div id="user_add_tip"></div>
	<div class="h30 lh28">
		<label for="usercode">登录账号:</label> <input id="usercode" onblur="check_usercode();"
			class="easyui-validatebox" type="text" name="usercode"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="username">用户名称:</label> <input id="username" 
			class="easyui-validatebox" type="text" name="username"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="userpassword">登录密码:</label> <input id="userpassword" value="123456"
			class="easyui-validatebox" type="password" name="userpassword"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="roleid">角&nbsp;&nbsp;色:</label> 
		<select id="roleid"	class="easyui-validatebox" name="roleid"
			data-options="required:true" >
			<option value="">--请选择--</option>
			<c:forEach items="${roles}" var="role">
				<option value="${role.id}">${role.rolename}</option>
			</c:forEach>
		</select>
		
	</div>
	<div class="h30 lh28">
		<label for="isstart">是否启用:</label> 
		<select name="isstart">
			<option value="1" selected="selected">启用</option>
			<option value="0">不启用</option>
		</select>
	</div>
</form>