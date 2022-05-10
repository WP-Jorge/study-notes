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
  	String umail=(String)session.getAttribute("umail");
  	String newpwd=request.getParameter("newpwd");
  	String oldpwd=request.getParameter("oldpwd");

  	UserDao ud=new UserDao();
  	int row=0;
  	if(ud.checkLogin(umail,oldpwd)){
  		if(ud.updateUserPwd(umail,newpwd)==1){		
  			out.println("<script>parent.callback('修改成功')</script>");
  		} else{out.println("<script>parent.callback('修改失败')</script>");}
  	}else{out.println("<script>parent.callback('原密码错误')</script>");}
  	%>
  </body>
</html>
