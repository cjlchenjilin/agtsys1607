<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="role_dg"></table>
<script type="text/javascript">
	$('#role_dg').datagrid({
		url : 'role/list',
		columns : [ [ {
			field : 'id',
			title : 'id',
			width : 100,
			hidden : true
		}, {
			field : 'rolename',
			title : '角色名称',
			width : 100,
			align : 'center'
		}, {
			field : 'creationtime',
			title : '创建时间',
			width : 200,
			align : 'center',
			formatter : function(value) {
				//格式化时间
				return formatDate(value);
			}
		}, {
			field : 'isstart',
			title : '是否启用',
			width : 100,
			align : 'center',
			formatter : function(value) {
				switch (value) {
				case 0:
					return "未启用";
				case 1:
					return "启用";
				}
			},
			styler : function(value) {
				if (value == 0) {
					return 'background-color:#ffee00;color:red;';
				}
			}
		} ] ],
		//表格属性
		singleSelect : true,
		frozenColumns : true,
		fitColumns : false,

		//工具条
		toolbar : [ {
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				add_role();
			}
		}, '-', {
			iconCls : 'icon-edit',
			text : '修改',
			handler : function() {
				alert('help')
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() {
				alert('help')
			}
		} ]
	});

	//格式化时间
	function formatDate(date) {
		var d = new Date(date);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var day = d.getDate();
		var hour = d.getHours();
		var minute = d.getMinutes();
		var second = d.getSeconds();

		//格式化时间
		return year + "-" + (month < 10 ? "0" + month : month) + "-"
				+ (day < 10 ? "0" + day : day) + " "
				+ (hour < 10 ? "0" + hour : hour) + ":"
				+ (minute < 10 ? "0" + minute : minute) + ":"
				+ (second < 10 ? "0" + second : second);
	}
	//添加角色
	function add_role() {
		$('#formbox').dialog({
			title : '添加角色',
			width : 300,
			height : 200,
			closed : false,
			cache : false,
			href : 'role/add',
			modal : true,
			buttons:[{
				text:'添加',
				handler:function(){
					//约束验证
					if($('#add_role').form('validate')){
						//检查角色名称是否存在
						if(check_rolename()){
							$('#add_role').form('submit',{
								success: function(msg){
								     if(msg=="success"){
								    	 $.messager.alert('提示','添加【'+$('#rolename').val()+'】角色成功!','info');
								    	 //刷新列表
								    	 $('#role_dg').datagrid('reload');
								     }else{
								    	 $.messager.alert('提示','添加角色失败!','error');
								     }
								}
							})	
						}	
					}		
				}
			},{
				text:'取消',
				handler:function(){
					$('#formbox').dialog('close');
				}
			}]
		});
	}
	
</script>