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
  		
  			int id=Integer.parseInt(request.getParameter("n_id"));
  			NoticeDao nd=new NoticeDao();
  			int row=nd.deleteNotice(id);
  			if(row==1){		
				//out.println("<script>parent.callback('成功删除')</script>");
				//} else{out.println("<script>parent.callback('删除失败')</script>");}
  		%>
		<jsp:forward page="noticemanager.jsp"></jsp:forward>
		<% }else {%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%}%>
  </body>
</html>
