<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ page import="java.io.PrintWriter"%> <!-- 자바 스크립트를 사용하기 위해서!!! -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>메인화면이다. - 모든 회원정보 보기로 자동으로 넘어간다.</title>
</head>
<body>
	<!-- JSP WEB MVC Model2 Programming(중급 과정) ==> 섹션 4. MVC 패턴 연습 -->

	<%
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = 'MemberlistCon.do'");
		script.println("</script>");
	%>
</body>
</html>