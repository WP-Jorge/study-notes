<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">  
function callback(msg)   
{   
    document.getElementById("file").outerHTML = document.getElementById("file").outerHTML;   
    document.getElementById("msg").innerHTML = "<font color=red>"+msg+"</font>";   
}   
</script>
</head>
<body>
	<form action="doUpload.jsp" id="form1" name="form1" encType="multipart/form-data"  method="post" target="hidden_frame">  
	    <input type="file" id="file" name="file" style="width:450" />  
	    <input type="submit" value="上传文件" /><span id="msg"></span>  
	    <br>  
	    <font color="red">支持JPG,JPEG,GIF,BMP,SWF,RMVB,RM,AVI文件的上传</font>                
	    <iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>  
	</form>
</body>
</html>