<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style>
*{
margin: 0;
padding: 0;
}
#main {
	overflow: hidden;
}

#left {
	width: 260px;
	height: 600px;
	float: left;
	padding-left: 20px;
}

#right {
	width: 1000px;
	height: 600px;
	float: left;
}

#top, #bottom {
	clear: both;
	height: 80px;
	background: #337ab7;
	height: 80px;
}

.yi {
	width: 170px;
	height: 40px;
	background: #337ab7;
	color: #fff;
	margin-top: 10px;
	text-align: center;
	line-height: 40px;
	border-radius: 5px;
}

a {
	color: white;
}
.er{
list-style: none;
width:170px;
}
.er a{
color:#000;
}
.er li{
background: #ccc;
margin-top: 5px;
text-align: center;

}
#num{
position:absolute;
font-size:20px;
right:50px;
color: #fff;

}
#numNow{
position:absolute;
font-size:18px;
right:50px;
color: #fff;
top:25px;

}
</style>
<script>
$().ready(function(){
	$(".yi").click(function(){
		
		$(this).next().slideToggle(500);
	})
	
})



</script>
</head>
<body>
	<div id="container">
		<div id="top">
		<div id="num">本网站共<%=application.getAttribute("num")%>个人访问</div>
		<div id="numNow">有<%=application.getAttribute("num")%>个人正在访问</div>
		</div>
		<div id="main">
			<div id="left">
				<div class="yi">
					员工管理
				</div>
				<ul class="er">
					<li><a href="emp1" target="right">员工管理</a></li>
					<li><a href="emp1?type=showAdd" target="right">添加员工</a></li> 
				</ul>
				<div class="yi">
					部门管理
				</div>
				<ul class="er">
					<li><a href="dep1" target="right">部门管理</a></li>
					<li><a href="dep1?type=showAddDep" target="right">添加部门</a></li>
				</ul>
				<div class="yi">
					项目管理
				</div>
				<ul class="er">
					<li><a href="pro1" target="right">项目管理</a></li>
					<li><a href="pro1?type=showAdd" target="right">添加项目</a></li>
				</ul>
				<div class="yi">绩效管理</div>
				<ul class="er">
					<li><a href="score1" target="right">绩效查看</a></li>
					<li><a href="score1?type=manage" target="right">绩效管理</a></li>
				</ul>
			</div>
			<iframe src="emp1" id="right" name="right" scrolling="no"
				frameborder="0"></iframe>
		</div>
		<div id="bottom"></div>
	</div>
</body>
</html>