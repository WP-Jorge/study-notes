<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Title</title>
</head>
<body>
<p>模拟退出系统，张三退出系统系统</p>
<%
	session.removeAttribute("name");
%>
</body>
</html>