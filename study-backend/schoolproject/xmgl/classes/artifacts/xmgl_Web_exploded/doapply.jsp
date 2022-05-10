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
  	  	  	  	  	  	  	  	  		String p_title=request.getParameter("p_title");
  	  	  	  	  	  	  	  	  		String p_introduce=request.getParameter("p_introduce");
  	  	  	  	  	  	  	  	  		String p_stu1=request.getParameter("p_stu1");
  	  	  	  	  	  	  	  	  		String p_stu2=request.getParameter("p_stu2");
  	  	  	  	  	  	  	  	  		String p_stu3=request.getParameter("p_stu3");
  	  	  	  	  	  	  	  	  		String p_stu4=request.getParameter("p_stu4");
  	  	  	  	  	  	  	  	  		//int p_status=1;
  	  	  	  	  	  	  	  	  		int p_teacher=new UserDao().getUserByMail((String)session.getAttribute("umail")).getUid();
  	  	  	  	  	  	  	  	  		ProjectDao pd=new ProjectDao();
  	  	  	  	  	  	  	  	  		Project p=new Project();
  	  	  	  	  	  	  	  	  		p.setPintroduce(p_introduce);
  	  	  	  	  	  	  	  	  		p.setPtitle(p_title);
  	  	  	  	  	  	  	  	  		p.setPteacher(p_teacher);
  	  	  	  	  	  	  	  	  		//p.setP_status(p_status);
  	  	  	  	  	  	  	  	  		p.setPstu1(p_stu1);
  	  	  	  	  	  	  	  	  		p.setPstu2(p_stu2);
  	  	  	  	  	  	  	  	  		p.setPstu3(p_stu3);
  	  	  	  	  	  	  	  	  		p.setPstu4(p_stu4);
  	  	  	  	  	  	  	  	  		int row=pd.add(p);
  	  	  	  	  	  	  	  	  		if(row==1){
  	%>
  	 <jsp:forward page="teacherspro.jsp"></jsp:forward>
		<% }else {%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%} %>
  </body>
</html>
