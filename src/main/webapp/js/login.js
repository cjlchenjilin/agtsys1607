/**
 * 
 */
// 文档加载完毕之后执行
$(function() {
	$('#login-div').dialog({
		title : '系统登录',
		iconCls:'icon-ok',
		width : 350,
		height : 260,
		closed : false,
		cache : false,
		modal : true,
		buttons:[{
			text:'登录',
			handler:function(){
				alert('save');
			}
		},{
			text:'重置',
			handler:function(){
				alert('cancel')
			}
		}]
	});
});