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
  		int p_status=Integer.parseInt(request.getParameter("pstatus").toString().trim());
  		int p_id=Integer.parseInt(request.getParameter("pid").toString().trim());
  		ProjectDao pd=new ProjectDao();
  		int row=pd.updateStatus(p_status,p_id);
  		int location=0;
  		if(row==1){  			
  			if(p_status==1||p_status==2||p_status==3)location=1;
  			if(p_status==4||p_status==5||p_status==8||p_status==7)location=4;
  			if(p_status==5||p_status==6||p_status==7)location=6;
  			//expertcheck.jsp?p_status= 
  			response.sendRedirect("expertcheck.jsp?p_status="+location);
   		%>
   
		
		<%
			} else {
		%>
		<jsp:forward page="error.jsp"></jsp:forward>
		<%
			}
		%>
  </body>
</html>
