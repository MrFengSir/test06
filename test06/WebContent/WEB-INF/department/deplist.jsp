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
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<style>
#dep {
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

#dep .select {
	background: #337ab7;
}

#dep td {
	width: 150px;
}

#main {
	position: absolute;
	left: 240px;
	top: 100px;
}

.form-horizontal {
	position: absolute;
	left: 300px;
	top: 40px;
}

#title1 th {
	text-align: center;
}
#modal{
background:#fff;
}
#modal-body{
width:1000px;
height:800px;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						var selectId = -1;
						$("#showAddDep").click(function() {
							location.href = "dep1?type=showAddDep";
						})
						$("#showModifyDep")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "dep1?type=showModifyDep&id="
														+ selectId;
											} else {
												alert("请选中一条数据");
											}
										})
										$("#showManagePro")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "dep1?type=showManagePro&id="
														+ selectId;
											} else {
												alert("请选中一条数据");
											}
										})
											$("#showManagePro2")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "dep1?type=showManagePro2&id="
														+ selectId;
											} else {
												alert("请选中一条数据");
											}
										})
										
										$("#showManagePro3")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "dep1?type=showManagePro3&id="
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
										location.href = "dep1?type=delete&id="  
												+ selectId;
									} else {
										alert("请选中一条数据");
									}
 
								})

								<c:if test="${p.ye<=1}">
									$("#former").hide();
								</c:if>
					

					<c:if test="${p.ye==p.maxYe}">
								$("#next").hide();
					</c:if>
					
					$("#showManagePro4")
					.click(
							function() {
								if (selectId > -1) {
									$("#modal-body").load("dep1?type=showManagePro3&id="+selectId);
									$("#myModal").modal("show");
								} else {
									alert("请选中一条数据");
								}
							})
						
					

					})
</script>
</head>
<body>

	<form action="dep1" class="form-horizontal" method="post">
		<div class="form-group">
			<div class="col-sm-4">
				<select class="form-control" name="name">
					<option value="">请选择部门</option>
					<c:forEach items="${depList}" var="dep">
						<option value="${dep.name}"
							<c:if test="${dep.name==c.name}">selected</c:if>>${dep.name}</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-sm-4">
				<input type="text" class="form-control" placeholder="请输入部门人数"
					name="emp_count" value=${c.emp_count!=-1?c.emp_count:''}>
			</div>
			<div class="col-sm-2">
				<button type="submit" class="btn btn-primary">搜索</button>
			</div>
		</div>

	</form>

	<div id="main">


		<table id="dep" border=1>
			<thead>
				<tr id="title1">
					<th>部门</th>
					<th>部门人数</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="dep">
					<tr data-id="${dep.id}">
						<td>${dep.name}</td>
						<td>${dep.emp_count}</td>
					</tr>

				</c:forEach>
			</tbody>



		</table>



		<ul class="pagination">
			<li id="former"><a
				href="dep1?ye=${p.ye-1}&name=${c.name}&emp_count=${c.emp_count}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li id="page1"
					<c:if test="${p.ye==status.index}"> class="active" </c:if>><a
					href="dep1?ye=${p.ye+1}&name=${c.name}&emp_count=${c.emp_count}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="dep1?ye=${p.ye+1}&name=${c.name}&emp_count=${c.emp_count}">下一页</a></li>
		</ul>
		<div>
			<button type="button" class="btn btn-primary" id="showAddDep">增加</button>
			<button type="button" class="btn btn-primary" id="showModifyDep">修改</button>

			<button type="button" class="btn btn-primary" id="delete">刪除</button>
			<button type="button" class="btn btn-primary" id="showManagePro">項目管理</button>
			<button type="button" class="btn btn-primary" id="showManagePro2">項目管理2</button>
			<button type="button" class="btn btn-primary" id="showManagePro3">項目管理3</button>
			<button type="button" class="btn btn-primary" id="showManagePro4">項目管理4</button>
			
		</div>

	</div>
	
	

</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;<tton>
							<h4 class="modal-title" id="myModalLabel">项目</h4>
						</div>
						<div class="modal-body" id="modal-body">
						
						</div>
						<!-- /.modal-content -->
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">关闭<tton>

						</div>
					</div>
					<!-- /.modal -->
				</div>

			</div>



	

</body>
</html>