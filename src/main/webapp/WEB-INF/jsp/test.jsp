<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>test</title>
<style>
p {
	margin: 0;
	color: blue;
}

div, p {
	margin-left: 10px;
}

span {
	color: red;
}
</style>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<p>Type 'correct' to validate.</p>
	<form action="javascript:alert( 'exception!' );">
		<div>
			<input type="text"> <input type="submit">
		</div>
	</form>
	<span></span>

	<script>
		$("form").submit(function(event) {
			if ($("input:first").val() === "correct") {
				$("span").text("Validated...").show();
				return;
			}

			$("span").text("Not valid!").show().fadeOut(1000);
			console.log("Sample of data:",exception);
			$.ajax({
				method : "POST",
				url : "some.php",
				data : {
					name : "John",
					location : "Boston"
				}
			}).done(function(msg) {
				alert("Data Saved: " + msg);
				if (console && console.log) {
					console.log("Sample of data:");
				}
			});

			event.preventDefault();
		});
	</script>
</body>
</html>