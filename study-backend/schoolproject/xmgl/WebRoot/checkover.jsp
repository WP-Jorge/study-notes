<%@ page language="java" import="java.util.*,dao.*,entity.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'exit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <jsp:include page="islogin.jsp"></jsp:include>
  <%
  	request.setCharacterEncoding("utf-8");
      int teacher_id=new UserDao().getUserByMail((String)session.getAttribute("umail")).getUid();
      int pageSize=10;//每页显示条数
      ProjectDao pd=new ProjectDao();
  	int totalCount=pd.getCount(2);//1待立项总共条数

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
  		<table>
  			<tr>
  				<td>项目标题</td>
  				<td>评审专家</td>
  				<td>项目状态</td>
  				<td>查看编辑</td>
  			</tr>
  			<%
  				List<Project> plist = new ArrayList<Project>();
  			  					plist=pd.listByPage(2,np);//查询1状态的项目
  			  					 for(int i=0;i<plist.size();i++)
  			  				 	 {
  			  				 		Project pro=plist.get(i); 
  			  			  					int pid=pro.getPid();
  			  			  					User teacher=new UserDao().getUserById(pro.getPteacher());
  			%>
  			<tr>
  				<td><%=pro.getPtitle()%></td>
  				<td><%=pro.getExpertName() %></td>
  				<td><%=pro.getPsTitle()%></td>
  				<td><a href="checkapplydetail.jsp?pid=<%=pid %>">查看编辑</a></td>
  			</tr>
  			<%} %>
  		</table>
  </body>
  <form action="checkover.jsp" method="post" name="form2">
 <table>
 <tr>  
 <td>共<%out.print(totalCount);%>条记录,共<%out.print(totalpages);%> 页，当前是第<%out.print(np);%>  页</td> 
 <td> 
     <a href="checkover.jsp?page=<%=np - 1%>">上一页&nbsp; </a> 
     <a href="checkover.jsp?page=<%=np + 1%>">下一页&nbsp;  </a> 
     <a href="checkover.jsp?page=1">首页 &nbsp;</a> 
     <a href="checkover.jsp?page=<%=totalpages%>">尾页  </a> 
     <input type="submit" name="Submit" value="G0">  
     <input type="text" name="page" size="10"> 
      页</td> 
     </tr> 
     </table>
   </form>
</html>