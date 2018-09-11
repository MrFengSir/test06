<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
		<script type="text/javascript" src="js/jquery.js" ></script>
</head>
<body>
	<div id="main">
		<form action="emp1" class="form-horizontal" method="post">
			<input type="hidden" name="type" value="modify">
			<input type="hidden" name="id" value="${emp.id}">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" value="${emp.name}"
						name="name">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-2">
					男<input type="radio" name="sex" checked value="男" <c:if test="${emp.sex=='男'}">checked</c:if> > 女 <input
						type="radio" name="sex" value="女" <c:if test="${emp.sex=='女'}">checked</c:if>>
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" value="${emp.age}"
						name="age">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门名称</label>
				<div class="col-sm-2">
					<select class="form-control" name="depId">
						<option value="">请选择部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.id}" <c:if test="${dep.id==emp.dep.id}">selected</c:if>>${dep.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-2">
					<button type="submit" class="btn btn-primary">确定</button>
				</div>
			</div>
		</form>
	</div>

</body>
</html>