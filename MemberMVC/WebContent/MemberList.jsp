<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="java.util.ArrayList"%>
<%@ page import="model.MemberBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a, a:hover { 
		color:#000000;
		text-decoration: none;
	}
</style>
 <!-- a>>> a 태그를 전체 선택한다. a:hover는 사용자의 커서(마우스 포인터)가 요소 위에 올라가 있으면 선택된다.-->
</head>
<body>
	<center>
		<h2> 모든 회원 보기 </h2>
		<table width="800" border="1" bordercolors="gray">
			<tr height ="40">
				<td align="center" width="50">아이디</td>
				<td align="center" width="200">이메일</td>
				<td align="center" width="150">전화</td>
				<td align="center" width="150">취미</td>
				<td align="center" width="150">직업</td>
				<td align="center" width="100">나이</td>
			</tr>
			
			<%
				ArrayList<MemberBean> list = (ArrayList<MemberBean>)request.getAttribute("v");
				for(int i=0; i<list.size(); i++){
					MemberBean bean = list.get(i);
			%>
				<tr height ="40">
					<td align="center" width="50"><a href="MemberListOneProc?id=<%=bean.getId() %>"><%=bean.getId() %></a></td>
					<td align="center" width="200"><%=bean.getEmail() %></td>
					<td align="center" width="150"><%= bean.getTel() %></td>
					<td align="center" width="150"><%=bean.getHobby() %></td>
					<td align="center" width="150"><%=bean.getJob() %></td>
					<td align="center" width="100"><%=bean.getAge() %></td>
				</tr>
			<%
				}
			%>
		</table>
	</center>
</body>
</html>
	<!-- 
		---원래 ---
		<table width="800" border="1" bordercolors="gray">
			<tr height ="40">
				<td align="center" width="50">아이디</td>
				<td align="center" width="200">이메일</td>
				<td align="center" width="150">전화</td>
				<td align="center" width="150">취미</td>
				<td align="center" width="150">직업</td>
				<td align="center" width="100">나이</td>
			</tr>	
	
			<c:forEach var="bean" items="${request.v}">
		         <tr height ="40">
		            <td align="center" width="50">${bean.id}</td>
		            <td align="center" width="200"><a href="#">${bean.email}</a></td>
		            <td align="center" width="150">${bean.tel}</td>
		            <td align="center" width="150">${bean.hobby}</td>
		            <td align="center" width="150">${bean.job}</td>
		            <td align="center" width="100">${bean.age}</td>
		         </tr>
	      </c:forEach>
	 	</table>	
	 -->



<!-- 
	JSTL이란?

	JSP와 같은 태그를 개발자가 추가할 수 있는 기능을 제공하는데 이를 커스텀 태그라고 한다. 
	커스텀 태그 중에서 많이 사용되는 것들을 모아서 JSTL(JSP Standard Tag Library) 라고 한다. 
	이를 사용하면 스크립트릿 코드의 사용을 줄이면서 더욱 간결하고 이해하기 쉬운 JSP 코드를 작성할 수 있다.
 -->