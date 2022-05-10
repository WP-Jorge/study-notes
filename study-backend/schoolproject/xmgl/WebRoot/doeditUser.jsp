<%@ page language="java" import="java.util.*,dao.*,entity.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'exit.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<jsp:include page="islogin.jsp"></jsp:include>
	<body>
		<%
			request.setCharacterEncoding("utf-8");

			int u_id = Integer.parseInt(request.getParameter("id"));
			String u_mail = request.getParameter("mail");
			String u_password = request.getParameter("pwd");
			int u_role = Integer.parseInt(request.getParameter("role"));
			String u_realname = request.getParameter("realname");
			String u_phone = request.getParameter("phone");
			String u_qq = request.getParameter("qq");
			int u_status = Integer.parseInt(request.getParameter("status"));

			UserDao ud = new UserDao();
			int row = ud.updateUserInfo(u_id, u_mail, u_password, u_role,u_realname, u_phone, u_qq, u_status);
			if (row == 1) {
		%>
		<jsp:forward page="usermanager.jsp"></jsp:forward>
		<%
			} else {
		%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%
			}
		%>
	</body>
</html>
