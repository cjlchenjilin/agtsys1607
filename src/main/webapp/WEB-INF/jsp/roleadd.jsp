<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="add_role" method="post" action="role/add" class="p30">
	<div id="role_tip"></div>
	<div class="h30 lh28">
		<label for="rolename">角色名称:</label> <input id="rolename" onblur="check_rolename();"
			class="easyui-validatebox" type="text" name="rolename"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="isstart">是否启用:</label> 
		<select name="isstart">
			<option value="1" selected="selected">启用</option>
			<option value="0">不启用</option>
		</select>
	</div>
</form>