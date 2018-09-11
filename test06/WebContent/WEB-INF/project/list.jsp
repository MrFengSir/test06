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
#pro {
	border: 1px solid black;
	width: 530px;
	height: 120px;
	border-collapse: collapse;
	text-align: center;
	vertical-align: middle;
}

table tr:nth-child(odd) {
	background: palegreen;
}

#pro .select {
	background: #337ab7;
}

#pro td {
	width: 150px;
}

#pro input {
	width: 80px;
}

#main {
	position: absolute;
	left: 240px;
	top: 100px;
}

.input-group {
	width: 500px;
	left: 200px;
}

#search {
	position: absolute;
	width: 80px;
	left: 300px;
}

#title th {
	text-align: center;
}

#proId {
	position: absolute;
	width: 140px;
	height: 33px;
	top: 1px;
	left:10px;
}
#countId {
	position: absolute;
	width: 120px;
	height: 33px;
	top: 1px;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						var selectId = -1;
						$("#showAdd").click(function() {
							location.href = "pro1?type=showAdd";
						})
						$("#showModify")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "pro1?type=showModify&id="
														+ selectId;
											} else {
												alert("请选中一条数据");
											}
										})
						$("tr").click(function() {
							$(this).toggleClass("select");
							selectId = $(this).data("id");
						})
						$("#delete").click(
								function() {
									if (selectId > -1) {
										location.href = "pro1?type=delete&id="
												+ selectId;
									} else {
										alert("请选中一条数据");
									}

								})
						
					

					})
</script>
</head>
<body>
	<div style="padding: 20px 20px 10px;">
		<form class="bs-example bs-example-form" role="form" action="pro1"
			method="post">
			<div class="input-group">
				<select name="name" id="proId" class="form-control">
					<option value="">请选择项目</option>
					<c:forEach items="${proList}" var="pro">
					<option value="${pro.name}" <c:if test="${c.name==pro.name}">selected</c:if>>${pro.name}</option>
					</c:forEach>
				</select> 


				<button type="submit" class="btn btn-primary" id="search">
					搜索</button>


			</div>



		</form>
	</div>

	<div id="main">
		<c:if test="${p.ye<=1}">
			<script>
				$(document).ready(function() {
					$("#former").hide();
				})
			</script>

		</c:if>
		<c:if test="${p.ye>=p.maxYe}">
			<script>
				$(document).ready(function() {
					$("#next").hide();
				})
			</script>


		</c:if>


		<table id="pro" border=1>
			<thead>
				<tr id="title">
					<th>id</th>
					<th>部门</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="pro">
					<tr data-id="${pro.id}">
					<td>${pro.id}</td>
						<td>${pro.name}</td>
					</tr>

				</c:forEach>
			</tbody>



		</table>



		<ul class="pagination">
			<li id="former"><a
				href="pro1?ye=${p.ye-1}&name=${c.name}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li id="page1"
					<c:if test="${p.ye==status.index}"> class="active" </c:if>><a
					href="pro1?ye=${p.ye+1}&name=${c.name}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="pro1?ye=${p.ye+1}&name=${c.name}">下一页</a></li>
		</ul>
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showModify">修改</button>

			<button type="button" class="btn btn-primary" id="delete">刪除</button>
		</div>

	</div>

</body>
</html>