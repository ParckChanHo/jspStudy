package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 오라클 데이터 베이스에 연결하고 select, insert , update , delete 작업을 실행시켜 주는 클래스 입니다.
public class MemberDAO {

	// Connection pool(DBCP,커넥션 풀) 사용하기

	// 오라클에 접속하는 소스를 작성
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pw = "1234";

	ResultSet rs; // 데이터 베이스의 테이블의 결과를 리턴 받아 자바에 저장해 주는 객체
	Connection conn;// 데이터베이스에 접근할수 있도록 설정
	PreparedStatement pstmt;// 데이터 베이스에서 쿼리를 실행시켜주는 객체

	// 커넥션 풀을 이용하여 데이터 베이스에 접근하기
	public void getcon() {

		try {
			// 외부에서 데이터를 읽어야 하기에
			Context initctx = new InitialContext();

			// 톰캣 서버에 정보를 담아 놓은 곳 으로 이동
			// java:comp/env ==> 이 부분은 항상 똑같다.(그러니깐 외우기!!!)
			Context envctx = (Context) initctx.lookup("java:comp/env");

			// 데이터 베이스 객체를 선언
			// jdbc/Oracle11g ==> name에 해당된다.
			DataSource ds = (DataSource) envctx.lookup("jdbc/Oracle11g");

			// 데이터 소스를 기준으로 커넥션을 연결해 주세요
			conn = ds.getConnection();

		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} end finally*/

	} // end getcon()
	
	// JDBC를 이용하여 데이터 베이스에 접근하기
	public void JdbcGetcon() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);
			String sql = "select * from member";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				System.out.print("ID: "+rs.getString("id")+"\n");
				System.out.print("PASS1: "+rs.getString("pass1")+"\n");
				System.out.print("EMAIL: "+rs.getString("email")+"\n");
				System.out.print("TEL: "+rs.getString("tel")+"\n");
				System.out.print("HOBBY: "+rs.getString("hobby")+"\n");
				System.out.print("JOB: "+rs.getString("job")+"\n");
				System.out.print("AGE: "+rs.getString("age")+"\n");
				System.out.print("INFO: "+rs.getString("info")+"\n");
				System.out.println("================================================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} /*finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally*/
	} // end JdbcGetcon()
	
	public int insertMember(MemberBean bean) {
		try {
			getcon();
			
			// 아이디가 있는지 먼저 확인하기!!
			// select * from member where id='abc2';
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			
			//쿼리를 실행후 결과를 저장
			rs = pstmt.executeQuery(); // select시 사용하는 메소드
			if(rs.next())
				return -1;
			
			sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//?에 맞게 데이터를 맵핑
			pstmt.setString(1,bean.getId());
			pstmt.setString(2,bean.getPass1());
			pstmt.setString(3,bean.getEmail());
			pstmt.setString(4,bean.getTel());
			pstmt.setString(5,bean.getHobby());
			pstmt.setString(6,bean.getJob());
			pstmt.setString(7,bean.getAge());
			pstmt.setString(8,bean.getInfo());
			//4.오라클에서 퀴리를 실행 하시오 
			pstmt.executeUpdate(); // insert,update,delete 시 사용하는 메소드 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		return 0;
	}	
	
	public ArrayList<MemberBean> getAllMember(){
		try {
			getcon();
			
			String sql = "select * from member";
			pstmt = conn.prepareStatement(sql);
			//쿼리를 실행후 결과를 저장
			rs = pstmt.executeQuery(); // select시 사용하는 메소드
			
			ArrayList<MemberBean> list = new ArrayList<MemberBean>();
			MemberBean bean;
			while(rs.next()) {
				bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPass1(rs.getString("pass1"));
				bean.setEmail(rs.getString("email"));
				bean.setTel(rs.getString("tel"));
				bean.setHobby(rs.getString("hobby"));
				bean.setJob(rs.getString("job"));
				bean.setAge(rs.getString("age"));
				bean.setInfo(rs.getString("info"));
				
				list.add(bean);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		
		return null; // 오류가 발생할 경우!!!
	}
	
	// 하나의 회원 정보 얻기
	public MemberBean getOneMember(String id) {
		try {
			getcon();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//쿼리를 실행후 결과를 저장
			rs = pstmt.executeQuery(); // select시 사용하는 메소드
			
			if(rs.next()) {
				MemberBean bean = new MemberBean();
				bean.setId(rs.getString("id"));
				bean.setPass1(rs.getString("pass1"));
				bean.setEmail(rs.getString("email"));
				bean.setTel(rs.getString("tel"));
				bean.setHobby(rs.getString("hobby"));
				bean.setJob(rs.getString("job"));
				bean.setAge(rs.getString("age"));
				bean.setInfo(rs.getString("info"));
				
				return bean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		
		return null;
	} // end getOneMember()
	
	// 비밀번호 얻기
	public String getPass(String id) {
		try {
			getcon();
			
			String sql = "select pass1 from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//쿼리를 실행후 결과를 저장
			rs = pstmt.executeQuery(); // select시 사용하는 메소드
			
			if(rs.next()) {
				return rs.getString("pass1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		
		return null;
	}
	
	// 수정하기
	public int update(MemberBean bean) {
		try {
			getcon();
			String sql = "update member set email = ? ,tel = ? where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getEmail());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getId());
			
			pstmt.executeUpdate(); // insert,update,delete 시 사용하는 메소드 
			return 1; // update에 성공한 경우
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		
		return -1; // update에 실패한 경우
	}
	
	// 삭제하기
	public int delete(String id) {
		try {
			getcon();
			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate(); // insert,update,delete 시 사용하는 메소드 
			return 1; // delete에 성공한 경우
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} // end finally
		
		return -1; // delete에 실패한 경우
	}
	
	
}
