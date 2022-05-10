<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="dao.*,java.util.*,entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<LINK rel="shortcut icon" href="images/ms_ico.ico" >
    
    <title>创新项目管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
	<link rel="icon" href="img/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style>

		/* http://css-tricks.com/perfect-full-page-background-image/ */
		html {
			background: url(img/lgbg1.jpg) no-repeat center center fixed; 
			-webkit-background-size: cover;
			-moz-background-size: cover;
			-o-background-size: cover;
			background-size: cover;
		}

		body {
			padding-top: 20px;
			font-size: 16px;
			font-family: "Open Sans",serif;
			background: transparent;
		}

		h1 {
			font-family: "Abel", Arial, sans-serif;
			font-weight: 400;
			font-size: 40px;
		}

		/* Override B3 .panel adding a subtly transparent background */
		.panel {
			background-color: rgba(255, 255, 255, 0.5);
		}

		.margin-base-vertical {
			margin: 40px 0;
		}

	</style>	
	
	
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String msg=request.getParameter("msg");
	NoticeDao nd=new NoticeDao();
	Notice n=nd.getNewNotice();
	List<Notice> nlist=new ArrayList<Notice>();
	nlist=nd.listAll();
	int size=nlist.size();
 %>
<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 panel panel-default" style="margin-top: 150px;">

				<h1 class="margin-base-vertical">学生创新项目管理系统</h1>
				<h2 class="margin-base-vertical">登录</h2>
				
				<!--  --><form name="form1" class="form-horizontal" role="form" action="dologin.jsp" method="post">
					<div class="form-group">
						<label for="signup-email-address" class="col-sm-2 control-label">邮箱:</label>
						<div class="col-sm-10">
							<input onblur="check()" type="email" id="signup-email-address" size="30" name="mail" class="form-control f-input ipttxt" placeholder="请填写你的邮箱">
						</div>
					</div>
					<div class="form-group">
						<label for="signup-password" class="col-sm-2 control-label">密码:</label>
						<div class="col-sm-10">
							<input onblur="check()" type="password" id="signup-password" size="30" name="password" class="form-control f-input ipttxt" placeholder="请填写你的密码 ">
						</div>
					</div>			
					
					 <div class="checkbox">
			          <label>
			            <input type="checkbox" value="remember-me"> 
			            Remember me&nbsp;&nbsp;&nbsp;&nbsp;
			            <%if(msg!=null)out.println("<b style='color:red'>"+msg+"</b>");  %>	
			            <b style="color:red" id="errorMsg"></b>
			          </label>
			        </div>
					 	<button type="submit" id="signup-submit" class="btn btn-primary btn-lg btn-block">登录</button> 
						<!--<button type="button" onclick="check()" id="signup-submit" class="btn btn-primary btn-lg btn-block">登录</button>-->
				</form>
				<script>
					function check(){
						var password=document.getElementById("signup-password");
						var mail=document.getElementById("signup-email-address");
						var msg="";
						var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/; 
						if(password.value==""||){
						msg+="\n*密码有误";
						} 
						if(!(reg.test(mail.value))){
						msg+="\n*邮箱有误";
						}
						//if(msg==""){document.form1.submit();}
						//else {alert(msg);}
						if(msg=="") document.getElementById("errorMsg").innerHTML="OK...";
						else document.getElementById("errorMsg").innerHTML=msg;
					}
				</script>
				<h2 class="margin-base-vertical"></h2>
				<div class="margin-base-vertical">
					<small class="margin-base-vertical">
						<marquee>
						<b>公告消息：</b><%=n.getNcontents()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发布日期：<%=n.getNdate()%></marquee>
					</small>
				</div>
			</div><!-- //main content -->
		</div><!-- //row -->
	</div> <!-- //container -->		
			<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>

</body>
</html>