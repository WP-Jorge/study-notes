<%@ page language="java" import="java.util.*,entity.*,dao.*" pageEncoding="utf-8"%>
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
	<jsp:include page="islogin.jsp"></jsp:include>
  </head>
  <body>
  <%
  	User u=new UserDao().getUserByMail(session.getAttribute("umail").toString());
              	int u_role_id=u.getUrole();
              	int u_id=u.getUid();
              	
              	ProjectDao pd=new ProjectDao();
              	List<Project> plist=new ArrayList<Project>();
              	plist=pd.listAll();
              	int count_need_application_stuff=0,count_need_expert_pointed=0,count_need_over_stuff=0,count_need_reOver_stuff=0;
              	int count_checkApply=0,count_need_vheckOver=0,count_need_checkReover=0;
              	
              	for(Project p:plist){
              		boolean match=p.getPteacher()==u_id;
              		boolean match1=p.getPexpert()==u_id;
              		if((p.getPapplicationStuff()==null||p.getPapplicationStuff().length()==0)&&match){
              		//没有上传申报材料的
              		count_need_application_stuff++;
              		}
              		if(p.getPexpert()==0){
              		//没有分配专家的
              		count_need_expert_pointed++;
              		}
              		if(p.getPstatus()==2&&match){
              		//没有上传结题材料的
              		count_need_over_stuff++;
              		}
              		//if(!(p.getP_over_stuff().length()==0)){
              		if(p.getPstatus()==8&&match){
              		//要重新上传材料的
              			count_need_reOver_stuff++;
              		}
              		//}
              		if(p.getPstatus()==1&&match1){
              		//待立项评审的
              		count_checkApply++;
              		}
              		if(p.getPstatus()==4&&match1){
              		//待结题评审的
              		count_need_vheckOver++;
              		}
              		if(p.getPstatus()==6&&match1){
              		//延期待评审的
              		count_need_checkReover++;
              		}
              	}
              	String notice="";
              	if(u_role_id==1){//管理员
              		if(count_need_expert_pointed>0)
              			notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='projectmanager.jsp' target='main_right'>	需要分配评审专家：*"+count_need_expert_pointed;
              		else notice+="";
              	}
              	if(u_role_id==2){//评审专家
              		if(count_checkApply>0)
              			notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='expertcheck.jsp?p_status=1' target='main_right'> 待立项评审：</a>*"+count_checkApply;
              		else notice+="";
            		if(count_need_vheckOver>0)
            	notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='expertcheck.jsp?p_status=4' target='main_right'> 待结题评审：</a>*"+count_need_vheckOver;
            		else notice+="";
            		if(count_need_checkReover>0)
            	notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='expertcheck.jsp?p_status=6' target='main_right'> 延期待评审：</a>*"+count_need_checkReover;
            		else notice+="";
              	}
              		 
              	if(u_role_id==3){//指导教师
              		if(count_need_over_stuff>0)
              			notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='teacherspro.jsp' target='main_right'> 需要上传结题材料</a>：*"+count_need_over_stuff;
              		else notice+="";
              		if(count_need_application_stuff>0)
              			notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='teacherspro.jsp' target='main_right'> 需要上传申报材料</a>：*"+count_need_application_stuff;
              		else notice+="";
              		if(count_need_reOver_stuff>0)
              			notice+="<br /><span class='glyphicon glyphicon-paperclip' aria-hidden='true'></span>&nbsp;&nbsp;<a href='teacherspro.jsp' target='main_right'> 需要重新上传结题材料</a>：*"+count_need_reOver_stuff;
              		else notice+="";
              	}
  %>
   
	  <div class="panel panel-default">
			  <div class="panel-heading">通知消息</div>
			  <div class="panel-body">
	  <p><%=notice %></p>
	  </div>
	  </div>




    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>