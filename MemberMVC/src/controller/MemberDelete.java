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
 * Servlet implementation class MemberDelete
 */
@WebServlet("/MemberDelete")
public class MemberDelete extends HttpServlet {
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
		
		String real_delete = request.getParameter("real_delete");
		if(real_delete != null && real_delete.equals("real_delete")) {
			// ������ ȸ�������� ������ ��
			String id = request.getParameter("id");
			String pass1 = request.getParameter("pass1"); // delete���� �Է¹��� ��й�ȣ
			
			if(pass1 == null || pass1.equals("")) { // ��й�ȣ�� �Է����� �ʾ��� ���!!
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('��й�ȣ�� �Է��ϼ���.')");
				script.println("history.back()");
				script.println("</script>");
				
				return;
			}
			
			// ��й�ȣ Ȯ���ϱ�!!!
			MemberDAO mdao = new MemberDAO();
			String checkPass = mdao.getPass(id);
			if(checkPass.equals(pass1)) { // ��й�ȣ�� ������
				int check_delete = mdao.delete(id);
				if(check_delete == 1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('�����Ǿ����ϴ�.')");
					script.println("</script>");
					
					ArrayList<MemberBean> v = mdao.getAllMember();
					request.setAttribute("v", v);// ���͸� jsp������ �Ѱ���

					RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
					dis.forward(request, response);
				}
				else {  // check_delete == -1 ===> ������ �߻��� ���
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('������ �����߽��ϴ�.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
			else { // ��й�ȣ�� Ʋ����
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� Ȯ���� �ּ���.')");
				script.println("history.back()");
				script.println("</script>");
			}
		} // end if
		
		else { // ó���� ������ ȸ�������� �����ֱ�
			String id = request.getParameter("id");
			// ������ ���̽��� �����Ͽ� ȸ���� ��� ������ ����
			MemberDAO mdao = new MemberDAO();
			MemberBean bean = mdao.getOneMember(id);

			request.setAttribute("oneMember", bean);// �ϳ��� ȸ�� ������ jsp������ �Ѱ���

			RequestDispatcher dis = request.getRequestDispatcher("MemberDelete.jsp");
			dis.forward(request, response);
		}
		
	}

}
