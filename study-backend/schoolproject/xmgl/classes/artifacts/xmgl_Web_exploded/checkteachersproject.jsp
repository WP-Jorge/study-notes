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
			   	<%
			  			  			  	if(p.getPapplicationStuff()==null||p.getPapplicationStuff().length()==0){
			  			  			  %>			   	
			   	<button id="btn_show_up" type="button" onclick="showUploadDiv()" class="btn btn-default">上传立项材料(压缩文件zip,rar,rpm......)</button>
			  	<%}else{ %>
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=0">点击下载</a>
			  	<%} %>			  			
			  </div>
			  
			  
			  
			  
			  
			  
			  <%
			  			  			  			  			  			  			  	if(p.getPstatus()==2||p.getPstatus()==4||p.getPstatus()==5||p.getPstatus()==6||p.getPstatus()==8) {
			  			  			  			  			  			  			  %>
			  <div class="panel-body">
			   	结题材料：
			   	<%
			  	if(p.getPoverStuff()==null||p.getPoverStuff().length()==0||p.getPstatus()==8){
			  %>			   	
			   	<button id="btn_show_down" type="button" onclick="showDownloadDiv()" class="btn btn-default">上传结题材料(压缩文件zip,rar,rpm......)</button>
			  	<%}else{ %>
			  	<a href="doDownload.jsp?pid=<%=pid%>&&fileType=1">点击下载</a>
			  	<%} %>
			  </div>
			  <%} %>
			  
			  
			  <div class="panel-body">
			  <div style="margin-left:100px;">
			  		<div style="display:none;" id="uploadfile_div">
						<form class="form-inline" method="post" action="doUpload.jsp" enctype="multipart/form-data" target="hidden_frame">
						  <input type="hidden" name="pid" value="<%=pid %>">
						  <input type="hidden" name="filetype" value=0><!-- 0标识申请文件 -->
						  
						  <div class="form-group">
						     <input type="file" name="file1" id="file1" size="40" ><!--<span id="msg"></span> -->
						   </div>
						    <div class="form-group">
						    <button type="submit" name="submit" class="btn btn-default">上传</button>
						  </div>  
						    
						</form>
						<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe> 
						<!-- out.println("<script>parent.callback('上传成功')</script>"); -->
						</div>
			  
			  
			  		<div style="display:none;" id="downloadfile_div">
						<form class="form-inline" method="post" action="doUpload.jsp" enctype="multipart/form-data" target="hidden_frame1">
						  <input type="hidden" name="pid" value="<%=pid %>">
						  <input type="hidden" name="filetype" value=1><!-- 0标识申请文件 -->
						 
						 	 <div class="form-group">
						     <input type="file" name="file1" id="file1" size="40" >
						   </div>
						    <div class="form-group">
						    <button type="submit" name="submit" class="btn btn-default">上传</button>
						  </div>  
						       
						</form>
						
						<iframe name="hidden_frame1" id="hidden_frame1" style="display:none"></iframe> 
						<!-- out.println("<script>parent.callback('上传成功')</script>"); -->
						
						</div>
						 <span id="msg" ></span>
			  </div>
			  </div>
			 
			  
			  
			  
			  <div class="panel-body">
					分配专家：<%=p.getExpertName() %>
			  </div>
			  <div class="panel-body">
				  项目状态：<%=p.getPsTitle()%>
			  </div>
			  <div class="panel-body">
			  	<button type="button" onClick="javascript:if(confirm('确认删除？'))window.location.href='dodelproject.jsp?pid=<%=pid %>'" class="btn btn-default">删除</button>
			  	<button type="button" onclick="javascript:window.location.href='teacherspro.jsp';" class="btn btn-default">返回</button>
			  </div>
			</div>	






<script>
function showUploadDiv(){
	document.getElementById("btn_show_up").style.display="none";
	document.getElementById("uploadfile_div").style.display="";
}
function showDownloadDiv(){
	document.getElementById("btn_show_down").style.display="none";
	document.getElementById("downloadfile_div").style.display="";
}
function callback(msg)   
{   
    //document.getElementById("oldpwd").outerHTML = document.getElementById("oldpwd").outerHTML;   
    document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";   
    document.getElementById("file1").readonly="readonly";
    document.getElementById("filename").readonly="readonly";
}
</script>


















    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>