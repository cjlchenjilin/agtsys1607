<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="systemconfig/add" method="post" class="formTable"
 id="add_systemconfig_${configtype}">
	<div id="tip"></div>
	<input name="configtype" type="hidden" value="${configtype}"/>
	<table>	
		<tr>
			<th width="100" style="text-align: right">配置类型：</th>
			<td><input id="configtypename" type="text" name="configtypename" 
			onblur="if(this.value=='') return;check_configname(${configtype},this.value)"
				class="easyui-validatebox" data-options="required:true" /></td>
		</tr>
		<c:if test="${configtype==2 or configtype==7}">
			<tr>
				<th width="100" style="text-align: right">配置数值：</th>
				<td><input id="configvalue" type="text" name="configvalue"
					class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
		</c:if>
		<tr>
			<th style="text-align: right">是否启用：</th>
			<td><select id="isstart" name="isstart"
				class="easyui-validatebox" data-options="required:true">
					<option value="1" selected="selected">启用</option>
					<option value="0">不启用</option>
			</select></td>
		</tr>
	</table>
</form>