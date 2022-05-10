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
		<link rel="stylesheet" type="text/css" href="static/subscribe/css/subscribe.css"/>
		<link rel="stylesheet" type="text/css" href="static/lib/bootstrap-4.5.0-dist/css/bootstrap.css"/>
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
					<div class="subscribe_title">
						我的借阅
					</div>
					<div class="subscribe_list">
						<table class="table table-striped table-hover">
						  <thead>
						    <tr class="table-info">
						      <th scope="col">序号</th>
						      <th scope="col">书名</th>
						      <th scope="col">类型</th>
						      <th scope="col">发行日期</th>
							  <th scope="col">简介</th>
							  <th scope="col">操作</th>
						    </tr>
						  </thead>
						  <tbody>
						  <c:forEach items="${borrowList}" var="book" varStatus="index">
							  <tr>
								  <td>${index.count}</td>
								  <td>${book.bname}</td>
								  <td>${book.btype}</td>
								  <td>${book.byear}</td>
								  <td>${book.bdes}</td>
								  <td><a href="javascript:restitute(${book.bid})" class="btn btn-outline-info">归还</a></td>
							  </tr>
						  </c:forEach>
						  </tbody>
						</table>
					</div>
				</article>
			</div>
			
			<footer>
				<div class="footer">
					@Z09418233 朱心熹
				</div>
			</footer>
		</div>
		<script src="static/subscribe/js/subscribe.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
