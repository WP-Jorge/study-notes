<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
	String basePath = request.getScheme() + "://" +
			request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
	<title>$Title$</title>
	<base href="<%=basePath%>"/>
</head>
<body>
<p><a href="user/some">发送user/some的get请求</a></p>
<br>
</body>
</html>
