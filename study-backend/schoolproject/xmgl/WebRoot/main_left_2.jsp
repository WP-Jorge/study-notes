<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
     		<a href="main_right.jsp" class="list-group-item" target="main_right">
            <span class="glyphicon glyphicon-bell" aria-hidden="true"></span>&nbsp;&nbsp;提醒通知</a>
            <a href="personalInfo.jsp" class="list-group-item" target="main_right">
             <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;个人信息</a>
  	<a class="list-group-item"  href="expertcheck.jsp?p_status=1" target="main_right">
  	<span class="glyphicon glyphicon-flag" aria-hidden="true"></span>&nbsp;&nbsp;待立项评审</a>
  	<a class="list-group-item" href="expertcheck.jsp?p_status=4" target="main_right">
  	<span class="glyphicon glyphicon-tag" aria-hidden="true"></span>&nbsp;&nbsp;待结题评审</a>
  	
  	<a class="list-group-item" href="expertcheck.jsp?p_status=6" target="main_right">
  	<span class="glyphicon glyphicon-tags" aria-hidden="true"></span>&nbsp;&nbsp;延期待结题</a>
			 <a href="exit.jsp" class="list-group-item" target="_top">
             <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbsp;&nbsp;退出系统</a>    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>