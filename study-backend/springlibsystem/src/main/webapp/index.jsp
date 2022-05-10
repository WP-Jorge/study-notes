<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title></title>
		<script src="static/lib/jquery-3.5.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="static/lib/bootstrap-4.5.0-dist/js/bootstrap.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="static/base/base.css"/>
		<link rel="stylesheet" type="text/css" href="static/index/css/index.css" />
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
<%--				<%System.out.println(session.getAttribute("user"));%>--%>
				<article>
					<div class="nav">
						<div class="nav_title">
							图书管理系统导航
						</div>
						<div class="nav_list">
							<div class="alert alert-info" role="alert">
								<a href="library.do" class="alert-link">1.前往图书馆</a>
							</div>
							<div class="alert alert-info" role="alert">
								<a href="subscribe.do" class="alert-link">2.查看我的借阅</a>
							</div>
							<div class="alert alert-info" role="alert">
								<a href="profile.do" class="alert-link">3.查看个人信息</a>
							</div>
							<div class="alert alert-info" role="alert">
								<a href="logout.do" class="alert-link">4.退出系统</a>
							</div>
						</div>
					</div>
				</article>
			</div>

			<footer>
				<div class="footer">
					@Z09418233 朱心熹
				</div>
			</footer>
		</div>
		<script src="static/index/js/index.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
