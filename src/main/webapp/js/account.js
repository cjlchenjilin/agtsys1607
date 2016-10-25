/**
 * 财务管理
 */
//输入自动搜索用户功能
$("#searchUserText").keyup(function(){
		$("#searchUserId").val("");
		$("#serachresult").html("");
		//查询用户
		loadUsers();
});
	//单击搜索用户功能
	$("#searchUserText").click(function(){
		$("#searchUserId").val("");
		$("#serachresult").html("");
		loadUsers();
	});
	
	$("#caiwuok").click(function(){
		if($("#searchUserId").val()==null || $.trim($("#searchUserId").val()).length == 0){
			$.messager.alert('错误','对不起，您还没有选择用户，请搜索进行用户的选择。','error');
			$("#searchUserText").focus();
		}else if($("#accounttype").val() == null || $.trim($("#accounttype").val()).length == 0){
			$.messager.alert('错误','对不起，您还没有选择操作类型，请选择操作类型。','error');
			$("#accounttype").focus();
		}else if($("#money").val() == null || $.trim($("#money").val()).length == 0){
			$.messager.alert('错误','对不起，您还没有输入需要操作的金额，请输入。','error');
			$("#money").focus();
		}else{
			//判断是否是数字
			if(isNaN($("#money").val())){
				$.messager.alert('错误','对不起，您输入的不是数字。','error');
				$("#money").focus();
				return;
			} 
			$.messager.confirm('确认', '您确定要执行当前财务操作吗？', function(r){
				if (r){
					$.post('account/operation',
						{
						'userid':$("#searchUserId").val(),
						'money':$("#money").val(),
						'detailtype':$("#accounttype").val(),
						'detailtypename':$("#accounttype option:selected").text(),
						'memo':$("#memo").val()
						},function(result){
						if(result == 'success'){
							$.messager.alert('提示','恭喜您，财务操作成功。','info');
							$("#systemtip").html("恭喜您，财务操作成功。");
						}else{
							$.messager.alert('提示','对不起，当前财务操作失败。','error');
							$("#systemtip").html("对不起，当前财务操作失败。");
						}
					},'text');
				}
			});
		}	
	});
	
	function confirmUser(uid,ucode){
		$("#searchUserId").val(uid);
		$("#searchUserText").val(ucode);
		$("#serachresult").html("");
	}
	
	//加载用户
	function loadUsers(){
		$.get("account/usercode/load",{'usercode':$("#searchUserText").val()},
			function(result){
				var $ul = $("<ul></ul>");
				for(var i=0;i< result.length;i++){
					//jquery对象
					var $li = $("<li>"+result[i].usercode+"</li>");
					$li.attr("onclick","confirmUser("+result[i].id+",'"+result[i].usercode+"')");
					$ul.append($li);
				}
				$("#serachresult").append($ul);		
		},'json');
	}