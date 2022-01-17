package com.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mSignUp")
public class MemSignUp extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		System.out.println(" -- doGet() -- ");
		
		String m_name = request.getParameter("m_name");
		String m_pass = request.getParameter("m_pass");
		String m_gender = request.getParameter("m_gender");
		// 파라미터 하나가 여러개의 값을 가질 경우, 배열로 리턴받아 처리한다. 보통 체크박스(Checkbox)를 사용할 때 사용한다. ==> getParameterValues() 배열을 리턴해준다.
		// 만약 0번 배열의 값만 읽어오고 싶다면.. ==> request.getParameterValues("m_hobby")[0];
		String[] m_hobbys = request.getParameterValues("m_hobby");
		String m_residence = request.getParameter("m_residence");
		
		System.out.println("m_name : " + m_name);
		System.out.println("m_pass : " + m_pass);
		System.out.println("m_gender : " + m_gender);
		// m_hobbys : [sport, reading]
		System.out.println("m_hobbys : " + Arrays.toString(m_hobbys));
		System.out.println("m_residence : " + m_residence);
		
		// getParameterNames() 메소드 ==> 파라미터의 이름 집합을 Enumeration 객체로 반환하는 메소드로써, 전체 파라미터를 보다 쉽게 다룰 수 있는 방법을 제공한다.
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			System.out.println("name : " + name);
			System.out.println("name : " + request.getParameter(name));
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(" -- doPost() -- ");
		
		doGet(request, response);
	}

}
