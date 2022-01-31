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
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		// Response에서 alert띄우기 (한글깨짐 해결)
		response.setContentType("text/html; charset=UTF-8");
		
		String real_delete = request.getParameter("real_delete");
		if(real_delete != null && real_delete.equals("real_delete")) {
			// 정말로 회원정보를 삭제할 때
			String id = request.getParameter("id");
			String pass1 = request.getParameter("pass1"); // delete에서 입력받은 비밀번호
			
			if(pass1 == null || pass1.equals("")) { // 비밀번호를 입력하지 않았을 경우!!
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호를 입력하세요.')");
				script.println("history.back()");
				script.println("</script>");
				
				return;
			}
			
			// 비밀번호 확인하기!!!
			MemberDAO mdao = new MemberDAO();
			String checkPass = mdao.getPass(id);
			if(checkPass.equals(pass1)) { // 비밀번호가 같으면
				int check_delete = mdao.delete(id);
				if(check_delete == 1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('삭제되었습니다.')");
					script.println("</script>");
					
					ArrayList<MemberBean> v = mdao.getAllMember();
					request.setAttribute("v", v);// 벡터를 jsp쪽으로 넘겨줌

					RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
					dis.forward(request, response);
				}
				else {  // check_delete == -1 ===> 오류가 발생할 경우
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('삭제에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				}
			}
			else { // 비밀번호가 틀리면
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('비밀번호가 틀렸습니다. 다시 확인해 주세요.')");
				script.println("history.back()");
				script.println("</script>");
			}
		} // end if
		
		else { // 처음에 선택한 회원정보를 보여주기
			String id = request.getParameter("id");
			// 데이터 베이스에 연결하여 회원의 모든 정보를 리턴
			MemberDAO mdao = new MemberDAO();
			MemberBean bean = mdao.getOneMember(id);

			request.setAttribute("oneMember", bean);// 하나의 회원 정보를 jsp쪽으로 넘겨줌

			RequestDispatcher dis = request.getRequestDispatcher("MemberDelete.jsp");
			dis.forward(request, response);
		}
		
	}

}
