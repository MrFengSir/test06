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
	width: 530px;
	height: 240px;
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
}

#emp input {
	width: 80px;
}

#main {
	position: absolute;
	left: 240px;
	top: 100px;
}


#title th{
text-align: center;
}
#topform{
position: absolute;
left: 210px;
top: 20px;
}
#bigPhoto{
display:none;
position:absolute;
}
#bigPhoto img{
width:100px;
height:150px;
}

</style>
<script>
	$(document)
			.ready(
					function() {
						var selectId = -1;
						$("#showAdd").click(function() {
							location.href = "emp1?type=showAdd";
						})
						$("#showAdd2").click(function() {
							location.href = "emp1?type=showAdd2";
						})
						$("#showAdd3").click(function() {
							location.href = "emp1?type=showAdd3";
						})
						$("#showModify")
								.click(
										function() {
											if (selectId > -1) {
												location.href = "emp1?type=showModify&id="
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
										location.href = "emp1?type=delete&id="
												+ selectId;
									} else {
										alert("请选中一条数据");
									}

								})
						function doBatch(type) {
							var length = $("#emp .select").length;
							var ids = "";
							if (length > 0) {
								$("#emp .select").each(
										function(index, element) {
											ids += $(this).data("id") + ",";
										})
								ids = ids.substring(0, ids.length - 1);
								location.href = "emp1?type=" + type + "&ids="
										+ ids;
							} else {
								alert("请选中一条数据");
							}

						}
						$("#deleteBatch").click(function() {
							doBatch("deleteBatch");

						})

						$("#modifyBatch1").click(function() {
							doBatch("showModifyBatch1");

						})
						$("#modifyBatch2").click(function() {
							doBatch("showModifyBatch2");

						})
						$("tr")
								.dblclick(
										function() {
											$(this).unbind("dblclick");
											$(this).unbind("click");
											$(this).addClass("updateEmp");
											var name = $(this).children().eq(0)
													.text();
											$(this)
													.children()
													.eq(0)
													.html(
															"<input type='text' name='name' value='"+name+"' />");

											var sex = $(this).children().eq(1)
													.text();
											var select = "";
											if (sex == "男") {
												select = "<select name='sex'><option selected value='男'>男</option><option value='女'>女</option></select>";
											} else {
												select = "<select name='sex'><option value='男'>男</option><option selected value='女'>女</option></select>";
											}
											$(this).children().eq(1).html(
													select);

											var age = $(this).children().eq(2)
													.text();
											$(this)
													.children()
													.eq(2)
													.html(
															"<input type='text'  name = 'age' value='"+age+"' />");

										})
						$("#modifyBatch3")
								.click(
										function() {
											//var emps ="";
											var arr = new Array();
											$(".updateEmp")
													.each(
															function(index,
																	element) {
																var id = $(this)
																		.data(
																				"id");
																var name = $(
																		this)
																		.find(
																				"[name=name]")
																		.val();
																var sex = $(
																		this)
																		.find(
																				"[name=sex]")
																		.val();
																var age = $(
																		this)
																		.find(
																				"[name=age]")
																		.val();
																var emp = {
																	id : id,
																	name : name,
																	sex : sex,
																	age : age
																}
																arr.push(emp);

															})
											//emps=emps.substring(0,emps.length-1);
											var str = JSON.stringify(arr);
											str = str.replace(/{/g, "%7b");
											str = str.replace(/}/g, "%7d");

											location.href = "emp1?type=modifyBatch3&emps="
													+ str;
										})
										
										$("#emp img").hover(function(event){
											var photo=$(this).attr("src");
											$("#bigPhoto img").attr("src",photo);
											$("#bigPhoto").fadeIn();
											$("#bigPhoto").css({left:event.pageX-100,top:event.pageY-100});
										},function(){
											$("#bigPhoto").fadeOut();
											
										})
									

					})
</script>
</head>
<body>
<div id="topform">
	<form action="emp1" class="form-horizontal" method="post">
			<div class="form-group">
				<div class="col-sm-2">
					<input type="text" class="form-control" placeholder="请输入姓名"
						name="name" value=${c.name}>
				</div>
				<div class="col-sm-3">
					<select class="form-control" name="sex">
						<option value="">请选择性别</option>
						<option value='男' <c:if test="${c.sex=='男' }">selected</c:if>>男</option>
						<option value='女' <c:if test="${c.sex=='女' }">selected</c:if>>女</option>
					</select>
				</div>
				<div class="col-sm-2">
					<input type="text" class="form-control" placeholder="请输入年龄"
						name="age" value=${c.age==-1?"":c.age }>
				</div>
				
					<div class="col-sm-3">
					<select class="form-control" name="depName">
						<option value="">请选择部门</option>
					<c:forEach items="${depList}" var="dep">
					<option value="${dep.name}" <c:if test="${c.dep.name==dep.name}">selected</c:if>>${dep.name}</option>
					</c:forEach>
						
					</select>
				</div>
				<div class="col-sm-2">
					<button type="submit" class="btn btn-primary">搜索</button>
				</div>
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
		<c:if test="${p.ye==p.maxYe}">
			<script>
				$(document).ready(function() {
					$("#next").hide();
				})
			</script>


		</c:if>


		<table id="emp" border=1>
			<thead>
				<tr id="title">
					<th>姓名</th>
					<th>性別</th>
					<th>年齡</th>
					<th>部门</th>
					<th>照片</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="emp">
					<tr data-id="${emp.id}">
						<td>${emp.name}</td>
						<td>${emp.sex}</td>
						<td>${emp.age}</td>
						<td>${emp.dep.name}</td>
						<td><c:if test="${not empty emp.pic_name}"><img src="pic/${emp.pic_name}" style="width:30px;height:40px"/></c:if></td>
					</tr>

				</c:forEach>
			</tbody>



		</table>



		<ul class="pagination">
		<li><a href="emp1?ye=1&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depName=${c.dep.name}">首页</a></li>
			<li id="former"><a href="emp1?ye=${p.ye-1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depName=${c.dep.name}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li id="page1"
					<c:if test="${p.ye==status.index}"> class="active" </c:if>><a
					href="emp1?ye=${status.index}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depName=${c.dep.name}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a href="emp1?ye=${p.ye+1}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depName=${c.dep.name}">下一页</a></li>
					<li ><a href="emp1?ye=${p.maxYe}&name=${c.name}&sex=${c.sex}&age=${c.age!=-1?c.age:''}&depName=${c.dep.name}">尾页</a></li>
			
		</ul>
		<div>
			<button type="button" class="btn btn-primary" id="showAdd">增加</button>
			<button type="button" class="btn btn-primary" id="showAdd2">增加2</button>
			<button type="button" class="btn btn-primary" id="showAdd3">增加3</button>
			<button type="button" class="btn btn-primary" id="showModify">修改</button>
			<button type="button" class="btn btn-primary" id="modifyBatch1">批量修改1</button>
			<button type="button" class="btn btn-primary" id="modifyBatch2">批量修改2</button>
			<button type="button" class="btn btn-primary" id="modifyBatch3">批量修改3</button>
			<button type="button" class="btn btn-primary" id="delete">刪除</button>
			<button type="button" class="btn btn-primary" id="deleteBatch">批量刪除</button>
		</div>
		<div id="bigPhoto">
		<img src="" />
		
		</div>

	</div>

</body>
</html>