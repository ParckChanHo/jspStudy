package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAO;

/**
 * Servlet implementation class MemberListOneProc
 */
// 하나의 회원 정보를 상세히 보기-Servlet
@WebServlet("/MemberListOneProc")
public class MemberListOneProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		
		String id = request.getParameter("id");
		// 데이터 베이스에 연결하여 회원의 모든 정보를 리턴
		MemberDAO mdao = new MemberDAO();
		MemberBean bean = mdao.getOneMember(id);

		request.setAttribute("oneMember", bean);// 하나의 회원 정보를 jsp쪽으로 넘겨줌

		RequestDispatcher dis = request.getRequestDispatcher("MemberListOne.jsp");
		dis.forward(request, response);
	}

}
