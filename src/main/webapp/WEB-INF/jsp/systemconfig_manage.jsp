<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table id="systemconfig_dg_${configtype}"></table>
<script type="text/javascript">
$('#systemconfig_dg_${configtype}').datagrid({
	url : 'systemconfig/list/${configtype}',
	columns : [ [
	   {
		field : 'configtypename',
		title : '配置类型',
		width : 100,
		align : 'center'
	},
	<c:if test="${configtype !=1 and  configtype!=5 and configtype!=6 }">
		{
			field : 'configvalue',
			title : '配置数值',
			width : 200,
			align : 'center',
		},
	</c:if> 
	{
		field : 'isstart',
		title : '是否启用',
		width : 100,
		align : 'center',
		formatter : function(value,row,index) {
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
			}else{
				return 'color:green;';
			}
		}
	},
	 {
		field : 'id',
		title : '操作',
		width : 100,
		align : 'center',
		formatter : function(value,row,index) {
			return '<a class="easyui-linkbutton"  href="">修改</a><c:if test="${configtype ==1 or configtype ==5 or configtype==6 or configtype==7 }">|<a class="easyui-linkbutton" href="javascript:delete_systemconfig('+value+',\''+row.configtypename+'\')">删除</a></c:if>';
		}
	}] ],
	//加载数据失败事件
	onLoadError:function(){
		$.messager.alert('提示',"加载数据失败！",'error');
	},
	onLoadSuccess:function(data){
		alert(data);
	},
	//表格属性
	singleSelect : true,
	frozenColumns : true,
	fitColumns : false,
	rownumbers:true,

	//工具条
	<c:if test="${configtype !=3 and configtype != 4}">
		toolbar : [ {
			iconCls : 'icon-add',
			text : '添加',
			handler : function() {
				add_systemconfig_${configtype}();
			}
		}]
	</c:if>
});
//
//添加配置
function add_systemconfig_${configtype}(){
	$('#formbox').dialog({
		title : '添加配置${configtype}',
		width : 300,
		height : 200,
		closed : false,
		cache : false,
		href : 'systemconfig/add/${configtype}',
		modal : true,
		buttons:[{
			text:'添加',
			handler:function(){
				//约束验证
				if($('#add_systemconfig_${configtype}').form('validate')){
					//检查配置类型名称是否存在
					if(check_configname(${configtype},$('#configtypename').val())){
						$('#add_systemconfig_${configtype}').form('submit',{
							success: function(msg){
							     if(msg=="success"){
							    	 //关闭对话框
							    	 $('#formbox').dialog('close');
							    	 $.messager.alert('提示','添加【'+$('#configtypename').val()+'】配置成功!','info');
							    	 //刷新列表
							    	 $('#systemconfig_dg_${configtype}').datagrid('reload');
							     }else{
							    	 $.messager.alert('提示','添加配置失败!','error');
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
//检查类型名称
function check_configname(configtype,configtypename){
	var flag = false;
	$.ajax({
		url:'systemconfig/check',
		async:false,
		type:'post',
		data:'configtype='+configtype+'&configtypename='+configtypename,
		success:function(msg){
			if(msg=="success"){
		    	$('#tip').html('<font color="green">该类型名称可以使用</font>');
		    	flag = true;
		     }else{
		    	 $('#tip').html('<font color="red">该类型名称不可以使用</font>');
		     }
		}
	}); 
	return flag;
}
//删除配置
function delete_systemconfig(id,configtypename){
	$.messager.confirm('删除提示','您确定删除【'+configtypename+'】?',
			function(r){ 
				if (r){ 
					$.ajax({
						url:'systemconfig/delete/'+id,
						async:false,
						type:'post',
						success:function(msg){
							if(msg=="success"){
						    	 $.messager.alert('提示','删除【'+configtypename+'】配置成功!','info');
						    	 //刷新列表
						    	 $('#systemconfig_dg_${configtype}').datagrid('reload');
						     }else if(msg=="fail"){
						    	 $.messager.alert('提示','删除【'+configtypename+'】配置失败!','error');
						     }else{
						    	 $.messager.alert('提示',msg,'error');
						     }
						}
					});   
				}
			}
	);
}
</script>