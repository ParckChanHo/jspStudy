package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginCon")
public class LoginCon extends HttpServlet {
	/*
	   ��Ű��?
	  
	  1) ��Ű�� Key�� Value�� �̷���� ������ Ŭ���̾�Ʈ���� �����ϰ� ������ �� ������ �����ܿ��� ������ ��Ű�� Ŭ���̾�Ʈ���� ������
	         �� �� �ִ�.
	         
	  2) ��Ű�� �� ������(Ŭ���̾�Ʈ)�� ������ �ȴ�
	  
	  3) ���� -> Ŭ���̾�Ʈ���� ��Ű�� ������ ��� 
	       �������� ������ ��Ű�� Ŭ���̾�Ʈ�� �������� ������ �Ǿ ������� ��ǻ�Ϳ� ������
	       �ȴ�. �̷��� ������ �� ��Ű�� �ٽ� �ش��ϴ� �� �������� ������ �� ������(Ŭ���̾�Ʈ)���� ������ ��Ű�� �����ϰ� �ȴ�. �̸�-�� �� �ܿ���
	      ������,���,��ȿ�Ⱓ,���� �Ӽ� ���� ������ �� �ִ�.
	  
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String mId = request.getParameter("mID");
		String mPw = request.getParameter("mPW");
		
		out.print("mId : " + mId);
		out.print("mPw : " + mPw);
		
		// Ŭ���̾�Ʈ�� ���� ��Ű ���� �б�
		Cookie[] cookies = request.getCookies(); 
		// ��Ű�� �迭�� return�� �Ǹ� ��Ű ���� ������ null�� return�� �ȴ�. ���� null�� ���� ó���� ���־�� �Ѵ� 
		Cookie cookie = null;
		
		for (Cookie c : cookies) {
			// getName() ==> ��Ű�� �̸��� String ���� ��ȯ  getValue() ==> ��Ű�� ���� String���� ��ȯ
			System.out.println("c.getName() : " + c.getName() + ", c.getValue() : " + c.getValue());
			
			if(c.getName().equals("memberId")) {
				cookie = c;
			}
		}
		
		if(cookie == null) {
			System.out.println("cookie is null");
			cookie = new Cookie("memberId", mId);
		}
		
		// �������� ��Ű�� �����Ͽ��� addCookie() �޼ҵ带 ���� Ŭ���̾�Ʈ���� ������ ��Ű�� �����Ѵ�.
		response.addCookie(cookie);
		// �� �����̸� 60 * 60�� 1�ð�(60��)�� �ǹ��Ѵ�.  1���Ϸ� �����Ϸ��� (7*24*60*60) �̴�.
		cookie.setMaxAge(60*60); // ��Ű�� ��ȿ�Ⱓ �����ϱ�
		
		response.sendRedirect("loginOk.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
