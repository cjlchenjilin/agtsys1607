/**
 * 
 */
// 文档加载完毕之后执行
$(function() {
	$('#login-div').dialog({
		title : '系统登录',
		iconCls : 'icon-ok',
		width : 350,
		height : 260,
		closed : false,
		cache : false,
		modal : true,
		buttons : [ {
			text : '登录',
			handler : function() {
				if ($('#login-form').form('validate')) {
					$('#login-form').submit();
				}
			}
		}, {
			text : '重置',
			handler : function() {
				$('#login-form').form('reset');
			}
		} ]
	});
	// 验证码输入提示
	$("#captchca").keyup(function() {
		// 获取文本框的值
		var captchca = $("#captchca").val();
		if (captchca.length >= 4) {
			// 访问服务器验证输入,ajax
			$.ajax({
				type : "POST",
				url : "captchca/check",
				data : "captchca="+captchca,
				dataType:"text",
				success : function(msg) {
					if(msg == "success"){
						$("#tip").html("<font color='green'>验证码正确</font>");
					}else{
						$("#tip").html("<font color='red'>验证码不正确</font>");
					}
				}
			});
		}
	});
});