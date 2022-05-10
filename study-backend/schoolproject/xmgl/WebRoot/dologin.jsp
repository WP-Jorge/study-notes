<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="dao.*,entity.*,java.util.*,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>dologin</title>
</head>
<body>
<%
session.removeAttribute("umail");
String mail=request.getParameter("mail");
String psw=request.getParameter("password");
UserDao ud=new UserDao();
if(ud.checkLogin(mail,psw)){
	session.setAttribute("umail",mail);%>
	<jsp:forward page="main.jsp"></jsp:forward>
	<% }
else {
	String msg="Email or Password Error !";
	response.sendRedirect("index.jsp?msg="+msg);}
%>
</body>
</html>