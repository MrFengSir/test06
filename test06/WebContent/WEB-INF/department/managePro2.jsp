<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery.js"></script>
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
	left: 300px;
	top: 100px;
}
.form-horizontal{
position:absolute;
left:400px;
top:30px;

}


#title th {
	text-align: center;
}
#top{
position:absolute;
left:530px;
font-size:25px;
top:40px;
}
.form-control{
width:200px;

}

</style>
<script>
	$(document)
			.ready(
					function() {
						
					
						$("#addPro").click(function(){
							var proId = $("#selectId").val();
							var i =0; 
							$.ajax({
								url:"dep1",
								type:"post", 
								data:{ 
									type:"addPro2",
									depId:${dep.id}, 
									proId:proId
								},
							dataType:"text", 
								success:function(data){
									if(data=="true"){ 
										
										var proName="";
										$("#selectId").children().each(function(index,element){
											
											if($(this).val()==proId){ 
												proName=$(this).text();  
												i=index;
											}
										})
										var tr = "<tr data-id="+proId+"><td>"+proId+"</td><td>"+proName+"</td></tr>";
										$("#dep").append(tr);
										$("#selectId").children().eq(i).remove();
										if($("#selectId").children().length==1){
											$("#addPro").unbind("click");
											$("#addPro").addClass("disabled");
											}
									
										
									}
									
								}	
						})
						
						})
						  $("#deletePro").click(
								  
								function() {
									if (selectId > -1) {
										var i =0; 
										$.ajax({
											url:"dep1",
											type:"post", 
											data:{ 
												type:"deletePro2",
												depId:${dep.id},  
												proId:selectId
											}, 
										dataType:"text", 
											success:function(data){
												if(data=="true"){ 
													
													var proName="";
													$("tr").each(function(index,element){
														
														if($(this).data("id")==selectId){ 
															proName=$(this).children().eq(1).text();
															i=index;
														}
													})
													var option = "<option value='"+selectId+"'>"+proName+"</option>";
													$("#selectId").append(option);
													$("tr").eq(i).remove();
													$("#addPro").click(function(){
														var proId = $("#selectId").val();
														var i =0; 
														$.ajax({
															url:"dep1",
															type:"post", 
															data:{ 
																type:"addPro2",
																depId:${dep.id}, 
																proId:proId
															},
														dataType:"text", 
															success:function(data){
																if(data=="true"){ 
																	
																	var proName="";
																	$("#selectId").children().each(function(index,element){
																		
																		if($(this).val()==proId){ 
																			proName=$(this).text();  
																			i=index;
																		}
																	})
																	var tr = "<tr data-id="+proId+"><td>"+proId+"</td><td>"+proName+"</td></tr>";
																	$("#dep").append(tr);
																	$("#selectId").children().eq(i).remove();
																	if($("#selectId").children().length==1){
																		$("#addPro").unbind("click");
																		$("#addPro").addClass("disabled");
																		}
																
																	
																}
																
															}	
													})
													
													})
													
												}
												
											}	
									})
										
									} else {
										alert("请选中一条数据");
									}

								})
						if($("#selectId").children().length==1){
							$("#addPro").unbind("click");
							$("#addPro").addClass("disabled");
							}
						
						$(document).on("click","tr",function() {
							$(this).toggleClass("select");
							selectId = $(this).data("id");
							
						})
					     
					
						
					})
</script>
</head>
<body>
	
	<div id="top">
	${dep.name}
	</div>

	<div id="main">

			
		<table id="dep" border=1>
			<thead>
				<tr id="title">
					<th>id</th>
					<th>项目</th>
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
		

</br>


		<div>
		<select class="form-control " name="proId" id="selectId">
					<option value="">请选择项目</option>
					<c:forEach items="${noHaveList}" var="noHavePro">
						<option value="${noHavePro.id}">${noHavePro.name}</option>
					</c:forEach>
				</select>
		
			<button type="button" class="btn btn-primary" id="addPro">增加</button>
			<button type="button" class="btn btn-primary" id="deletePro">刪除</button>
		</div>

	</div>

</body>
</html>