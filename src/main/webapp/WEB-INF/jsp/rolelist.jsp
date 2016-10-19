<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table id="role_dg"></table>
<script type="text/javascript">
	$('#role_dg').datagrid({
		url : 'role/list',
		columns : [ [{field:'check',checkbox:true},
		    {
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
				//获取id getSelected
				var row = $('#role_dg').datagrid('getSelected');
				if(row == null){
					$.messager.alert('提示','您没有选中一行数据!','warning');
					return;
				}
				update_role(row.id);
			}
		}, '-', {
			iconCls : 'icon-remove',
			text : '删除',
			handler : function() {
				//获取id getSelected
				var row = $('#role_dg').datagrid('getSelected');
				if(row == null){
					$.messager.alert('提示','您没有选中一行数据!','warning');
					return;
				}else{
					//确认删除对话框
					$.messager.confirm('删除确认', '您确定要删除该【'+row.rolename+'】角色吗?', function(r){
						if (r){
							//删除角色
							delete_role(row);
						}
					});
				}	
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
	
	//修改角色
	function update_role(id){
		$('#formbox').dialog({
			title : '修改角色',
			width : 300,
			height : 200,
			closed : false,
			cache : false,
			href : 'role/update/'+id,
			modal : true,
			buttons:[{
				text:'修改',
				handler:function(){
					//约束验证
					if($('#update_role').form('validate')){
						//做个标记
						var flag = false;
						if($('#rolename').val()!=old_rolename){
							//检查服务器角色名称是否存在
							flag = check_rolename();							
						}else{
							flag = true;
						}
						if(flag)
						{
								$('#update_role').form('submit',{
									success: function(msg){
									     if(msg=="success"){
									    	 $.messager.alert('提示','修改【'+$('#rolename').val()+'】角色成功!','info');
									    	 //关闭修改窗口
									    	 $('#formbox').dialog('close');
									    	 //刷新列表
									    	 $('#role_dg').datagrid('reload');
									     }else{
									    	 $.messager.alert('提示','修改角色失败!','error');
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
	//删除角色
	function delete_role(row){
		$.ajax({
			url:'role/delete/'+row.id,
			async:false,
			success:function(msg){
				if(msg=="success"){
			    	 $.messager.alert('提示','删除【'+row.rolename+'】角色成功!','info');
			    	 //刷新列表
			    	 $('#role_dg').datagrid('reload');
			     }else{
			    	 $.messager.alert('提示','删除【'+row.rolename+'】角色失败!','error');
			     }
			}
		})
	}
	
	//检查角色名称
	function check_rolename(){
		var flag = false;
		var rolename=$('#rolename').val();
		//为空直接返回不检查
		if(rolename==''){
			return;
		}
		$.ajax({
			url:'role/check',
			async:false,
			data:'rolename='+rolename,
			type:'post',
			success:function(msg){
				if(msg=="success"){
					$('#role_tip').html("<font color='green'>角色名称可以使用</font>");
					flag=true;
				}else if(msg=="fail"){
					$('#role_tip').html("<font color='red'>角色名称已经存在</font>");
				}
			}
		});
		return flag;
	}
</script>