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
  		int u_id=Integer.parseInt(request.getParameter("expert_u_id"));
  		int p_id=Integer.parseInt(request.getParameter("pid"));
  		ProjectDao pd=new ProjectDao();
  		int row=pd.pointExpert(u_id,p_id);
  		if(row==1){
  	 %>
  	 <jsp:forward page="checkproject.jsp"></jsp:forward>
		<% }else {%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%} %>
  </body>
</html>