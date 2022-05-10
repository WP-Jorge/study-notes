<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>$Title$</title>
</head>
<body>
<p>提交参数给Controller</p>
<p><a href="user/some.do">发起some.do的get请求</a></p><br>
<form action="receiveproperty.do" method="post">
	姓名：<input type="text" name="name"><br>
	年龄：<input type="text" name="age"><br>
	<button>post提交</button>
</form>
<br>
<p>请求参数名与控制器的形参名不一样</p>
<form action="receiveparam.do" method="post">
	姓名：<input type="text" name="rname"><br>
	年龄：<input type="text" name="rage"><br>
	<button>post提交</button>
</form>
<br>
<p>使用对象接收请求参数</p>
<form action="receiveObject.do" method="post">
	姓名：<input type="text" name="name"><br>
	年龄：<input type="text" name="age"><br>
	<button>post提交</button>
</form>
</body>
</html>
