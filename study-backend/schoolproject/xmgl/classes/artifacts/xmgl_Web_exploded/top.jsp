<%@ page language="java" import="dao.*,java.util.*,entity.*" pageEncoding="utf-8"%>
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
    
    
    
    <nav class="navbar navbar-inverse navbar-fixed-top" style="border-bottom-color:#fff; background-color:#EEEEEE;">
      <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 10px; ">          
          <!-- <b style="font-size:20px;" class="navbar-brand">学生创新项目管理系统</b><br /> -->
          <h2 style="">学生创新项目管理系统</h2>
          <span style="font-size:12px;">欢迎你，<%
          	UserDao ud=new UserDao();
            						out.print(ud.getUserByMail((String)session.getAttribute("umail")).getUrealname());
          %></span>
        </div>
        <div style="display:block">
        	<%NoticeDao nd=new NoticeDao();
				Notice n=nd.getNewNotice();%> 
		   <marquee class="easyui-tooltip" style="margin-left: 200px; margin-right: 200px;">
		   		<b>公告消息：</b><%=n.getNcontents()%>
		   </marquee>
        </div>       
      </div>
    </nav>  
    
    
      
   			

    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>