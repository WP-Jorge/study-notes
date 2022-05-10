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
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
   <jsp:include page="islogin.jsp"></jsp:include>
  <%
  	request.setCharacterEncoding("utf-8");
    		String mail=request.getParameter("umail");
    		User u=new UserDao().getUserByMail(mail);
    		int ur_id=u.getUrole();
  %>
  <body>
<div class="panel panel-default">
			  <div class="panel-heading">新增用户</div>
			  	<div class="panel-body">
<form method="post"  action="doadduser.jsp">
  <div class="form-group">
    <label for="exampleInputEmail1" class="col-sm-2 control-label">邮箱：</label>
    <div style="width:100%"  class="col-sm-10">
    	<input type="email" class="form-control" name="u_mail" id="exampleInputEmail1" placeholder="xxx@cslg.cn">
  	</div>
  </div>
  <div  class="form-group">
    <label for="exampleInputPassword1" class="col-sm-2 control-label">密码：</label>
    <div  style="width:100%" class="col-sm-10">
    	<input type="password" class="form-control" name="u_password" id="exampleInputPassword1" placeholder="最少 6 个字符 ">
  	</div>
  </div>
  <div class="form-group">
	<label for="signup-password" class="col-sm-2 control-label">密码确认:</label>
	<div  style="width:100%" class="col-sm-10">
		<input type="password" id="signup-password-confirm" size="30" name="repassword" class="form-control f-input ipttxt" placeholder="两次密码要一致">
	</div>
  </div>
  <div  class="form-group">
	<label for="signup-username" class="col-sm-2 control-label">姓名:</label>
	<div  style="width:100%"  class="col-sm-10">
		<input type="text" id="signup-username" name="u_realname" class="form-control f-input ipttxt" size="30"  placeholder="3-15个字符，一个汉字为两个字符">
	</div>
  </div>
  <div class="form-group">
	<label for="signup-username" class="col-sm-2 control-label">手机：</label>
	<div  style="width:100%"  class="col-sm-10">
		<input type="text" id="signup-username" name="u_phone" class="form-control f-input ipttxt" size="30"  placeholder="11个数字字符">
	</div>
  </div>
  <div class="form-group">
	<label for="signup-username" class="col-sm-2 control-label">QQ:</label>
	<div  style="width:100%"  class="col-sm-10">
		<input type="text" id="signup-username" name="u_qq" class="form-control f-input ipttxt" size="30" placeholder="QQ号码确保正确">
	</div>
  </div>
  <div class="form-group">
	<label for="signup-username" class="col-sm-2 control-label">角色:</label>
	<div  style="width:100%"  class="col-sm-10">
	
	
		
  		
  		<%RoleDao rd=new RoleDao();
  				List<Role> rlist=new ArrayList<Role>();
  				rlist=rd.listAll();
  				for(int i=0;i<rlist.size();i++){
 				Role role=new Role();
 				role=(Role)rlist.get(i); %>
  			<input type="radio" name="u_role" <%if(role.getRid()==ur_id)out.print("checked='checked'");%> value="<%=role.getRid()%>" /><%=role.getRtitle()%>&nbsp;&nbsp;&nbsp;&nbsp;
  			<%} %>
  		
  		
	</div>
  </div>
  <div class="form-group">
	<label for="signup-username" class="col-sm-2 control-label">账户状态:</label>
	<div  style="width:100%"  class="col-sm-10">
			<input name="u_status" type="radio" <%if(u.getUstatus()==1)out.print("checked='checked'");%> checked="checked" value="1"/>有效
  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  			<input name="u_status" type="radio" <%if(u.getUstatus()==0)out.print("checked='checked'");%> value="0"/>无效
  				
	</div>
  </div>
  <div class="panel-body"></div>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <button type="submit" class="btn btn-default">确认添加</button>
  &nbsp;&nbsp;&nbsp;&nbsp;
  <button type="button" onclick="javascript:self.location='usermanager.jsp'" class="btn btn-default">返回</button>
</form>
    
 </div>
 </div>
    
   
    
    
    
    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </body>
</html>