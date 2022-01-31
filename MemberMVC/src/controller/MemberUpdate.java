package controller;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class MemberUpdate
 */
@WebServlet("/MemberUpdate")
public class MemberUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request , response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // �ѱ� ���� ����
		// Response���� alert���� (�ѱ۱��� �ذ�)
		response.setContentType("text/html; charset=UTF-8");

		// useBean�� ������� ���ϹǷ� Bean ��ü�� ����Ѵ�.
		MemberBean update_bean = new MemberBean();
		
		String real_update = request.getParameter("real_update");
		if(real_update != null && real_update.equals("real_update")) {
			// ������ ȸ�������� ������ ��
			String pass1 = request.getParameter("pass1"); // update���� �Է¹��� ��й�ȣ
			String id = request.getParameter("id"); 
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			
			if(pass1 == null || pass1.equals("")  ||
					email == null || email.equals("") ||
					tel == null || tel.equals("")) {
					
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('�Է��� �� �� ������ �ֽ��ϴ�.')");
				script.println("history.back()");
				script.println("</script>");
				
				return;
			}
			
			// ��й�ȣ Ȯ���ϱ�!!!
			MemberDAO mdao = new MemberDAO();
			String checkPass = mdao.getPass(id);
			if(checkPass.equals(pass1)) { // ��й�ȣ�� ������
				update_bean.setId(id);
				update_bean.setEmail(email);
				update_bean.setTel(tel);
				
				int check_update = mdao.update(update_bean);
				if(check_update == 1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('�����Ǿ����ϴ�.')");
					script.println("</script>");
					
					ArrayList<MemberBean> v = mdao.getAllMember();
					request.setAttribute("v", v);// ���͸� jsp������ �Ѱ���

					RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
					dis.forward(request, response);
				}
				else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('������ �����߽��ϴ�.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
			else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���� �ּ���.')");
				script.println("history.back()");
				script.println("</script>");
			}
			
			
		}
		else {
			// ó���� ȸ�������� ������ ��!!
			String id = request.getParameter("id");
			// ������ ���̽��� �����Ͽ� ȸ���� ��� ������ ����
			MemberDAO mdao = new MemberDAO();
			MemberBean bean = mdao.getOneMember(id);

			request.setAttribute("oneMember", bean);// �ϳ��� ȸ�� ������ jsp������ �Ѱ���

			RequestDispatcher dis = request.getRequestDispatcher("MemberUpdate.jsp");
			dis.forward(request, response);
		}
		
	}

}
