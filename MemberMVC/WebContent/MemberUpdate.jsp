<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="model.MemberBean"%>  
<!-- 하나의 회원 정보를 수정하기-jsp -->  
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
		<h2>회원 정보 수정 하기</h2>
	
		<form action="MemberUpdate" method="post">
			<table width="500" border="1">
				<tr height="50">
					<td width="150" align="center">아이디 </td>
					<td width="350" align="center"><%=bean.getId()%></td>
				</tr>
				
				<tr height="50">
					<td width="150" align="center">패스워드</td>
					<td width="350" align="center">
						<input type="password" name="pass1" size="40"/>
					</td>
				</tr>
				
				<tr height="50">
					<td width="150" align="center">이메일</td>
					<td width="350" align="center">
						<input type="email" name="email" size="40" value="<%=bean.getEmail()%>">						
					</td>
				</tr>
				
				<tr height="50">
					<td width="150" align="center">전화 번호 </td>
					<td width="350" align="center">
						<input type="tel" name="tel" size="40" value="<%=bean.getTel()%>">				
					</td>
				</tr>
									
				<tr height="50">
					<td align = center width="150" colspan="2">
						<input type="submit" value="회원 수정하기">	
						<input type="button" value="회원 전체 보기" onclick="location.href='MemberlistCon.do'">
						<input type="hidden" name="real_update"	value="real_update"/>		
						<input type="hidden" name="id" value="<%=bean.getId()%>"/>
					</td>
				</tr>	
			</table>
		</form>	
	</center>
</body>
</html>