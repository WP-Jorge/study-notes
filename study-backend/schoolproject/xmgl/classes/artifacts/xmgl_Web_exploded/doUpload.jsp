<%@ page contentType="text/html; charset=utf-8" language="java" 
import="java.util.*,com.jspsmart.upload.*,dao.*,java.text.SimpleDateFormat,entity.*" errorPage="" %>
<html>
<head>
<title>文件上传处理页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>

<body>
<%
	// 新建一个SmartUpload对象
	SmartUpload su = new SmartUpload();
	// 上传初始化
	su.initialize(pageContext);
	// 设定上传限制
	// 1.限制每个上传文件的最大长度。
	// su.setMaxFileSize(1000000000);
	// 2.限制总上传数据的长度。
	// su.setTotalMaxFileSize(2000000000);
	// 3.设定允许上传的文件（通过扩展名限制）,仅允许doc,txt文件。
	 su.setAllowedFilesList("zip,rar,rpm");
	// 4.设定禁止上传的文件（通过扩展名限制）,禁止上传带有exe,bat,	jsp,htm,html扩展名的文件和没有扩展名的文件。
	su.setDeniedFilesList("exe,bat,jsp,htm,html,,");
	// 上传文件
	try{
	su.upload();}
	catch(Exception e){
		out.println("<script>parent.callback('格式只限zip、rar、rpm压缩文件')</script>");
	}
	// 将上传文件全部保存到指定目录
	//int count = su.save("/上传目录",su.SAVE_VIRTUAL);
	//out.println(count+"个文件上传成功！<br>");
	request.setCharacterEncoding("utf-8");
	int p_id=Integer.parseInt(su.getRequest().getParameter("pid").toString().trim());
	Project p=new ProjectDao().getProById(p_id);
	String teacher=p.getTeacherName();
	String proName=p.getPtitle();
	
	int file_type=Integer.parseInt(su.getRequest().getParameter("filetype").toString().trim());
	
	if(file_type!=0){
		if(p.getPstatus()==8){new ProjectDao().updateStatus(6,p_id);}
		else{new ProjectDao().updateStatus(4,p_id);}
	}
	
	String fileType=file_type==0?"application":"conclusion";
	
	//String filename=su.getRequest().getParameter("filename").toString().trim();
	
	// 逐一提取上传文件信息，同时可保存文件。
	for (int i=0;i<su.getFiles().getCount();i++)
	{
		com.jspsmart.upload.File file = su.getFiles().getFile(i);

		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");
		String uploadTime=df.format(date);
		String fileExt=file.getFileExt();
		if(fileExt.equalsIgnoreCase("zip")||fileExt.equalsIgnoreCase("rar")||fileExt.equalsIgnoreCase("rpm"))
	out.println("<script>parent.callback('格式只限zip、rar、rpm压缩文件')</script>");
		//String saveAsFileName=uploadTime+file.getFileName();
		String saveAsFileName=uploadTime+teacher+proName+fileType+"."+fileExt;
		//另存到指定目录,在文件名前加上上传的时间防止重名
		try{
		file.saveAs("/uploadFile/"+saveAsFileName,file.SAVEAS_VIRTUAL);}
	catch(Exception e){
		out.println("<script>parent.callback('格式只限zip、rar、rpm压缩文件')</script>");
	}
		
		//将修改后的文件名称保存到数据库中
		ProjectDao pd=new ProjectDao();
		int row=pd.uploadFile(file_type,saveAsFileName,p_id);
		if(row==1){out.println("<script>parent.callback('上传成功')</script>");}
		else{out.println("<script>parent.callback('上传失败')</script>");}
		// 若文件不存在则继续
		if (file.isMissing()) continue;

		// 显示当前文件信息
		//out.println("<table border=1>");
		//out.println("<tr><td>表单项名（FieldName）</td><td>"
		//+ file.getFieldName() + "</td></tr>");
		//out.println("<tr><td>文件长度（Size）</td><td>" + 
		//file.getSize() + "</td></tr>");
		//out.println("<tr><td>文件名（FileName）</td><td>" 
		//+ file.getFileName() + "</td></tr>");
		//out.println("<tr><td>文件扩展名（FileExt）</td><td>" 
		//+ file.getFileExt() + "</td></tr>");
		//out.println("<tr><td>文件全名（FilePathName）</td><td>"
		//+ file.getFilePathName() + "</td></tr>");
		//out.println("</table><br />");

		// 将文件另存
		// file.saveAs("/upload/" + myFile.getFileName());
		// 另存到以WEB应用程序的根目录为文件根目录的目录下
		// file.saveAs("/upload/" + myFile.getFileName(), su.SAVE_VIRTUAL);
		// 另存到操作系统的根目录为文件根目录的目录下
		// file.saveAs("c:\\temp\\" + myFile.getFileName(),	su.SAVE_PHYSICAL);

	}
%>
</body>
</html>