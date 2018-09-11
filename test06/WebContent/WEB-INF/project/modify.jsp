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
				left: 300px;
			}
			
		</style>
</head>
<body>
	<form action="pro1" method="post"/>
	<input type="hidden" name="type" value="modify" />
		<input type="hidden" name="id" value="${pro.id}" />
	
			<div>
				部门名称
				<input type="text" name="name" value="${pro.name}"/>
			</div>
			
			
			<input type="submit" value="保存"/>
			
		</form>

</body>
</html>