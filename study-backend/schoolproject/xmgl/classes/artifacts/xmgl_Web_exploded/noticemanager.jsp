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
    

	<div class="panel panel-default">
			<div class="panel-heading">
					<a class="btn btn-default" href="addnotice.jsp" role="button">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;发布公告</a>
			</div>
			 <div class="panel-body">
	
	
	
  		<div class="container-fluid">
      		<div class="row">
      			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" style="margin-left: 0px;width:100%">
		  
          <div class="table-responsive">
            <table class="table table-hover">
              <thead>
                <tr>
                  <th>#</th>
                  <th>标题</th>
                  <th>内容</th>
                  <th>发布日期</th>
                  <th>发布人</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
               <%
               	NoticeDao nd=new NoticeDao();
                                			UserDao ud=new UserDao();
                              	List<Notice> nlist=new ArrayList<Notice>();
                              	nlist=nd.listAll();
                              	int size=nlist.size();
                              	for(int i=0;i<size;i++){
                              		Notice n=nlist.get(i);
                              		User u=ud.getUserById(n.getNbywho());
                              		String nc=n.getNcontents();
                              		if(nc.length()>20)nc=nc.substring(0,14)+"******";
               %>
  		 	<tr>
  				<td><%=n.getNid()%></td>
  				<td><%=n.getNtitle()%></td>
  				<td><%=nc%></td>
  				<td><%=n.getNdate()%></td>
  				<td><%=u.getUrealname()%></td>
  				<td>
  				<!-- <input type="button" value="删除" onClick="javascript:if(confirm('确认删除？'))window.location.href='dodeletenotice.jsp?n_id='" />-->
  				<button type="button" onClick="javascript:if(confirm('确认删除？'))window.location.href='dodeletenotice.jsp?n_id=<%=n.getNid()%>'" class="btn btn-default">删除</button>
  				</td>
  			</tr>
  			<%} %>        
              </tbody>
            </table>   
            <%NoticeDao auto=new NoticeDao();
  		int all=auto.getCount();
  		if(all>5){
  			out.print("<b style='color:red'>过期记录5条，请删除</b>");
  		}
  		%>         
          </div>  
        </div>
      </div>
    </div>
      

</div>
</div>
    

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>