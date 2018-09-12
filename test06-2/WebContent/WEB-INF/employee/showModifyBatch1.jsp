<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="entity.*" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery.js" ></script>
		<style>
			
			form{
				position: absolute;
				width: 300px;
				height: 500px;
				top: 20px;
				left: 350px;
			}
			
		</style>
</head>
<body>

	<form action="emp1" method="post"/>
	<input type="hidden" name="type" value="modifyBatch1" />
		<input type="hidden" name="ids" value="${ids}" />
	
			<div>
				姓名
				<input type="text" name="name" value="${emp.name}"/>
			</div>
			<div>
				年龄
				<input type="text" name="age" value="${emp.age}"/>
			</div>
			<div>
				姓名
				<select name="sex">
					<option>男</option>
					<option>女</option>
				</select>
			</div>
			
			
			<input type="submit" value="保存"/>
			
		</form>

</body>
</html>