<%--
  Created by IntelliJ IDEA.
  User: 蛋丁
  Date: 2020/10/20
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>登录</title>
	<style type="text/css">
        * {
            padding: 0;
            margin: 0;
        }
        .container {
            width: 300px;
            margin: 100px auto;
        }
        .container h2 {
            text-align: center;
        }
        form input:nth-child(1),form input:nth-child(3) {
            width: 100%;
            height: 30px;
            margin-bottom: 10px;
        }
        form input:nth-child(1) {
            margin-top: 10px;
        }
	</style>
</head>
<body>
<div class="container">
	<h2>登录DVD借阅系统</h2>
	<div class="form">
		<form action="loginServlet.do" method="post">
			<input type="text" name="username" placeholder="请输入用户名" />
			<br />
			<input type="password" name="password" placeholder="请输入密码" />
			<br />
			<input type="submit" value="登录"/>
		</form>
	</div>
</div>
</body>
</html>
