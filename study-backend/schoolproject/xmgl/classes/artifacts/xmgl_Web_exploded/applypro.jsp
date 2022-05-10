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
  <div class="panel panel-default">
		<div class="panel-heading">申请项目</div>
		<div class="panel-body">
			<form class="form-horizontal" role="form" method="post" action="doapply.jsp">
			<div class="form-group">
				<label for="signup-username" class="col-sm-2 control-label">项目名称:</label>
				<div class="col-sm-10">
					<input type="text" id="p_title" class="form-control f-input ipttxt" size="30"  name="p_title"  placeholder="3-15个字符，一个汉字为两个字符">
				</div>
			</div>
			<div class="form-group">
				<label for="signup-username" class="col-sm-2 control-label">小组成员:</label>
				<div class="col-sm-10">
					<div class="input-group">
					  <span class="input-group-addon" id="basic-addon1">成员1：</span>
					  <input type="text" class="form-control" name="p_stu1"  aria-describedby="basic-addon2">
					  <span class="input-group-addon" id="basic-addon1">成员2：</span>
					  <input type="text" class="form-control"  name="p_stu2" aria-describedby="basic-addon2">
					  <span class="input-group-addon" id="basic-addon1">成员3：</span>
					  <input type="text" class="form-control" name="p_stu3" aria-describedby="basic-addon2">
					  <span class="input-group-addon" id="basic-addon1">成员4：</span>
					  <input type="text" class="form-control"  name="p_stu4" aria-describedby="basic-addon2">
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="signup-username" class="col-sm-2 control-label">项目简介(200字以内):</label>
				<div class="col-sm-10">
					<textarea class="form-control" rows="6" name="p_introduce"></textarea>
				</div>
			</div>
			<div class="form-group" style="margin-left: 180px; margin-right: 20px;">
<button class="btn btn-default" type="submit" style="margin-right: 20px;">发布</button>
<button type="reset" class="btn btn-default">重置</button>
</div>
				
				
			</form>	
			







		</div>
	</div>	

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>