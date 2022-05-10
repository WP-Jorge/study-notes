<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script src="static/lib/jquery-3.5.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="static/lib/bootstrap-4.5.0-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="static/base/base.css"/>
		<link rel="stylesheet" type="text/css" href="static/login/css/login.css"/>
		<link rel="stylesheet" type="text/css" href="static/lib/bootstrap-4.5.0-dist/css/bootstrap.css"/>
	</head>
	<body>
		<div class="wrapper">
			<div class="content">
				<header>
					<div class="header">
						图书管理系统
					</div>
				</header>
				
				<article>
					<%String msg = "欢迎注册图书管理系统！";%>
					<div class="alert alert-warning alert-dismissible fade show" role="alert">
						<c:if test='${requestScope.msg != null}'>
							${requestScope.msg}
						</c:if>
						<c:if test='${requestScope.msg == null}'>
							<%= msg %>
						</c:if>
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
					    <span aria-hidden="true">&times;</span>
					  </button>
					</div>
					
					<div class="input_bar">
						<div class="input_title">图书管理系统</div>
						<form action="regist.do" method="post" class="login_input">
							<input required="required" type="text" name="username" class="username" placeholder="请输入用户名">
							<input required="required" type="password" name="password" class="password" placeholder="请输入密码">
							<div class="action">
								<a href="login.jsp" class="regiest btn btn-outline-success">去登录</a>
								<input type="submit" class="login btn btn-outline-info" value="注册"/>
							</div>
						</form>
					</div>
				</article>
			</div>
			
			<footer>
				<div class="footer">
					@Z09418233 朱心熹
				</div>
			</footer>
		</div>
		
		<script src="static/login/js/login.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
