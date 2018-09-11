<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="entity.*,java.util.List" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery.js" ></script>
<script>
 $(document).ready(function(){
	 $("#save").click(function(){
		 var emps ="";
		 $(".emp").each(function(index,element){
			 var id = $(this).find("[name=id]").val();
			 var name = $(this).find("[name=name]").val();
			 var sex = $(this).find("[name=sex]").val();
			 var age = $(this).find("[name=age]").val();
			 emps+=id+","+name+","+sex+","+age+";";
			 
		 })
		 emps=emps.substring(0,emps.length-1);
		 location.href="emp1?type=modifyBatch2&emps="+emps;
	 })
 })

</script>		
		<style>
		
			
			form{
				width: 300px;
				height: 100px;
				top: 20px;
				left: 500px;
			}
			
		</style>
</head>
<body>


 <c:forEach items="${list}" var="emp">
<div>
	<form action="emp1" method="post" class="emp"/>
	
	<input type="hidden" name="type" value="modifyBatch2" />
		<input type="hidden" name="id" value="${emp.id}" />
	
			<div>
				姓名
				<input type="text" name="name" value="${emp.name}"/>
			</div>
			<div>
				年龄
				<input type="text" name="age" value="${emp.age}"/>
			</div>
			<div>
				性别
				<select name="sex">
					<option>男</option>
					<option>女</option>
				</select>
			</div>

		</form>
		</div>
		</c:forEach>
		
			<button type="button" class="btn btn-primary" id="save">保存</button>
		

</body>
</html>