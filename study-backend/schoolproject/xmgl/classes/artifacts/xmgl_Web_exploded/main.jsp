<%@ page language="java" import="dao.*,java.util.*,entity.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>创新项目管理系统</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

  </head>
  <jsp:include page="islogin.jsp"></jsp:include>
  		<frameset border="0" rows="12%,*,8%">
  			<frame noresize scrolling="no" src="top.jsp">
  			<frameset border="0" cols="15%,*">
  				<%
  					int role=new UserDao().getUserByMail((String)session.getAttribute("umail")).getUrole();
  				  					if(role==1){
  				%>
  				<frame noresize scrolling="no" src="main_left_1.jsp"><%} else if(role==2){ %>
  				<frame noresize scrolling="no" src="main_left_2.jsp"><%} else if(role==3){ %>
  				<frame noresize scrolling="no" src="main_left_3.jsp"><%} %>
  				<frame noresize scrolling="no" src="main_right.jsp" name="main_right">
  			</frameset>
  			<frame noresize scrolling="no" src="footer.jsp">
  		</frameset>
</html>
