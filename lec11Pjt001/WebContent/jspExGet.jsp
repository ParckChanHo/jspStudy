<%@ page language="java" contentType="text/html; charset=utf-8" 
   pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Insert title here</title>
    </head>
    <body>
		
		<%!
			String connectedIP;
			String connectedUser;
			
		%>
		
		<!-- application 객체 -->
		<%	
			// (String)getServletContext().getAttribute("connectedIP");
			connectedIP = (String)application.getAttribute("connectedIP");
			// (String)getServletContext().getAttribute("connectedUser");
			connectedUser = (String)application.getAttribute("connectedUser");
		%>
		
		<p>connectedIP : <%= connectedIP %></p>
		<p>connectedUser : <%= connectedUser %></p>
		
    </body>
</html>