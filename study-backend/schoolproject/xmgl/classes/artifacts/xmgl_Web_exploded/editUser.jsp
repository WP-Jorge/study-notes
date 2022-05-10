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
  <jsp:include page="islogin.jsp"></jsp:include>
  <%
  	request.setCharacterEncoding("utf-8");
    		String mail=request.getParameter("umail");
    		User u=new UserDao().getUserByMail(mail);
    		int ur_id=u.getUrole();
  %>
  <body>
   <div class="panel panel-default">
			  <div class="panel-heading">
			  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp;&nbsp;修改用户资料</div>
			  <div class="panel-body">
				<form class="form-horizontal" role="form" method="post" action="doeditUser.jsp">
					<input type="hidden" value="<%=u.getUid()%>" name="id"/>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-10">
							<input type="email" id="signup-username" class="form-control f-input ipttxt" size="30"  name="mail" value="<%=u.getUmail()%>" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="exampleInputPassword1" class="col-sm-2 control-label">密码：</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name="pwd" value="<%=u.getUpassword()%>" id="exampleInputPassword1" placeholder="Password">
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">姓名:</label>
						<div class="col-sm-10">
							<input type="text" id="signup-username" class="form-control f-input ipttxt" size="30"  name="realname" value="<%=u.getUrealname()%>" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">手机:</label>
						<div class="col-sm-10">
							<input type="text" id="signup-username" name="phone" value="<%=u.getUphone()%>" class="form-control f-input ipttxt" size="30" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">QQ帐号:</label>
						<div class="col-sm-10">
							<input type="text" id="signup-username" name="qq" value="<%=u.getUqq()%>" class="form-control f-input ipttxt" size="30" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">角色:</label>
						<div class="col-sm-10">
							<select name="role">
							<%
								RoleDao rd=new RoleDao();
							  							List<Role> rlist=new ArrayList<Role>();
							  							rlist=rd.listAll();
							  					 		for(int i=0;i<rlist.size();i++){
							 								Role role=new Role();
							 								role=(Role)rlist.get(i);
							%>
  						<option <%if(role.getRid()==ur_id)out.print("selected='selected'");%> value="<%=role.getRid()%>"><%=role.getRtitle()%></option>
  						<%
  							}
  						%>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						
						<label for="signup-username" class="col-sm-2 control-label">账户状态:</label>
						<div class="col-sm-10">
							<input name="status" type="radio" <%if(u.getUstatus()==1)out.print("checked='checked'");%> checked="checked" value="1"/>有效
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input name="status" type="radio" <%if(u.getUstatus()==0)out.print("checked='checked'");%> value="0"/>无效
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" type="button" onClick="javascript:if(confirm('确认删除？'))self.location='dodeluser.jsp?uid=<%=u.getUid()%>';">删除该用户</button>
						</div>
					</div>
					
						
							 <div class="form-group" style="margin-left: 180px;">
<button class="btn btn-default" type="submit" style="margin-right: 20px;">确认修改</button>
<button class="btn btn-default" type="button" onclick="javascript:self.location='usermanager.jsp'">返回</button>
</div>
						
				</form>	
				</div>
			</div>	
<script>
	function callback(msg){
		alert(msg);
	}
</script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>