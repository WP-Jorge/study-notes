<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>$Title$</title>
	<script type="text/javascript" src="js/jQuery-3.5.1.js"></script>
	<script type="text/javascript">
		$(function () {
			$("button").click(() => {
				$.ajax({
					// url: "returnStudentJsonArray.do",
					url: "returnStringData.do",
					data: {
						name: "zhangsan",
						age: 19,
					},
					type: "post",
					// 如果使用文本返回，吧datatype注释掉
					// dataType: "json",
					dataType: "text",
					success: (res) => {
						// res从服务器端返回的是json格式的字符串{"name" :" zhangsan","age":20}
						// jquery会把字符串转为json对象，赋值给resp形参。
						// [{"name":"李四","age":20},{"name":"张三","age":22}]
						// alert(res.name + '   ' + res.age);
						console.log(res);
						// $.each(res, (i, n) => {
						// 	console.log(n.name);
						// 	console.log(n.age);
						// })
					}
				})
			})
		})
	</script>
</head>
<body>
<p>处理器方法返回string表示视图名称</p>
<form action="returnString-view.do" method="post">
	姓名：<input type="text" name="name"><br>
	年龄：<input type="text" name="age"><br>
	<button>post提交</button>
</form>
<br>
<p>处理器方法返回string表示视图完整路径</p>
<form action="returnString-view2.do" method="post">
	姓名：<input type="text" name="name"><br>
	年龄：<input type="text" name="age"><br>
	<button>post提交</button>
</form>
<br>
<button id="btn">发送AJAX请求</button>
</body>
</html>
