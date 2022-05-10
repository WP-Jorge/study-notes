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
  			int pid=Integer.parseInt(request.getParameter("pid"));
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
			  	指导教师：<%=teacher%>
			  </div>
			  
			  <div class="panel-body">
			  小组成员：<%=p.getPstu1()%>、<%=p.getPstu2()%>、<%=p.getPstu3()%>、<%=p.getPstu4()%>
			  </div>
			  
			  <div class="panel-body">
			  项目简介：<p><%=p.getPintroduce()%></p>
			  </div>
			  
			  
			  
			  <div class="panel-body">
			   	立项申请材料：
			   	<%
			  			  			  			  	if(p.getPapplicationStuff()==null||p.getPapplicationStuff().length()==0){
			  			  			  			  %>			   	
			   	<b style="color:red">未上传材料</b>
			  	<%
			  		}else{
			  	%>
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=0">点击下载</a>
			  	<%
			  		}
			  	%>
			  </div>
			  
			  
			  <div class="panel-body">
			   	结题材料：
			   	<%
			  			  			  	if(p.getPoverStuff()==null||p.getPoverStuff().length()==0||p.getPstatus()==8){
			  			  			  %>			   	
			   <b style="color:red">未上传材料</b>
			  	<%
			  		}else{
			  	%>
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=1">点击下载</a>
			  	<%
			  		}
			  	%>
			  </div>
			  
			  
			  
			  
			  
			  <div class="panel-body">
					分配专家：
					<script>
		  		 		function show(){
		  		 			document.getElementById("p_e_form").style.display="";
		  		 			document.getElementById("expert_div").style.display="none";
		  		 		}
	  		 		</script>
	  		 		<span id="expert_div"><%=p.getExpertName()%>
	  		 		<%
	  		 			if(!(p.getPexpert()!=0))out.print("<button type='button' onclick='show()' class='btn btn-default'>分配评审专家</button>");
	  		 		%>
	  		 		
	  		 		</span>
	  		 		
	  		 		
	  		 		<form class="form-inline" id="p_e_form" style="display:none" method="post" action="dopointexpert.jsp?pid=<%=pid%>"><!--dopointexpert.jsp checkproject.jsp -->
	  		 			<div class="form-group" style="float:left;">
	  		 			<select name="expert_u_id" class="form-control">
	  		 			<%
	  		 				List<User> experts=new UserDao().listAllExpert();
	  		 				  		 			for(int i=0;i<experts.size();i++){
	  		 				  		 				User expert=experts.get(i);
	  		 			%>
	  		 				<option <%if(i==1)out.print("selected='selected'");%> value="<%=expert.getUid()%>"><%=expert.getUrealname()%></option>
	  		 			<%} %>
	  		 			</select>	  		 				 			
	  		 			</div>
	  		 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  		 			<button type="submit" class="btn btn-default">确定</button>	
	  		 		</form>
	  		 		
	  		 		
			  </div>
			  <div class="panel-body">
				  项目状态：<%=p.getPsTitle()%>
			  </div>
			  <div class="panel-body">
			  	<button type="button" onclick="javascript:window.location.href='projectmanager.jsp';" class="btn btn-default">返回</button>
			  </div>
			</div>	
			
	
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>