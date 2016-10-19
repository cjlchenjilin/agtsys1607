<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="update_role" method="post" action="role/update" class="p30">
	<div id="role_tip"></div>
	<!-- 保存角色id，隐藏 -->
	<input id="id" name="id" type="hidden" value="${role.id}" />
	<div class="h30 lh28">
		<label for="rolename">角色名称:</label> <input id="rolename" 
		onblur="if(this.value==old_rolename) return;check_rolename();"
			class="easyui-validatebox" type="text" name="rolename" value="${role.rolename}"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="isstart">是否启用:</label> 
		<select name="isstart">
			<c:if test="${ role.isstart==1}">
				<option value="1" selected="selected">启用</option>
				<option value="0">不启用</option>
			</c:if>	
			<c:if test="${role.isstart == 0 }">
				<option value="1" >启用</option>
				<option value="0" selected="selected">不启用</option>
			</c:if>		
		</select>
	</div>
</form>
<script type="text/javascript">
//定义全局角色名称保存原来的角色名称,使用el表达式，注意加引号‘’
var old_rolename = '${role.rolename}';
</script>