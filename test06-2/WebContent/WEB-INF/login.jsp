<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style>
* {
	padding: 0;
	margin: 0;
}

#dakj {
	background: #337ab7;
	width: 1550px;
	height: 850px;
	left: 0px;
	top: 0px;
}

.container {
	position: absolute;
	top: 50px;
	left: 570px;
	width: 400px;
	height: 150px;
	text-align: center;
}

#randomSrc {
	position: absolute;
	top: 0px;
	left: 210px;
}
</style>
<script>

if(self!=top){
	top.location="user";
}
	$(document).ready(function() {

		$("#go").click(function() {
			var usename = $("#usename").val();
			var code = $("#code").val();
			var random = $("#random").val();
			$.ajax({
				url : "user",
				type : "post",
				data : {
					type : "doLogin",
					usename : usename,
					code : code,
					random : random,
				},
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						location.href = "main";
					} else {
						alert("用户、密码或者验证码不正确");
					}
				}

			})
		})
		$("#randomSrc").click(function() {
			$(this).attr("src", "user?type=randomImage&" + Math.random());
		})

	})
</script>
</head>
<body>

	<div id="dakj">


		<div class="container">

			<h3 class="form-signin-heading">系统用户登录</h3>
			<div class="col-sm-10">
				<input type="text" class="form-control" placeholder="请输入用户名"
					id="usename" value="${name}">
			</div>
			<div style="height: 10px; clear: both; display: block"></div>
			<div class="col-sm-10">
				<input type="password" class="form-control" placeholder="请输入密码"
					id="code">
			</div>
			<div style="height: 10px; clear: both; display: block"></div>

			<div class="col-sm-7" id="yan">
				<input type="text" class="form-control" placeholder="请输入验证码"
					id="random"> <img src="user?type=randomImage"
					id="randomSrc">
			</div>
			<div style="height: 10px; clear: both; display: block"></div>
			<div class="col-sm-10">
				<button class="btn btn-lg btn-primary btn-block" type="button"
					id="go">登录</button>
			</div>
		</div>


	</div>


</body>
</html>