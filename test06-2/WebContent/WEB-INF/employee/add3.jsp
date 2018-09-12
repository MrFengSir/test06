<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<style>
#photos img{

width:100px;
height:150px;

}
</style>
<script>
$(document).ready(function(){
	
	$("#upload").click(function(){
		var formData = new FormData();
		for(var i =0;i<$("[name=pic_name]")[0].files.length;i++){
		formData.append("pic_name",$("[name=pic_name]")[0].files[i]);
		}
	$.ajax({
		url:"emp1?type=upload",
		type:"post",
		data:formData,
		cache:false,
		processData:false,
		contentType:false,
		dataType:"text",
		success:function(data){
			var str="<img src='pic/"+data+"'/>";
			str+="<input type='hidden' name='photo' value='"+data+"' />";
			$("#photos").append(str);
			
		}
	})
	})
})


</script>

</head>
<body>
<div id="main">
		<form action="emp1?type=add3" class="form-horizontal" method="post" >
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" placeholder="请输入姓名"
						name="name">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-2">
					男<input type="radio" name="sex" checked value="男"> 女 <input
						type="radio" name="sex" value="女">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" placeholder="请输入年龄"
						name="age">
				</div>
			</div>

			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门名称</label>
				<div class="col-sm-2">
					<select class="form-control" name="depId">
						<option value="">请选择部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.id}">${dep.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">照片</label>
				<div class="col-sm-2">
					<input type="file" class="form-control"  value="请选择文件"
						name="pic_name" >
				</div>
				
				<div class="col-sm-2">
					<input type="button" class="btn btn-primary"  value="上传"
						id="upload">
				</div>	
			</div>
			<div id="photos">
			
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