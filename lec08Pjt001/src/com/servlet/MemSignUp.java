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
		// �Ķ���� �ϳ��� �������� ���� ���� ���, �迭�� ���Ϲ޾� ó���Ѵ�. ���� üũ�ڽ�(Checkbox)�� ����� �� ����Ѵ�. ==> getParameterValues() �迭�� �������ش�.
		// ���� 0�� �迭�� ���� �о���� �ʹٸ�.. ==> request.getParameterValues("m_hobby")[0];
		String[] m_hobbys = request.getParameterValues("m_hobby");
		String m_residence = request.getParameter("m_residence");
		
		System.out.println("m_name : " + m_name);
		System.out.println("m_pass : " + m_pass);
		System.out.println("m_gender : " + m_gender);
		// m_hobbys : [sport, reading]
		System.out.println("m_hobbys : " + Arrays.toString(m_hobbys));
		System.out.println("m_residence : " + m_residence);
		
		// getParameterNames() �޼ҵ� ==> �Ķ������ �̸� ������ Enumeration ��ü�� ��ȯ�ϴ� �޼ҵ�ν�, ��ü �Ķ���͸� ���� ���� �ٷ� �� �ִ� ����� �����Ѵ�.
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
