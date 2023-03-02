<%@ page language="java" contentType="text/html; charset=UTF-8"
import="com.biz.user.UserVO"%>


	<hr>
	<a href="index.jsp">HOME</a>&nbsp;&nbsp;&nbsp;
	
	<%UserVO user = (UserVO)session.getAttribute("user");%>
   <%if(user == null){%>
	<a href="insertUserView.do">회원가입</a>&nbsp;&nbsp;&nbsp;
	<a href="loginView.do">로그인</a>&nbsp;&nbsp;&nbsp;
	<%}else{ %>
	<a href="insertBoardView.do">글등록</a>&nbsp;&nbsp;&nbsp;
	<a href="logout.do">로그아웃</a>&nbsp;&nbsp;&nbsp;
	<%} %>
	<hr>
	<br>

