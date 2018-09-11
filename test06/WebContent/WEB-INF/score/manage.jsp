<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
<style>
#emp {
	border: 1px solid black;
	width: 800px;
	height: 120px;
	border-collapse: collapse;
	text-align: center;
	vertical-align: middle;
}

table tr:nth-child(odd) {
	background: palegreen;
}

#emp .select {
	background: #337ab7;
}

#emp td {
	width: 150px;
	height:50px;
}

#emp input {
	width: 80px;
}

#main {
	position: absolute;
	left: 30px;
	top: 100px;
}


#title th{
text-align: center;
}
#topform{
position: absolute;
left: 10px;
top: 20px;
}
#save{
position:absolute;
left:330px;
top:300px;
}
.pagination{
position: absolute;
left:200px;
}

</style>
<script>
$(document).ready(function(){
	
	$("#main input").click(function(){
		var input = $(this);
		var str = $(this).val();
		if(str==""){
			
			$(this).blur(function(){
				var value = $(this).val();
				var empId=$(this).data("empid");
				var proId=$(this).data("proid");
				$.ajax({
					url:"score1",
					type:"post",
					data:{
						type:"add",
						empId:empId,
						proId:proId,
						value:value
					},
					dataType:"text",
					success:function(data){
						input.parent().next().html(data);
						
					}
				})
			
		})
		}else{
			$(this).blur(function(){
				var value = $(this).val();
				var id=$(this).data("id");
				$.ajax({
					url:"score1",
					type:"post",
					data:{
						type:"update",
						id:id,
						value:value
					},
					dataType:"text",
					success:function(data){
						input.parent().next().html(data);
						
					}
				})
			
		})
			
		}
 	
		
		
 	})


	
})
	
</script>
</head>
<body>

<c:if test="${p.ye<=1}">
			<script>
				$(document).ready(function() {
					$("#former").hide();
				})
			</script>

		</c:if>
		<c:if test="${p.ye==p.maxYe}">
			<script>
				$(document).ready(function() {
					$("#next").hide();
				})
			</script>


		</c:if>

<div id="topform">
	<form action="score1" class="form-horizontal" method="post">
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" placeholder="姓名"
						name="name" value=${c.emp.name}>
				</div>
				<div class="col-sm-2">
					<select class="form-control" name="depId">
						<option value="">请选择部门</option>
					<c:forEach items="${depList}" var="dep">
					<option value="${dep.id}" <c:if test="${c.emp.dep.id==dep.id}">selected</c:if>>${dep.name}</option>
					</c:forEach>
						
					</select>
				</div>
				<div class="col-sm-2">
				<select name="proId" id="proId" class="form-control">
					<option value="">请选择项目</option>
					<c:forEach items="${proList}" var="pro">
					<option value="${pro.id}" <c:if test="${c.pro.id==pro.id}">selected</c:if>>${pro.name}</option>
					</c:forEach>
				</select> 
				</div>
				
				<div class="col-sm-1">
					<input type="text" class="form-control" placeholder="成绩"
						name="value" value=${c.value==-1?"":c.value}>
				</div>
				
				<div class="col-sm-1">
					<input type="text" class="form-control" placeholder="等級"
						name="last" value=${c.last}>
				</div>
				
				<div class="col-sm-1">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
			</div>
		</form>
		</div>


	<div id="main">

		<table id="emp" border=1>
			<thead>
				<tr id="title">
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>成绩</th>
					<th>等级</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="score">
					<tr data-id="${score.id}">
						<td>${score.emp.name}</td>
						<td>${score.emp.dep.name}</td>
						<td>${score.pro.name}</td>
						<td><input type="text" data-id="${score.id}" data-empid="${score.emp.id}" data-proid="${score.pro.id}" value="${score.value}" /></td>
						<td>${score.last}</td>
					</tr>

				</c:forEach>
			</tbody>



		</table>
		
		 <ul class="pagination">
		<li><a href="score1?type=manage&ye=1&name=${c.emp.name}&depId=${c.emp.dep.id!=-1?c.emp.dep.id:""}&proId=${c.pro.id!=-1?c.pro.id:""}&value=${c.value!=-1?c.value:""}&last=${c.last}">首页</a></li>
			<li id="former"><a href="score1?type=manage&ye=${p.ye-1}&name=${c.emp.name}&depId=${c.emp.dep.id!=-1?c.emp.dep.id:""}&proId=${c.pro.id!=-1?c.pro.id:""}&value=${c.value!=-1?c.value:""}&last=${c.last}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li id="page1"
					<c:if test="${p.ye==status.index}"> class="active" </c:if>><a
					href="score1?type=manage&ye=${status.index}&name=${c.emp.name}&depId=${c.emp.dep.id!=-1?c.emp.dep.id:""}&proId=${c.pro.id!=-1?c.pro.id:""}&value=${c.value!=-1?c.value:""}&last=${c.last}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a href="score1?type=manage&ye=${p.ye+1}&name=${c.emp.name}&depId=${c.emp.dep.id!=-1?c.emp.dep.id:""}&proId=${c.pro.id!=-1?c.pro.id:""}&value=${c.value!=-1?c.value:""}&last=${c.last}">下一页</a></li>
					<li ><a href="score1?type=manage&ye=${p.maxYe}&name=${c.emp.name}&depId=${c.emp.dep.id!=-1?c.emp.dep.id:""}&proId=${c.pro.id!=-1?c.pro.id:""}&value=${c.value!=-1?c.value:""}&last=${c.last}">尾页</a></li>
			
		</ul> 



		<div>
			<button type="button" class="btn btn-primary" id="save">保存</button>
		</div>

	</div>

</body>
</html>