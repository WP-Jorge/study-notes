<%--
  Created by IntelliJ IDEA.
  User: jimshen
  Date: 2019/3/18
  Time: 8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
</head>
<body>
<p>
    说明：此为测试用例首页<br/>
<ul>
    <li>如果用户此前未登陆系统，将自动转向到oauth2进行扫码验证</li>
    <li>如果用户已登陆，将显示用户真实姓名</li>
</ul>
</p>
<%
    if (session.getAttribute("PersonName") == null)
        response.sendRedirect("oauth2");
    else
        out.println(session.getAttribute("PersonName"));
%>
</body>
</html>
