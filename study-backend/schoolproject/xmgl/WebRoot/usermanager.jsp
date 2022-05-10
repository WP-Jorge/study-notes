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
  <%
    request.setCharacterEncoding("utf-8");
    int pageSize=10;//每页显示条数
    UserDao ud=new UserDao();
	int totalCount=ud.getCount();//总共条数

	int totalpages=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
	String p =request.getParameter("page");//获取页码参数
	int np = 1;//np为当前页，表示页面初次打开时当前页为第1页
	if (p != null && !p.equals("")) 
	np = Integer.parseInt(p);//从请求参数中获取当前页的值
	if (np > totalpages)//当到达最后页，再向下翻页时，当前页仍为最后页
		np = totalpages;
	else if (np < 1)//当前页为第一页，在向上翻页时，当前页仍为第一页
		np = 1;
	//mlist = mbiz.turnPage(np);//获得当前页显示的数据行

	//int currentPage=1;//当前页
	//int currentStart;
	%>
  <body>    
    
	
	
	
	
	
  	<div class="panel panel-default">
			<div class="panel-heading">
				<a class="btn btn-default" href="adduser.jsp" role="button">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp;&nbsp;添加用户</a>
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
                  <th>用户角色</th>
                  <th>真实姓名</th>
                  <th>手机</th>
                  <th>QQ</th>
                  <th>账户状态</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                <%
                	List<User> mlist = new ArrayList<User>();
                			mlist=ud.listByPage(np);
                			 for(int i=0;i<mlist.size();i++)
                 						{
                		 		User user=new User();
                		 		user=(User)mlist.get(i); 
                		 		String umail=user.getUmail();
                %>
				  			<tr>
				  				<td><%=user.getUid()%></td>
				  				<td><%=user.getRtitle()%></td>
				  				<td><%=user.getUrealname()%></td>
				  				<td><%=user.getUphone()%></td>
				  				<td><%=user.getUqq()%></td>
				  				<td><%=user.getStitle()%></td>
				  				<td><a href="editUser.jsp?umail=<%=umail %>">编辑</a></td>
				  			</tr>
				  	 <%} %>            
              </tbody>
            </table>
            <center>
            <form action="usermanager.jsp" method="post" name="form2">
 <table>
 <tr>  
 <td>共<%out.print(totalCount);%>条记录,共<%out.print(totalpages);%> 页，当前是第<%out.print(np);%>  页</td> 
 <td> 
     <a href="usermanager.jsp?page=<%=np - 1%>">上一页&nbsp; </a> 
     <a href="usermanager.jsp?page=<%=np + 1%>">下一页&nbsp;  </a> 
     <a href="usermanager.jsp?page=1">首页 &nbsp;</a> 
     <a href="usermanager.jsp?page=<%=totalpages%>">尾页  </a> 
     <!-- <input type="submit" name="Submit" value="G0"> --> 
     <button type="button" onclick="javascript:document.form2.submit();" class="btn btn-xs btn-info">GO</button>
     <input type="text" name="page" size="4"> <span>页</span></td>      
     </tr> 
     </table>
   </form>
   </center>
   
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