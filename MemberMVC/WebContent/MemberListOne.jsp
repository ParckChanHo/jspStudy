<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>    
<%@ page import="model.MemberBean"%>    
<!-- 하나의 회원 정보를 상세히 보기-jsp -->
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		// 해당하는 id의 회원정보를 받는다.
		MemberBean bean = (MemberBean)request.getAttribute("oneMember"); 
	%>
	<center>
		<h2>회원 정보 보기</h2>
		
		<table width="400" border="1">
			<tr height="50">
				<td align="center" width="150">아이디</td>
				<td align="center" width="250"><%= bean.getId() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">이메일</td>
				<td align="center" width="250"><%= bean.getEmail() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">전화번호</td>
				<td align="center" width="250"><%= bean.getTel() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">취미</td>
				<td align="center" width="250"><%= bean.getHobby() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">직업</td>
				<td align="center" width="250"><%= bean.getJob() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">나이</td>
				<td align="center" width="250"><%= bean.getAge() %></td>
			</tr>
			<tr height="50">
				<td align="center" width="150">하고싶은 말</td>
				<td align="center" width="250"><%= bean.getInfo() %></td>
			</tr>
			
			<tr height="40">
				<td align="center" colspan="2">
					<!-- Servlet으로 먼저 보내야 한다. -->
					<input type="button" value="회원수정" onclick="location.href='MemberUpdate?id=<%=bean.getId()%>'"> 
					<input type="button" value="회원삭제" onclick="location.href='MemberDelete?id=<%=bean.getId()%>'"> 
					<input type="button" value="목록보기" onclick="location.href='MemberlistCon.do'">
					<input type="button" value="회원가입" onclick="location.href='MemberJoin.jsp'">      
				</td>
			</tr>
		</table>
	</center>
	
</body>
</html>