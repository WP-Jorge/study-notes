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
  			int u_role=new UserDao().getUserByMail(session.getAttribute("umail").toString()).getUrole();
  		  		int pid=Integer.parseInt(request.getParameter("pid"));
  		  			ProjectDao pd=new ProjectDao();
  		  			int row=pd.deleteProject(pid);
  		  			if(row==1){
  			  			if(u_role==1){
  		%>
					<jsp:forward page="projectmanager.jsp"></jsp:forward>
				<%}else {%>
					<jsp:forward page="teacherspro.jsp"></jsp:forward>
				<%}
			}else {%>
			<jsp:forward page="error.jsp"></jsp:forward>
			<%}%>
  </body>
</html>
