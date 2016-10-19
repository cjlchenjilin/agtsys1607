$(function() {
	$('#mm').menu({
		onClick : function(item) {
			if (item.text == "退出系统") {
				window.location.href = "user/logout";
			} else if (item.text == "修改密码") {
				//新建修改密码对话框
				$('#formbox').dialog({
					title : '修改密码',
					width : 300,
					height : 280,
					closed : false,
					cache : false,
					href : 'user/pwd/update',
					modal : true,
					buttons:[{
						text:'确认修改',
						handler:function(){
							//表单验证
							if($('#update_pwd').form('validate')){
								//验证旧密码
								if(validate_oldpwd($('#oldpwd').val())){
									//提交数据到远程服务器进行修改
									$.ajax({
										   type: "POST",
										   url: "user/pwd/update",
										   data: "userpassword="+$('#newpassword').val(),
										   success: function(msg){
										     if(msg=="success"){
										    	 $.messager.alert('修改提示','修改密码成功!','info'); 
										     }else{
										    	 $.messager.alert('修改提示','修改密码失败!','error');
										     }
										   }
									});
								}
							}
						}
					},{
						text:'取    消',
						handler:function(){
							$('#formbox').dialog('close');
						}
					}]
				});
			}
		}
	});
	
	//树形菜单
	$('#tree').tree({
		url:'main/tree',
		onLoadSuccess:function(node,data){
			//收起所有菜单
			$('#tree').tree('collapseAll');
			//展开代理商菜单
			$.each(data,function(index,node){
				if(node.text=='代理商管理'){
					$('#tree').tree('expand',node.target);
				}
			});
		},
		onClick: function(node){
			if(node.attributes.url != ""){
				//如果存在就选择
				if($('#tabs').tabs('exists',node.text)){
					$('#tabs').tabs('select',node.text);
				}else{
					//不存在就创建
					create_tab(node);
				}	
			}				
		}
	});		
})
//创建一个选项卡
	function create_tab(node){
		$('#tabs').tabs('add', {
			title : node.text,
			href:node.attributes.url,
			closable : true,
			tools : [ {
				iconCls : 'icon-mini-refresh',
				handler : function() {
					var tab = $('#tabs').tabs('getSelected');  // get selected panel
					tab.panel('refresh');
				}
			}]
		});	
	}