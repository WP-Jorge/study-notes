<%@ page import="domain.Book" %>
<%@ page import="java.util.List" %>
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
		<link rel="stylesheet" type="text/css" href="static/library/css/library.css" />
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
					<div class="library_title">
						图书馆藏
					</div>
					<form class="search" action="search.do" method="post">
						<div class="form-row align-items-center">
							<%String bname = (String) request.getAttribute("bname") == null ? "" : (String) request.getAttribute("bname");%>
							<%String btype = (String) request.getAttribute("btype") == null ? "" : (String) request.getAttribute("btype");%>
							<%String byear = (String) request.getAttribute("byear") == null ? "" : (String) request.getAttribute("byear");%>
							<div class="col-auto">
								<input type="text" name="bname" value="<%=bname%>" class="form-control mb-2" placeholder="书名">
							</div>
							<div class="col-auto">
								<input type="text" name="btype" value="<%=btype%>" class="form-control mb-2" placeholder="类型">
							</div>
							<div class="col-auto">
								<input type="text" name="byear" value="<%=byear%>" class="form-control mb-2" placeholder="发行日期">
							</div>
							<div class="col-auto">
								<button type="submit" class="btn btn-info mb-2">搜索</button>
							</div>
						</div>
					</form>
					<div class="library_list">
						<table class="table table-striped table-hover">
						  <thead>
						    <tr class="table-info">
						      <th scope="col">序号</th>
						      <th scope="col">书名</th>
						      <th scope="col">类型</th>
						      <th scope="col">发行时间</th>
							  <th scope="col">简介</th>
						      <th scope="col">操作</th>
						    </tr>
						  </thead>
						  <tbody>
						  <c:forEach items="${sessionScope.allBooks}" var="book" varStatus="index">
							  <c:if test="${book.borrowed == 0}">
								  <tr>
									  <td>${index.count}</td>
									  <td>${book.bname}</td>
									  <td>${book.btype}</td>
									  <td>${book.byear}</td>
									  <td>${book.bdes}</td>
									  <td><a href="javascript:borrow(${book.bid})" class="btn btn-outline-info">借阅</a></td>
								  </tr>
							  </c:if>
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
		<script src="static/library/js/library.js" type="text/javascript" charset="utf-8"></script>
	</body>
</html>
