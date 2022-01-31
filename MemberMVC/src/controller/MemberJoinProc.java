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
		//�ѱ� ó�� 
		request.setCharacterEncoding("UTF-8");
		// Response���� alert���� (�ѱ۱��� �ذ�)
		response.setContentType("text/html; charset=UTF-8");

		// useBean�� ������� ���ϹǷ� Bean ��ü�� ����Ѵ�.
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
			script.println("alert('�Է��� �� �� ������ �ֽ��ϴ�.')");
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
			
			String [] arr = request.getParameterValues("hobby"); // ����ڰ� üũ�� üũ�ڽ����� �迭�� ��ȯ���ش�.
			// request.getParameterValues("hobby")[0]; ==> 0�� �迭�� ���� ��ȯ���ش�.
			String data="";
			
			for(String string : arr) {
				data += string + " ";//�ϳ��� ��Ʈ������ ������ ���� 
			}
			
			bean.setHobby(data);
			bean.setJob(request.getParameter("job"));
			bean.setAge(request.getParameter("age"));
			bean.setInfo(request.getParameter("info"));
			
			
			//�е���尡 ���� ��쿡�� ������ ���̽��� ���� 
			if(pass1.equals(pass2)){
				//������ ���̽� ��ü ����
				MemberDAO mdao = new MemberDAO();
				int check_id = mdao.insertMember(bean);
				
				if(check_id == -1) {
					request.setAttribute("msg","false");
					RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp");
					dis.forward(request, response);
				}
					
				//��Ʈ�ѷ����� �Ǵٸ� ��Ʈ�ѷ��� ȣ���� �־�� �մϴ�. 
				RequestDispatcher dis = request.getRequestDispatcher("/MemberlistCon.do");
				dis.forward(request, response);
				
			}else {
				request.setAttribute("msg","�н����尡 ��ġ���� �ʽ��ϴ�");
				RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp");
				dis.forward(request, response);
			}
		} // end else��
		
		
	}
	
	/*
	 1. �żҵ� 
	 2. �żҵ� 
	 3. �żҵ� 
	 ......
	 */
}
