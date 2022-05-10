<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String mail=(String)session.getAttribute("umail");
	if(mail==null){%>
	<jsp:forward page="index.jsp"></jsp:forward>
	<%}
%>