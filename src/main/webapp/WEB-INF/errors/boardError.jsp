<%@ page language="java" contentType="text/html; charset=UTF-8"
	isErrorPage="true" import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error</title>
</head>
<body>

	<center>
		<h3><%=exception.getClass().getName()%>가 발생했습니다!!</h3>		
		<hr>
		<%Date currentTime = new Date(); %>
		예외 발생 시간 : <%=currentTime.toString() %>
		<% StackTraceElement[] elements = exception.getStackTrace(); %>
		예외위치 : <%=elements[0].toString() %>
	</center>

</body>
</html>