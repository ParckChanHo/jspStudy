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
	   Statement와 PreparedStatement의 차이점
	   
	   1) Statement의 주요 메서드
	    (1) executeQuery() ==> select() 문을 수행할 때 사용하며 Resultset 클래스의 객체를 반환한다. 해당 select문의 결과에 해당하는 데이터에 접근할 수 있는
	        방법을 제공한다. 
	     String sql = "SELECT * FROM book";   
	     ResultSet res = stmt.executeQuery(sql); 
	    (2) executeUpdate() ==> insert(),update(),delete()와 같은 문을 수행할 때 사용한다. 반환값은 int 형이고 처리된 데이터의 수를 반환한다.
	   
	   2) PreparedStatement의 주요 메서드
	      PreparedStatement는 SQL문을 미리 만들어두고 변수를 따로 입력하는 방식으로 효율성이나 유지 보수 측면에서 유리한 구조이며 SQL Injection 공격까지 방어가 가능하다.
	         그리고 기본적으로 Statement를 상속을 받기 때문에 Statement 클래스의 메서드도 모두 사용이 가능하다.
	         
	     (1) executeQuery() ==> select() 문을 수행할 때 사용하며 Resultset 클래스의 객체를 반환한다. 해당 select문의 결과에 해당하는 데이터에 접근할 수 있는
	         방법을 제공한다.
	      String sql = "SELECT * FROM book";   
	      ResultSet res = pstmt.executeQuery(sql);   
	     (2) executeUpdate() ==> insert(),update(),delete()와 같은 문을 수행할 때 사용한다. 반환값은 int 형이고 처리된 데이터의 수를 반환한다.  
	     
	     PreparedStatement 사용법
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
