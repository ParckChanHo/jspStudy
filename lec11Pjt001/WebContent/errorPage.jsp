<%@ page language="java" contentType="text/html; charset=utf-8" 
   pageEncoding="utf-8"%>
<!-- 내가 이 jsp 페이지(errorPage.jsp)를 에러 페이지로 사용하겠다. -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Insert title here</title>
    </head>
    <body>

		<%
			response.setStatus(200);
			String msg = exception.getMessage();
		%>
		
		<h1> error message : <%= msg %> </h1>

    </body>
</html>