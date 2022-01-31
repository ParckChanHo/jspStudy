package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAO;

@WebServlet("/Proc.do")
public class MemberJoinProc extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}	
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 처리 
		request.setCharacterEncoding("UTF-8");
		// Response에서 alert띄우기 (한글깨짐 해결)
		response.setContentType("text/html; charset=UTF-8");

		// useBean을 사용하지 못하므로 Bean 객체를 사용한다.
		MemberBean bean = new MemberBean();
		
		if(request.getParameter("id") == null || request.getParameter("id").equals("") ||
				request.getParameter("pass1") == null || request.getParameter("pass1").equals("") ||
				request.getParameter("pass2") == null || request.getParameter("pass2").equals("") ||
				request.getParameter("email") == null || request.getParameter("email").equals("") ||
				request.getParameter("tel") == null || request.getParameter("tel").equals("") ||
				request.getParameterValues("hobby") == null || request.getParameterValues("hobby").equals("") ||
				request.getParameter("job") == null || request.getParameter("job").equals("") ||
				request.getParameter("age") == null || request.getParameter("age").equals("") ||
				request.getParameter("info") == null || request.getParameter("info").equals("")) {
			
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('입력이 안 된 사항이 있습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else {
			bean.setId(request.getParameter("id"));
			
			String pass1 = request.getParameter("pass1");
			String pass2 = request.getParameter("pass2");
			
			bean.setPass1(request.getParameter("pass1"));
			bean.setPass2(request.getParameter("pass2"));
			bean.setEmail(request.getParameter("email"));
			bean.setTel(request.getParameter("tel"));
			
			String [] arr = request.getParameterValues("hobby"); // 사용자가 체크한 체크박스들을 배열로 반환해준다.
			// request.getParameterValues("hobby")[0]; ==> 0번 배열의 값만 반환해준다.
			String data="";
			
			for(String string : arr) {
				data += string + " ";//하나의 스트링으로 데이터 연결 
			}
			
			bean.setHobby(data);
			bean.setJob(request.getParameter("job"));
			bean.setAge(request.getParameter("age"));
			bean.setInfo(request.getParameter("info"));
			
			
			//패드워드가 같을 경우에만 데이터 베이스에 저장 
			if(pass1.equals(pass2)){
				//데이터 베이스 객체 생성
				MemberDAO mdao = new MemberDAO();
				int check_id = mdao.insertMember(bean);
				
				if(check_id == -1) {
					request.setAttribute("msg","false");
					RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp");
					dis.forward(request, response);
				}
					
				//컨트롤러에서 또다른 컨트롤러를 호출해 주어야 합니다. 
				RequestDispatcher dis = request.getRequestDispatcher("/MemberlistCon.do");
				dis.forward(request, response);
				
			}else {
				request.setAttribute("msg","패스워드가 일치하지 않습니다");
				RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp");
				dis.forward(request, response);
			}
		} // end else문
		
		
	}
	
	/*
	 1. 매소드 
	 2. 매소드 
	 3. 매소드 
	 ......
	 */
}
