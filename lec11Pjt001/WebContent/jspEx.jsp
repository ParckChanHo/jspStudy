<%@ page language="java" contentType="text/html; charset=utf-8" 
   pageEncoding="utf-8"%>
   <!-- 이 jsp 페이지에서(jspEx.jsp) 오류가 발생하면 errorPage.jsp 페이지로 이동시켜라. -->
<%@ page errorPage="errorPage.jsp"%>
<!DOCTYPE html>
<html>
    <head>
    <!-- 실전 JSP (renew ver.) - 신입 프로그래머를 위한 강좌 : JSP 내장 객체 -->
        <meta charset="utf-8">
        <title>Insert title here</title>
    </head>
    <body>
		
		<%!
			String adminId;
			String adminPw;
			
			String imgDir;
			String testServerIP;
			
			String str;
		%>
		
		<!-- config 객체 -->
		<%	
			// getServletConfig().getInitParameter("adminId");
			adminId = config.getInitParameter("adminId");
			// getServletConfig().getInitParameter("adminPw");
			adminPw = config.getInitParameter("adminPw");
		%>
		
		<p>adminId : <%= adminId %></p>
		<p>adminPw : <%= adminPw %></p>
		
		<!-- application 객체 -->
		<%	
			// getServletContext().getInitParameter("imgDir")
			imgDir = application.getInitParameter("imgDir");
			// getServletContext().getInitParameter("testServerIP")
			testServerIP = application.getInitParameter("testServerIP");
		%>
		
		<p>imgDir : <%= imgDir %></p>
		<p>testServerIP : <%= testServerIP %></p>
		
		<%-- <%
			application.setAttribute("connectedIP", "165.62.58.23");
			application.setAttribute("connectedUser", "hong");
		%>
		
		<!-- out 객체 -->
		<%
			out.print("<h1>Hello JAVA World!!</h1>");
			out.print("<h2>Hello JSP World!!</h2>");
			out.print("<h3>Hello Servlet World!!</h3>");
		%>
		
		<!-- exception 객체 -->
		<%
			out.print(str.toString());
		%> --%>
		
    </body>
</html>