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
      int teacher_id=new UserDao().getUserByMail((String)session.getAttribute("umail")).getUid();
      int pageSize=10;//每页显示条数
      ProjectDao pd=new ProjectDao();
  	int totalCount=pd.getCount(teacher_id);//总共当前教师的项目条数

  	int totalpages=(totalCount%pageSize==0)?(totalCount/pageSize):(totalCount/pageSize+1);
  	String p =request.getParameter("page");//获取页码参数
  	int np = 1;//np为当前页，表示页面初次打开时当前页为第1页
  	if (p != null && !p.equals("")) 
  	np = Integer.parseInt(p);//从请求参数中获取当前页的值
  	if (np > totalpages)//当到达最后页，再向下翻页时，当前页仍为最后页
  		np = totalpages;
  	else if (np < 1)//当前页为第一页，在向上翻页时，当前页仍为第一页
  		np = 1;
  	//plist = mbiz.turnPage(np);//获得当前页显示的数据行

  	//int currentPage=1;//当前页
  	//int currentStart;
  %>
  <body>
  <jsp:include page="islogin.jsp"></jsp:include>
  
  <div class="panel panel-default">
			<div class="panel-heading">
				我的项目
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
                  <th>项目标题</th>
                  <th>评审专家</th>
                  <th>项目状态</th>
                  <th>查看编辑</th>
                  <th>删除</th>
                </tr>
              </thead>
              <tbody>
              <%
              	List<Project> plist = new ArrayList<Project>();
                            		plist=pd.listByPage(0,np,teacher_id);//查询0状态的项目
                            		 for(int i=0;i<plist.size();i++)
                            	 	 {
                            	 		Project pro=plist.get(i); 
                              					int pid=pro.getPid();
                              					User teacher=new UserDao().getUserById(pro.getPteacher());
              %>
  			<tr>
  				<td><%=pid %></td>
  				<td><%=pro.getPtitle()%></td>
  				<td><%=pro.getExpertName() %></td>
  				<td><%=pro.getPsTitle()%></td>
  				<td><a href="checkteachersproject.jsp?pid=<%=pid %>">
  				
  				<%
  				  					if(pro.getPapplicationStuff()==null||pro.getPapplicationStuff().length()==0)out.print("<b style='color:red'>上传立项材料</b>");
  				  				  				  				  				else if(pro.getPstatus()==2)out.print("<b style='color:#00FF00;'>上传结题材料</b>"); 
  				  				  				  				  				else if(pro.getPstatus()==8)out.print("<b style='color:#FF9900;'>重新结题材料</b>"); 
  				  				  				  				  				else out.print("<b style='color:blue'>查看编辑</b>");
  				  				%>
  				
  				</a></td>
  				<td>
  				<button type="button" class="btn btn-default" onClick="javascript:if(confirm('确认删除？'))window.location.href='dodelproject.jsp?pid=<%=pid %>'" >删除</button>
  				</td>
  				
  			</tr>
  			<%} %>
  			</tbody>
  		</table>
  		
  		
  		
  		<center>
  		<form action="teacherspro.jsp" method="post" name="form2">
  <table>
 <tr>  
 <td>共<%out.print(totalCount);%>条记录,共<%out.print(totalpages);%> 页，当前是第<%out.print(np);%>  页</td> 
 <td> 
     <a href="teacherspro.jsp?page=<%=np - 1%>">上一页&nbsp; </a> 
     <a href="teacherspro.jsp?page=<%=np + 1%>">下一页&nbsp;  </a> 
     <a href="teacherspro.jsp?page=1">首页 &nbsp;</a> 
     <a href="teacherspro.jsp?page=<%=totalpages%>">尾页  </a> 
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
  