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
// �ϳ��� ȸ�� ������ ���� ����-Servlet
@WebServlet("/MemberListOneProc")
public class MemberListOneProc extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // �ѱ� ���� ����
		
		String id = request.getParameter("id");
		// ������ ���̽��� �����Ͽ� ȸ���� ��� ������ ����
		MemberDAO mdao = new MemberDAO();
		MemberBean bean = mdao.getOneMember(id);

		request.setAttribute("oneMember", bean);// �ϳ��� ȸ�� ������ jsp������ �Ѱ���

		RequestDispatcher dis = request.getRequestDispatcher("MemberListOne.jsp");
		dis.forward(request, response);
	}

}
