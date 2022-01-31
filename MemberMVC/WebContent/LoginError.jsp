<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 아이디 중복 확인 -->
	<%
		if(request.getAttribute("msg").equals("false")){
			
		
	%>
	<script type="text/javascript">
		alert("사용할 수 없는 아이디 입니다.");
		location.href = 'MemberJoin.jsp';
	</script>
	
	<%
		} else{
	%>
	<script type="text/javascript">
		alert("패스워드기 일치하지 않습니다. 다시 확인해 주세요.");
		location.href = 'MemberJoin.jsp';
	</script>
	<%
		}
	%>
	<!-- 
		 location.href 를 이용하여 "blog.naver.com" 으로 페이지를 이동하시오.
		 location.href = "blog.naver.com";
	 -->
</body>
</html>