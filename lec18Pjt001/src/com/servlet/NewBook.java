package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newBook")
public class NewBook extends HttpServlet {
	/*
	   Statement�� PreparedStatement�� ������
	   
	   1) Statement�� �ֿ� �޼���
	    (1) executeQuery() ==> select() ���� ������ �� ����ϸ� Resultset Ŭ������ ��ü�� ��ȯ�Ѵ�. �ش� select���� ����� �ش��ϴ� �����Ϳ� ������ �� �ִ�
	        ����� �����Ѵ�. 
	     String sql = "SELECT * FROM book";   
	     ResultSet res = stmt.executeQuery(sql); 
	    (2) executeUpdate() ==> insert(),update(),delete()�� ���� ���� ������ �� ����Ѵ�. ��ȯ���� int ���̰� ó���� �������� ���� ��ȯ�Ѵ�.
	   
	   2) PreparedStatement�� �ֿ� �޼���
	      PreparedStatement�� SQL���� �̸� �����ΰ� ������ ���� �Է��ϴ� ������� ȿ�����̳� ���� ���� ���鿡�� ������ �����̸� SQL Injection ���ݱ��� �� �����ϴ�.
	         �׸��� �⺻������ Statement�� ����� �ޱ� ������ Statement Ŭ������ �޼��嵵 ��� ����� �����ϴ�.
	         
	     (1) executeQuery() ==> select() ���� ������ �� ����ϸ� Resultset Ŭ������ ��ü�� ��ȯ�Ѵ�. �ش� select���� ����� �ش��ϴ� �����Ϳ� ������ �� �ִ�
	         ����� �����Ѵ�.
	      String sql = "SELECT * FROM book";   
	      ResultSet res = pstmt.executeQuery(sql);   
	     (2) executeUpdate() ==> insert(),update(),delete()�� ���� ���� ������ �� ����Ѵ�. ��ȯ���� int ���̰� ó���� �������� ���� ��ȯ�Ѵ�.  
	     
	     PreparedStatement ����
	     <%
		    request.setCharacterEncoding("euc-kr");
		    
		    String memberID = request.getParameter("memberID");
		    String password= request.getParameter("password");
		    String name = request.getParameter("name");
		    String email = request.getParameter("email");
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		 
		    try {
		        String jdbcDriver = "jdbc:mysql://localhost:3306/test?" +
		                            "useUnicode=true&characterEncoding=euckr";
		        String dbUser = "user";
		        String dbPass = "password";
		        
		        conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
		        pstmt = conn.prepareStatement(
		            "insert into MEMBER values (?, ?, ?, ?)");
		        pstmt.setString(1, memberID);
		        pstmt.setString(2, password);
		        pstmt.setString(3, name);
		        pstmt.setString(4, email);
		        
		        pstmt.executeUpdate();
		    } finally {
		        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		    }
		 
		%>   
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String bookName = request.getParameter("book_name");
		String bookLoc = request.getParameter("book_loc");
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pw = "1234";
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName(driver);
			
			con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement();
			String sql = "INSERT INTO book(book_id, book_name, book_loc)";
					sql += " VALUES (BOOK_SEQ.NEXTVAL, '" + bookName + "', '" + bookLoc + "')";
			int result = stmt.executeUpdate(sql);
			
			if(result == 1) {
				out.print("INSERT success!!");
			} else {
				out.print("INSERT fail!!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) stmt.close();
				if(con != null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
