/**
 * 角色配置管理
 */
// 全局变量
var check_roleid = -1;
var check_rolename ="";
// 获取指定角色的功能权限
function getFunctionsByRoleId(roleid,rolename) {
	// 给全局变量赋值
	check_roleid = roleid;
	check_rolename = rolename;
	// 清空数据
	$('#permission_dg').datagrid({
		data : null
	});
	// 清除选中状态
	$('#permission_dg').datagrid('clearChecked');
	// 重新加载数据
	$("#permission_dg").datagrid({
		url : "permission/list/" + roleid,
		columns : [ [ {
			field : 'checked',
			checkbox : true,
		}, {
			field : 'id',
			title : 'Id',
			hidden : true
		}, {
			field : 'functionname',
			title : '功能名称'
		}, {
			field : 'funcurl',
			title : '功能url'
		}, {
			field : 'creationtime',
			title : '创建时间',
			formatter : function(value) {
				if (value == null)
					return;
				var date = new Date(value);
				var year = date.getFullYear().toString();
				var month = (date.getMonth() + 1);
				var day = date.getDate().toString();
				return year + "-" + month + "-" + day;
			}
		}, {
			field : 'isstart',
			title : '是否启用',
			align : 'center',
			formatter : function(value, row, index) {
				switch (value) {
				case 0:
					return "停用";
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
					return 'color:red;';
				} else {
					return 'color:green;';
				}
			}
		} ] ],
		toolbar : [ {
			text : '保存',
			iconCls : 'icon-save',
			handler : function() {
				savePermission();
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {
				closePage();
			}
		} ],
		fitColumns : true,// 自适应宽度
		striped : true,// 列表是否有间隔底色
		resizable : true,// 列尺寸可调节
		loadMsg : '正在努力加载中..',
		rownumbers : true, // 行前显示行号
		idField : 'id', // 指定选中时返回的维度字段名，如id

		singleSelect : false,

		selectOnCheck : true,

		checkOnSelect : true,

	/*
	 * onLoadSuccess : function(data) { if (data) { $.each(data.rows,
	 * function(index, item) { if (item.isCheck) {
	 * $('#permission_dg').datagrid('checkRow', index); } }); } }
	 */
	});
}

function savePermission() {
	$.messager.confirm('确认', '你确定要修改【'+check_rolename+'】的功能权限吗?', function(r){
		if (r){
			//获取当前选中的functionid
			var flist = '';
			$.each($('#permission_dg').datagrid('getChecked'),function(index, item) {
				flist += item.id+',';
			});
			
			//请求服务器更新权限配置
			$.post("permission/update",{'flist':flist,'roleid':check_roleid},function(result){
				if("success" == result){
					$.messager.alert('提示','保存成功！','info');
				}else{
					$.messager.alert('提示','保存失败！','error');
				}
			},'html');	
		}
	});
}

function closePage() {
	/*
	 * 关闭父页面的选项卡 var curTab = parent.$('#mainTabs').tabs('getSelected'); var
	 * currentTabIndex = parent.$('#mainTabs').tabs('getTabIndex',curTab);
	 * parent.$('#mainTabs').tabs('close',currentTabIndex);
	 */
	var curTab = $('#tabs').tabs('getSelected');
	var currentTabIndex = $('#tabs').tabs('getTabIndex', curTab);
	$('#tabs').tabs('close', currentTabIndex);
}