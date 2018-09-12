<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />


<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">

#title{
position:absolute;
top:15px;
left:150px;
font-size:30px;

}
#pro{
position:absolute;
z-index:200px;
width:600px;
height:280px;
left:0px;
top:60px;
border:1px solid #666;
}
#noPro{
position:absolute;
width:600px;
height:280px;
left:0px;
top:460px;
border:1px solid #666;
}
#btn{
position:absolute;
width:200px;
height:40px;
left:150px;
top:370px;
float:left;
}

.pro{
background: #337ab7;
height:40px;
line-height: 40px;
padding:0 20px;
margin-top: 20px;
color:#fff;
margin-left: 5px;
float: left;
}
.select{
background: red;
}
#btn .btn btn-primary{
margin-right: px;
}

</style>

<title>Insert title here</title>
<script>
$().ready(function(){
	
	$("#main2 .pro").click(function(){
		 
		$(this).toggleClass("select");
	})
	$("#main2 #addPro3").click(function(){
		
		
		if($("#main2 #noPro").find(".select").length>0){
			var proId=$("#main2 #noPro").find(".select").data("id");
			
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
				var div = $("#noPro").find(".select");
				$("#main2 #pro").append(div);
				div.removeClass("select");
				$("#main2 #noPro").remove(div);
			}
			
		}
			
			
		})
			
			
			
		}else{
			alert("请选中一条数据");
		}
		
		
	})
	
	
	$("#main2 #deletePro3").click(function(){
		
		
		if($("#main2 #pro").find(".select").length>0){
			var proId=$("#main2 #pro").find(".select").data("id");
			
		$.ajax({
			url:"dep1",
			type:"post",
			data:{
				type:"deletePro2",
				depId:${dep.id},
				proId:proId
			},
		dataType:"text",
		success:function(data){
			if(data=="true"){
				var div = $("#main2 #pro").find(".select");
				$("#main2 #noPro").append(div);
				div.removeClass("select");
				$("#main2 #pro").remove(div);
			}
			
		}
			
			
		})
			
			
			
		}else{
			alert("请选中一条数据");
		}
		
		
	})
	$("#main2 #addBatch").click(function(){
		if($("#noPro").find(".select").length>0){
			var proIds="";
			$("#main2 #noPro").find(".select").each(function(index,element){
				proIds+=$(this).data("id")+",";	
			})
			
				$.ajax({
			url:"dep1",
			type:"post",
			data:{
				type:"addPro2Batch",
				depId:${dep.id},
				proIds:proIds  
			}, 
		dataType:"text",
		success:function(data){
			if(data=="true"){
				var div = $("#main2 #noPro").find(".select");
				$("#main2 #pro").append(div);
				div.removeClass("select");
				$("#main2 #noPro").remove(div);
			}
			
		}
			
				})
			
		}else{
			alter("请选中一条数据");
		}
	})
	
	$("#main2 #deleteBatch").click(function(){
		if($("#pro").find(".select").length>0){
			var proIds="";
			$("#main2 #pro").find(".select").each(function(index,element){
				proIds+=$(this).data("id")+",";	
			})
			
				$.ajax({
			url:"dep1",
			type:"post",
			data:{
				type:"deletePro2Batch",
				depId:${dep.id}, 
				proIds:proIds  
			}, 
		dataType:"text",
		success:function(data){
			if(data=="true"){
				var div = $("#main2 #pro").find(".select");
				$("#noPro").append(div);
				div.removeClass("select");
				
				$("#main2 #pro").remove(div);
			}
			
		}
			
				})
			
		}else{
			alter("请选中一条数据");
		}
	})
		var proLeft = $("#main2 #pro").offset().left;
	    var proTop = $("#main2 #pro").offset().top;
	    var proWidth = parseFloat($("#main2 #pro").css("width"));
	    var proHeight = parseFloat($("#main2 #pro").css("Height"));
	    var startLeft;
	    var startTop;
	 $("#main2 #noPro .pro").draggable({
		 
		 start:function(){
			 startLeft = $(this).offset().left;
			 startTop = $(this).offset().top;
		 },
		 stop:function(){
			 var stopLeft = $(this).offset().left;
			 var stopTop = $(this).offset().top;
			 var pro = $(this);
			if(stopLeft>proLeft&&stopLeft<proLeft+proWidth&&stopTop>proTop&&stopTop<proTop+proHeight){
				var proId=$(this).data("id");
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
						pro.css("position","static");
						$("#main2 #pro").append(pro);
						pro.css("position","relative");
						pro.css("left","0");
						pro.css("top","0");
						pro.removeClass("select");

					}
					
				}
					
					
				})
				
			}else{
				$(this).offset({left:startLeft ,top:startTop})
			}
		 }
		 
	 });
	
	var noProLeft = $("#main2 #noPro").offset().left;
	var noProTop = $("#main2 #noPro").offset().top;
	var noProWidth = parseFloat($("#main2 #noPro").css("width"));
	var noProHeight = parseFloat($("#main2 #noPro").css("Height"));
	 $("#main2 #pro .pro").draggable({
		 
		 start:function(){
			var startLeft = $(this).offset().left;
			 var startTop = $(this).offset().top;
		 },
		 stop:function(){
			 var stopLeft = $(this).offset().left;
			 var stopTop = $(this).offset().top;
			 var noPro = $(this);
			if(stopLeft>noProLeft&&stopLeft<noProLeft+noProWidth&&stopTop>noProTop&&stopTop<noProTop+noProHeight){
				var noProId=$(this).data("id");
				$.ajax({
					url:"dep1",
					type:"post",
					data:{
						type:"deletePro2",
						depId:${dep.id}, 
						proId:noProId
					}, 
				dataType:"text", 
				success:function(data){
					if(data=="true"){
						noPro.css("position","static");
						$("#main2 #noPro").append(noPro);
						noPro.css("position","relative");
						noPro.css("left","0");
						noPro.css("top","0");
						noPro.removeClass("select");
					}
					
				}
					
					
				})
				
			}else{
				$(this).offset({left:startLeft ,top:startTop})
			}
		 }
		 
	 });
	
	
})



</script>
</head>
<body>
<div id="main2">
<div id="title">
${dep.name}
</div>
<div id="pro">
<c:forEach items="${list}" var="pro">
<div class="pro" data-id="${pro.id}">${pro.name}</div>
</c:forEach>
</div>
<div id="btn">
			<button type="button" class="btn btn-primary" id="addPro3">↑</button>
			<button type="button" class="btn btn-primary" id="addBatch">批量↑</button>
			<button type="button" class="btn btn-primary" id="deletePro3">↓</button>
			<button type="button" class="btn btn-primary" id="deleteBatch">批量↓</button>
			

</div>

<div id="noPro"> 
<c:forEach items="${noHaveList}" var="noPro">
<div class="pro" data-id="${noPro.id}">${noPro.name}</div>
</c:forEach>
</div>
</div>
</body>
</html>