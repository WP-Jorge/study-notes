<%@ page import="domain.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script src="static/lib/jquery-3.5.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="static/lib/bootstrap-4.5.0-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="static/base/base.css" />
		<link rel="stylesheet" type="text/css" href="static/profile/css/profile.css" />
		<link rel="stylesheet" type="text/css" href="static/lib/bootstrap-4.5.0-dist/css/bootstrap.css" />
	</head>
	<body>
		<div class="wrapper">
			<div class="content">
				<header>
					<div class="header">
						<a href="index.jsp">图书管理系统</a>
					</div>
				</header>

				<article>
					<div class="profile_title">个人信息</div>
					<div class="profile_list">
						<ul class="list-group">
							<%User user = (User) session.getAttribute("user");%>
							<li class="list-group-item">用户ID：<%=user.getUid()%></li>
							<li class="list-group-item">用户名：<%=user.getUsername()%></li>
							<li class="list-group-item">借阅书籍数：${sessionScope.borrowCount}</li>
						</ul>
					</div>
				</article>
			</div>

			<footer>
				<div class="footer">
					@Z09418233 朱心熹
				</div>
			</footer>
		</div>
		<script src="static/profile/js/profilw.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
