<%--
  Created by IntelliJ IDEA.
  User: 蛋丁
  Date: 2020/9/13
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>
  <!-- Page Heading -->
  <table border='1' cellpadding='5' cellspacing='0' width='400'>
    <tr bgcolor='#CCCCFF' align='center' valign='center' height='20'>
      <td><h3>热门电影、动漫首页</h3></td>
    </tr>
  </table>

  <p>这是热门电影、动漫的首页.
  </p>

  <h3>DVDs</h3>
  <ul>
    <li><a href='listLibraryServlet.view'>List</a> all DVDs.</li>
    <li><a href='selectDVDServlet.view'>Register</a> for a DVDBorrower.</li>
  </ul>

  <h3>DVD Administrator</h3>
  <ul>
    <li><a href='addDVDItemFormServlet.view'>Add</a> a new DVD.</li>
  </ul>
  </body>
</html>
