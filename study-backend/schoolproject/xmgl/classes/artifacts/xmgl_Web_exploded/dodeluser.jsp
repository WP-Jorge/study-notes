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
  		int uid=Integer.parseInt(request.getParameter("uid").toString().trim());
  		int count=new ProjectDao().getCountByUid(uid);
  		if(count!=0){
  			out.println("有"+count+"个项目与该用户有关，不能删除<button class='btn btn-default' type='button' onclick='javascript:history.go(-1)'>返回</button>");
  			//out.println("<script>parent.callback('msg');</script>");
  		}else{
  			int row=new UserDao().deleteUser(uid);
  			if(row==1){
  				%>
		<jsp:forward page="usermanager.jsp?msg=1"></jsp:forward>
		<% }else {%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%}
  			
  		}
  	 %>
  </body>
</html>
