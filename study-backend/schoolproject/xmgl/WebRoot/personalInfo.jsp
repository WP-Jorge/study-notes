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
    <script type="text/javascript">
			function update(){
				document.getElementById("info").style.display="none";
				document.getElementById("updateInfo").style.display="";
			}
			function cancleUpdate(){
				document.getElementById("info").style.display="";
				document.getElementById("updateInfo").style.display="none";
				document.getElementById("updatePwd").style.display="none";
			}
			function updatePwd(){
				document.getElementById("info").style.display="none";
				document.getElementById("updatePwd").style.display="";
			}
			
function callback(msg)   
{   
    //document.getElementById("oldpwd").outerHTML = document.getElementById("oldpwd").outerHTML;   
  //  document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";   
    alert(msg);
}
function clearmsg(){
	 document.getElementById("msg").innerHTML = "";
}
</script>
					
  </head>
  <body>
    
    
    
  				  <% 
  					UserDao ud = new UserDao(); 
  					User u=ud.getUserByMail((String)session.getAttribute("umail"));  							 
  				 %>
		
		<div id="info" style="">
			<div class="panel panel-default">
			  <div class="panel-heading">个人资料</div>
			  <div class="panel-body">
			    姓名：<%=u.getUrealname()%>
			  </div>
			  <div class="panel-body">
			   邮箱：<%=u.getUmail()%>
			  </div>
			  <div class="panel-body">
			    手机号码：<%=u.getUphone()%>
			  </div>
			  <div class="panel-body">
			   QQ帐号：<%=u.getUqq()%>
			  </div>
			  <div class="panel-body">
				<button type="button" onclick="update()" class="btn btn-default">修改个人资料</button>
			 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" onclick="updatePwd()" class="btn btn-default">修改密码</button>
			  </div>
			</div>			
		</div>
		
			
				
		<div id="updateInfo" style="display:none">
			<div class="panel panel-default">
			  <div class="panel-heading">修改个人资料</div>
				<form name="form1" class="form-horizontal" role="form" action="doUpdatePersonalInfo.jsp" method="post">
					<div class="form-group">
						<div class="panel-body">
						<label for="signup-username" class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-10">
							<%=u.getUmail()%>
						</div>
						</div>
					</div>
					<div class="form-group">
						<div class="panel-body" >
						<label for="signup-username" class="col-sm-2 control-label">姓名:</label>
						<div class="col-sm-10">
							<input type="text" id="realname" name="realname" class="form-control f-input ipttxt" size="30"  value="<%=u.getUrealname()%>" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="panel-body" >
						<label for="signup-username" class="col-sm-2 control-label">手机:</label>
						<div class="col-sm-10">
							<input type="text" id="phone" name="phone" value="<%=u.getUphone()%>" class="form-control f-input ipttxt" size="30" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
						</div>
					</div>
					
					<div class="form-group">
						<div class="panel-body" >
						<label for="signup-username" class="col-sm-2 control-label">QQ帐号:</label>
						<div class="col-sm-10">
							<input type="text" id="qq" name="qq" value="<%=u.getUqq()%>" class="form-control f-input ipttxt" size="30" placeholder="3-15个字符，一个汉字为两个字符">
						</div>
						</div>
					</div>
					<div class="form-group">
						<div class="panel-body" style="margin-left: 120px;">
							
							<!--  <button type="submit" class="btn btn-default">确认修改</button> -->
							 <button type="button" onclick="checkform1()" class="btn btn-default">确认修改</button>
							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" onclick="cancleUpdate()" class="btn btn-default">返回</button>
							
						<br></div>
					</div>
				</form>	
			</div>			
		</div>		
				
			
						<script>
						function checkform1(){
								var phone=document.getElementById("phone").value;
								var tel = 18767802354;
									 var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
									 if (reg.test(phone)) {
									      document.form1.submit();
									 }else{
									      alert("*号码有误");
									 };
							}
						function checkpwdform(){
							var msg="";
							var oldpwd=document.getElementById("oldpwd").value;
							var newpwd=document.getElementById("newpwd").value;
							var renewpwd=document.getElementById("renewpwd").value;
							var reg = /^[\w]{6,16}$/;
							if(!(reg.test(oldpwd)))msg+="*6-12位字母、数字";
							if(!(reg.test(newpwd)))msg+="\n*6-12位字母、数字";
							if(!(reg.test(renewpwd)))msg+="\n*6-12位字母、数字";
							if(newpwd!=renewpwd)msg+="\n*两次密码不一致";
							if(msg==""){
							document.pwdform.submit();
							}else{alert(msg);}
						}
							</script>
			
			
			
			
			
			
			<div id="updatePwd" style="display:none">
			<div class="panel panel-default">
			  <div class="panel-heading">修改密码</div>
				<form id="pwdform" name="pwdform" class="form-horizontal" role="form" target="hidden_frame" action="doUpdatePwd.jsp" method="post">
					<div class="form-group">
					<div class="panel-body" >
						<label for="signup-password" class="col-sm-2 control-label">原密码:<span id="msg"></span></label>
						<div class="col-sm-10">
							<input type="password" id="oldpwd" size="30" name="oldpwd" class="form-control f-input ipttxt" placeholder="最少 6 个字符 ">
						</div>
					</div>
					</div>
					<div class="form-group">
					<div class="panel-body" >
						<label for="signup-password" class="col-sm-2 control-label">新密码:</label>
						<div class="col-sm-10">
							<input type="password" id="newpwd" size="30" name="newpwd" class="form-control f-input ipttxt" placeholder="最少 6 个字符 ">
						</div>
					</div>
					</div>
					<div class="form-group">
					<div class="panel-body" >
						<label for="signup-password" class="col-sm-2 control-label">确认密码:</label>
						<div class="col-sm-10">
							<input type="password" id="renewpwd" size="30" name="renewpwd" class="form-control f-input ipttxt" placeholder="最少 6 个字符 ">
						</div>
					</div>
					</div>
					<div class="form-group">
						<div class="panel-body" style="margin-left: 120px;">
							 <!--<button type="submit" class="btn btn-default">确认修改</button> -->
							<button type="button" onclick="checkpwdform()" class="btn btn-default">确认修改</button>
							 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" onclick="cancleUpdate()" class="btn btn-default">返回</button>
						</div>
					</div>
				</form>	
			</div>	
		</div>		
			
			
		
		<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe> 
    
    
    
    
    
    
    
    
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>