<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>异常处理页面</title>
</head>
<body>
	<h1 style="color:red">${exception}</h1>
	<h1>
		<span id="second">5</span>秒后返回上一页！！
	</h1>
	<script type="text/javascript">
		var second = document.getElementById("second").innerHTML;
		var my_interval = setInterval(function() {	
			second--;
			if (second>0) {
				document.getElementById("second").innerHTML=second;
			} else {
				window.history.back();
				clearInterval(my_interval);			
			}
		}, 1000);
	</script>
</body>
</html>