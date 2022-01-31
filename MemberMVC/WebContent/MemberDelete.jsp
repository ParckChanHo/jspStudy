<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.MemberBean"%>
<!-- 하나의 회원 삭제하기 -jsp --> 
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
		<h2>회원 가입</h2>
		<!--<form action="Proc.do?gubun=1" method="post">  -->
		<form action="MemberDelete" method="post">
			<table width="400" border="1">
				<tr height="50">
					<td width="150" align="center">아이디 </td>
					<td width="250" align="center"><%=bean.getId() %></td>
				</tr>
				
				<tr height="50">
					<td width="150" align="center">패스워드</td>
					<td width="250" align="center">
						<input type="password" name="pass1">						
					</td>
				</tr>
				
				<tr height="50">
					<td align = center width="150" colspan="2">
						<input type="submit" value="회원 삭제하기">	
						<input type="button" value="회원 전체 보기" onclick="location.href='MemberlistCon.do'">
						<input type="hidden" name="real_delete"	value="real_delete"/>		
						<input type="hidden" name="id" value="<%=bean.getId()%>"/>
					</td>
				</tr>	
			</table>
		</form>	
	</center>
</body>
</html>