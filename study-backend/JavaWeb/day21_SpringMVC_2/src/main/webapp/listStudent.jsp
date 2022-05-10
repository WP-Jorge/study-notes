<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>查询学生AJAX</title>
	<script src="js/jQuery-3.5.1.js"></script>
	<script type="text/javascript">
		$(() => {
			loadStudentData();
		})
		
		function loadStudentData() {
			$.ajax({
				url: "student/queryStudent.do",
				type: "post",
				success: (res) => {
					console.log(res);
					// 清空数据
					$("#info").html("");
					// 添加数据
					$.each(res, (i, item) => {
						$("#info").append("<tr>")
							.append("<td>" + item.id + "</td>")
							.append("<td>" + item.name + "</td>")
							.append("<td>" + item.age + "</td>")
							.append("</tr>");
					})
				}
			})
		}
	</script>
</head>
<body>
<div align="center">
	<table>
		<thead>
		<tr>
			<td>学号</td>
			<td>姓名</td>
			<td>年龄</td>
		</tr>
		</thead>
		<tbody id="info">

		</tbody>
	</table>
	<input type="button" id="btnloader" value="查询数据">
</div>
</body>
</html>