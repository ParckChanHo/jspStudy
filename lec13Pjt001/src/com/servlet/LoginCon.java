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
	   쿠키란?
	  
	  1) 쿠키는 Key와 Value로 이루어져 있으며 클라이언트에서 생성하고 저장할 수 있으며 서버단에서 전송한 쿠키가 클라이언트에게 저장이
	         될 수 있다.
	         
	  2) 쿠키는 웹 브라우저(클라이언트)에 저장이 된다
	  
	  3) 서버 -> 클라이언트에게 쿠키를 전송한 경우 
	       서버에서 전송한 쿠키는 클라이언트의 브라우저로 전송이 되어서 사용자의 컴퓨터에 저장이
	       된다. 이렇게 저장이 된 쿠키는 다시 해당하는 웹 페이지를 접속할 때 브라우저(클라이언트)에서 서버로 쿠키를 전송하게 된다. 이름-값 쌍 외에도
	      도메인,경로,유효기간,보안 속성 등을 저장할 수 있다.
	  
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
		
		// 클라이언트가 보낸 쿠키 정보 읽기
		Cookie[] cookies = request.getCookies(); 
		// 쿠키는 배열로 return이 되며 쿠키 값이 없으면 null이 return이 된다. 따라서 null에 대한 처리를 해주어야 한다 
		Cookie cookie = null;
		
		for (Cookie c : cookies) {
			// getName() ==> 쿠키의 이름을 String 으로 반환  getValue() ==> 쿠키의 값을 String으로 반환
			System.out.println("c.getName() : " + c.getName() + ", c.getValue() : " + c.getValue());
			
			if(c.getName().equals("memberId")) {
				cookie = c;
			}
		}
		
		if(cookie == null) {
			System.out.println("cookie is null");
			cookie = new Cookie("memberId", mId);
		}
		
		// 서버에서 쿠키를 생성하여서 addCookie() 메소드를 통해 클라이언트에게 생성한 쿠키를 전송한다.
		response.addCookie(cookie);
		// 초 단위이며 60 * 60은 1시간(60분)을 의미한다.  1주일로 지정하려면 (7*24*60*60) 이다.
		cookie.setMaxAge(60*60); // 쿠키의 유효기간 설정하기
		
		response.sendRedirect("loginOk.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
