<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
/* 	if(session.isNew()){
		String ip = request.getRemoteAddr();
		Set<String> set = new HashSet<String>();
		if (application.getAttribute("set") != null) {
			set = (Set<String>) application.getAttribute("set");
		}
		set.add(ip);
		int num = set.size();
		application.setAttribute("set", set);
	} */
	%>
	本网站共<%=application.getAttribute("num") %>个人访问
</body>
</html>