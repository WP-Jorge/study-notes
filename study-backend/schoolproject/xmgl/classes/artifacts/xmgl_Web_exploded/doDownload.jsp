<%@ page contentType="text/html; charset=utf-8" language="java" 
import="java.util.*,com.jspsmart.upload.*,dao.*" errorPage="" %>
<html>
<head>
<title>文件上传处理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
<%
	int pid=Integer.parseInt(request.getParameter("pid").toString().trim());
	int fileType=Integer.parseInt(request.getParameter("fileType").toString().trim());
	String filename=null;
	if(fileType==0)
		filename=new ProjectDao().getProById(pid).getPapplicationStuff();
	else
		filename=new ProjectDao().getProById(pid).getPoverStuff();
	SmartUpload su = new SmartUpload();
		// 初始化
	su.initialize(pageContext);
		// 设定contentDisposition为null以禁止浏览器自动打开文件，
		//保证点击链接后是下载文件。若不设定，则下载的文件扩展名为
		//doc时，浏览器将自动用word打开它。扩展名为pdf时，
		//浏览器将用acrobat打开。
	su.setContentDisposition(null);
		// 下载文件
		try{
		su.downloadFile("/uploadFile/"+filename);
	}catch(Exception e){out.print("文件不存在");}
	out.clear();
	out = pageContext.pushBody();
%>
</body>
</html>