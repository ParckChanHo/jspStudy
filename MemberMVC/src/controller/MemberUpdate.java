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
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		// Response에서 alert띄우기 (한글깨짐 해결)
		response.setContentType("text/html; charset=UTF-8");

		// useBean을 사용하지 못하므로 Bean 객체를 사용한다.
		MemberBean update_bean = new MemberBean();
		
		String real_update = request.getParameter("real_update");
		if(real_update != null && real_update.equals("real_update")) {
			// 정말로 회원정보를 수정할 때
			String pass1 = request.getParameter("pass1"); // update에서 입력받은 비밀번호
			String id = request.getParameter("id"); 
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			
			if(pass1 == null || pass1.equals("")  ||
					email == null || email.equals("") ||
					tel == null || tel.equals("")) {
					
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('입력이 안 된 사항이 있습니다.')");
				script.println("history.back()");
				script.println("</script>");
				
				return;
			}
			
			// 비밀번호 확인하기!!!
			MemberDAO mdao = new MemberDAO();
			String checkPass = mdao.getPass(id);
			if(checkPass.equals(pass1)) { // 비밀번호가 같으면
				update_bean.setId(id);
				update_bean.setEmail(email);
				update_bean.setTel(tel);
				
				int check_update = mdao.update(update_bean);
				if(check_update == 1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('수정되었습니다.')");
					script.println("</script>");
					
					ArrayList<MemberBean> v = mdao.getAllMember();
					request.setAttribute("v", v);// 벡터를 jsp쪽으로 넘겨줌

					RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
					dis.forward(request, response);
				}
				else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('수정에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
			else {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 틀렸습니다. 다시 확인해 주세요.')");
				script.println("history.back()");
				script.println("</script>");
			}
			
			
		}
		else {
			// 처음에 회원정보를 보여줄 때!!
			String id = request.getParameter("id");
			// 데이터 베이스에 연결하여 회원의 모든 정보를 리턴
			MemberDAO mdao = new MemberDAO();
			MemberBean bean = mdao.getOneMember(id);

			request.setAttribute("oneMember", bean);// 하나의 회원 정보를 jsp쪽으로 넘겨줌

			RequestDispatcher dis = request.getRequestDispatcher("MemberUpdate.jsp");
			dis.forward(request, response);
		}
		
	}

}
