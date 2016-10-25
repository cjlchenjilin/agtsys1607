<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="update_pwd" method="post" class="p30">
	<div id="pwd_tip"></div>
	<div class="h30 lh28">
		<label for="userpassword">旧 密 码:</label> <input id="oldpwd"
			class="easyui-validatebox" type="password" name="userpassword"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="newpassword">新 密 码:</label> <input
			class="easyui-validatebox" type="password" id="newpassword" name="newpassword"
			data-options="required:true" />
	</div>
	<div class="h30 lh28">
		<label for="twopassword">确认密码:</label> <input
			class="easyui-validatebox" type="password" name="twopassword" data-options="required:true"
			validType="pwdEquals['newpassword']" />
	</div>
</form>
<script type="text/javascript">
	//扩展validatebox的验证规则，判断2次密码是否相同
	$.extend($.fn.validatebox.defaults.rules, {
		pwdEquals : {
			validator : function(value, param) {			
				return value == $('#'+param[0]).val();
			},
			message : '2次密码不相同。'
		}
	});
	
	//验证旧密码
	function validate_oldpwd(pwd){
		var flag = false;
		$.ajax({
			   type: "POST",
			   url: "user/pwd/check", //restful
			   data: "pwd="+pwd,
			   async:false,
			   success: function(msg){
			   		if(msg=="success"){
			   			$('#pwd_tip').html("<font color='green'>旧密码输入正确</font>");
			   			flag = true;
			   		}else if(msg=="fail"){
			   			$('#pwd_tip').html("<font color='red'>旧密码输入不正确</font>");
			   		}else{
				    	 $.messager.alert('修改提示',msg,'error');
				    }
			   }
			});
		return flag;
	}
	$('#oldpwd').blur(function(){
		validate_oldpwd($(this).val());
	});
</script>