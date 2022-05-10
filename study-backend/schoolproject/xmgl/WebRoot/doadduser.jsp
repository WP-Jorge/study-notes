<%@ page language="java" import="java.util.*,dao.*,entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
  		  		  		  		  		  		  		  			User newuser=new User();
  		  		  		  		  		  		  		  			newuser.setUmail(request.getParameter("u_mail"));
  		  		  		  		  		  		  		  			newuser.setUpassword(request.getParameter("u_password"));
  		  		  		  		  		  		  		  			newuser.setUphone(request.getParameter("u_phone"));
  		  		  		  		  		  		  		  			newuser.setUqq(request.getParameter("u_qq"));
  		  		  		  		  		  		  		  			newuser.setUrealname(request.getParameter("u_realname"));
  		  		  		  		  		  		  		  			newuser.setUrole(Integer.parseInt(request.getParameter("u_role").toString().trim()));
  		  		  		  		  		  		  		  			newuser.setUstatus(Integer.parseInt(request.getParameter("u_status").toString().trim()));
  		  		  		  		  		  		  		  			if(new UserDao().add(newuser)==1){
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
