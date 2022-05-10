<%--
  Created by IntelliJ IDEA.
  User: 蛋丁
  Date: 2020/9/5
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>标题</title>
  </head>
  <body>
  <%
    for (int i = 1; i <= 10; i++ ) {
      out.println("<h3 style=\"text-align: center;\">" + i + "的平方是：" + i * i + "</h3>");
    }
  %>
  </body>
</html>
