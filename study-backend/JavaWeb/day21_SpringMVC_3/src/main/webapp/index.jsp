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
<p>当出来方法返回ModelAndView实现forward</p>
<form action="doForward.do" method="post">
	姓名：<input type="text" name="name"></input><br>
	年龄：<input type="text" name="age"></input><br>
	<button>提交</button>
</form>
<br>
<p>当出来方法返回ModelAndView实现redirect</p>
<form action="doRedirect.do" method="post">
	姓名：<input type="text" name="name"></input><br>
	年龄：<input type="text" name="age"></input><br>
	<button>提交</button>
</form>
</body>
</html>