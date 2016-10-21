<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-panel" style="height:auto;">
	<form id="search_form" action="" method="post">
		<table class="searchTb">
			<tr>
				<th>用户名称：</th>
				<td><input type="text" id="username" name="username" style="height:16px"/></td>
				<th>角色：</th>
				<td><select id="roleid" name="roleid">
						<option selected="selected" value="">--请选择--</option>
						<c:forEach items="${roles}" var="role">
							<option value="${role.id}">${role.rolename}</option>
						</c:forEach>
				</select></td>
				<th>是否启用：</th>
				<td><select name="isstart" id="isstart">
						<option value="" selected="selected">--请选择--</option>
						<option value="1">启用</option>
						<option value="0">未启用</option>
				</select></td>
				<td>&nbsp;<a href="javascript:search_user();"
					class="easyui-linkbutton m10" title="查询"
					data-options="iconCls:'icon-search'">查询</a>
				</td>
			</tr>
		</table>
	</form>
</div>
<table id="user_dg"></table>
<script type="text/javascript">
	$(function() {
		$("#user_dg")
				.datagrid(
						{
							url : "user/list",
							method:'get',
							columns : [ [
									{
										field : 'usercode',
										title : '登录账号'
									},
									{
										field : 'username',
										title : '用户名称'
									},
									{
										field : 'rolename',
										title : '角色'
									},
									{
										field : 'creationtime',
										title : '创建时间',
										formatter : function(value) {
											return formatDate(value);
										}
									},
									{
										field : 'isstart',
										title : '是否启用',
										align : 'center',
										formatter : function(value, row, index) {
											//formatter  表格格式化函数，返回3个参数：字段值，该行数据，该行索引
											switch (value) {
											case 0:
												return "未启用";
												break;
											case 1:
												return "启用";
												break;
											default:
												return "-";
												break;
											}
										},
										styler : function(value, row, index) {
											if (value == 0) {
												return 'background-color:#ffee00;color:red;';
											}
										}
									},
									{
										field : 'id',
										title : '操作',
										width : 100,
										align : 'center',
										formatter : function(value, row, index) {
											return '<a class="easyui-linkbutton"  href="">修改</a>'
											+'|<a class="easyui-linkbutton" href="javascript:delete_user('
													+ value
													+ ',\''
													+ row.username
													+ '\')">删除</a>';
										}
									} ] ],
							toolbar : [ {
								text : '新增',
								iconCls : 'icon-add',
								handler : function(){}
							} ],
							fitColumns : true,//自适应宽度 
							striped : true,//列表是否有间隔底色
							resizable : true,//列尺寸可调节
							singleSelect : true,
							loadMsg : '正在努力加载中..',
							rownumbers : true, //行前显示行号
							idField : 'id', //指定选中时返回的维度字段名，如id

							pagination : true,//是否显示分页
							pageSize : 5,//每页条数 
							pageList : [ 5, 10, 15 ]
						});
	});
	//格式化日期
	function formatDate(value) {
		if (value == null)
			return;
		var date = new Date(value);
		var year = date.getFullYear().toString();
		var month = (date.getMonth() + 1);
		var day = date.getDate().toString();
		var hour = date.getHours().toString();
		var minutes = date.getMinutes().toString();
		var seconds = date.getSeconds().toString();
		if (month < 10) {
			month = "0" + month;
		}
		if (day < 10) {
			day = "0" + day;
		}
		if (hour < 10) {
			hour = "0" + hour;
		}
		if (minutes < 10) {
			minutes = "0" + minutes;
		}
		if (seconds < 10) {
			seconds = "0" + seconds;
		}
		return year + "-" + month + "-" + day + " " + hour + ":" + minutes
				+ ":" + seconds;
	}
//搜索用户
function search_user(){
	var user = {"username":null,"roleid":null,"isstart":null};
	if($.trim($('#username').val())!=''){
		user.username = $.trim($('#username').val());
	}
	if($.trim($('#roleid').val())!=''){
		user.roleid = $.trim($('#roleid').val());
	}
	if($.trim($('#isstart').val())!=''){
		user.isstart = $.trim($('#isstart').val());
	}
	$("#user_dg")
	.datagrid('reload',user);
}
</script>