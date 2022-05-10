<%@ page language="java" import="java.util.*,dao.*,entity.*" pageEncoding="utf-8"%>
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
  <jsp:include page="islogin.jsp"></jsp:include>
  		<%
  			int pid=Integer.parseInt(request.getParameter("pid").toString().trim());
  		  		  			int pstatus=Integer.parseInt(request.getParameter("pstatus").toString().trim());//1,4,6
  		  		  			ProjectDao pd=new ProjectDao();
  		  		  			Project p=pd.getProById(pid);
  		  		  			String teacher=new UserDao().getUserById(p.getPteacher()).getUrealname();
  		%>
		<div class="panel panel-default">
			  <div class="panel-heading">项目详情</div>
			  <div class="panel-body">
			   项目名称：<%=p.getPtitle()%>
			  </div>
			  
			  <div class="panel-body">
			  	指导教师：<%=teacher %>
			  </div>
			  
			  <div class="panel-body">
			  小组成员：<%=p.getPstu1()%>、<%=p.getPstu2()%>、<%=p.getPstu3()%>、<%=p.getPstu4()%>
			  </div>
			  
			  <div class="panel-body">
			  项目简介：<p><%=p.getPintroduce()%></p>
			  </div>
			  
			  
			  
			  
			  
			  <div class="panel-body">
			   	立项申请材料：
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=0">点击下载</a>
			  </div>
			  <%
			  	if(p.getPstatus()==4||p.getPstatus()==6) {
			  %>
			  <div class="panel-body">
			   	结题材料：
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=1">点击下载</a>
			  </div>
			  <%}%>		  
			  
			  
			  
			  
			  
			  
			  <div class="panel-body">
	<button id="btn_check" type="button" onclick="show(<%=pstatus %>)" class="btn btn-default">审核(确定后不可修改)</button>
	<script>
	  		 	function show(pstatus){//146
	  		 		if(pstatus==1){
	  		 			document.getElementById("check_apply_form").style.display="";
	  		 		}
	  		 		if(pstatus==4){
	  		 			document.getElementById("check_over_form").style.display="";
	  		 		}
	  		 		if(pstatus==6){
	  		 			document.getElementById("check_delay_form").style.display="";	  		 		
	  		 		}
	  		 		document.getElementById("btn_check").style.display="none";
	  		 	}
	  		 </script>
			 <form class="form-inline" style="display:none" id="check_apply_form" method="post" action="doupdateProStatus.jsp">
		  		 <input class="form-control" type="hidden" value="<%=pid %>" name="pid" />
		  		 <div class="form-group">
		  		 <div class="radio">
				  <label>
				    <input type="radio" name="pstatus" id="pstatus" value="2" >
				    <b style="color:green;">允许立项</b>
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="pstatus" id="pstatus" value="3" >
				   <b style="color:red;"> 不予立项</b>
				  </label>
				</div>						 
		  		 </div>
		  		 
		  		 	<button type="submit" class="btn btn-default" >确定</button>
		  		 	&nbsp;&nbsp;&nbsp;&nbsp;
		  		 	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);" >返回</button>
		  		
  		 	</form>
  		 	<form class="form-inline" style="display:none" id="check_over_form" method="post" action="doupdateProStatus.jsp">
		  		<input class="form-control" type="hidden" value="<%=pid %>" name="pid"/>
		  		<div class="form-group">
		  		 <div class="radio">
				  <label>
				    <input type="radio" name="pstatus" id="pstatus" value="5" >
				    <b style="color:green;" >允许结题</b>
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input  type="radio" name="pstatus" id="pstatus" value="8" ><!-- 准备二次结题 -->
				   <b style="color:#FF9900;" > 延期结题</b>
				  </label>
				</div>	
				<div class="radio">
				  <label>
				    <input type="radio" name="pstatus" id="pstatus" value="7" >
				    <b style="color:red;" >终止资助</b>
				  </label>
				</div>					 
		  		 </div>
		  		 	<button type="submit" class="btn btn-default" >确定</button>
		  		 	&nbsp;&nbsp;&nbsp;&nbsp;
		  		 	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);" >返回</button>
  		 	</form>
  		 	<form class="form-inline" style="display:none" id="check_delay_form" method="post" action="doupdateProStatus.jsp">
		  		<input class="form-control" type="hidden" value="<%=pid %>" name="pid"/>
		  		 <div class="form-group">
		  		  <div class="radio">
				  <label>
				    <input  type="radio" name="pstatus" id="pstatus" value="5" >
				  <b style="color:green;"> 允许结题</b>
				  </label>
				</div>
				<div class="radio">
				  <label>
				    <input type="radio" name="pstatus" id="pstatus" value="7" >
				    <b style="color:red;" >终止资助</b>
				  </label>
				</div>					 
		  		 </div>
		  		 	<button type="submit" class="btn btn-default" >确定</button>
		  		 	&nbsp;&nbsp;&nbsp;&nbsp;
		  		 	<button type="button" class="btn btn-default" onclick="javascript:history.go(-1);" >返回</button>
  		 	</form>
</div>	
		
	</div>
  		 	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>