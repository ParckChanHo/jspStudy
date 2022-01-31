package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// ����Ŭ ������ ���̽��� �����ϰ� select, insert , update , delete �۾��� ������� �ִ� Ŭ���� �Դϴ�.
public class MemberDAO {

	// Connection pool(DBCP,Ŀ�ؼ� Ǯ) ����ϱ�

	// ����Ŭ�� �����ϴ� �ҽ��� �ۼ�
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String id = "scott";
	String pw = "1234";

	ResultSet rs; // ������ ���̽��� ���̺��� ����� ���� �޾� �ڹٿ� ������ �ִ� ��ü
	Connection conn;// �����ͺ��̽��� �����Ҽ� �ֵ��� ����
	PreparedStatement pstmt;// ������ ���̽����� ������ ��������ִ� ��ü

	// Ŀ�ؼ� Ǯ�� �̿��Ͽ� ������ ���̽��� �����ϱ�
	public void getcon() {

		try {
			// �ܺο��� �����͸� �о�� �ϱ⿡
			Context initctx = new InitialContext();

			// ��Ĺ ������ ������ ��� ���� �� ���� �̵�
			// java:comp/env ==> �� �κ��� �׻� �Ȱ���.(�׷��ϱ� �ܿ��!!!)
			Context envctx = (Context) initctx.lookup("java:comp/env");

			// ������ ���̽� ��ü�� ����
			// jdbc/Oracle11g ==> name�� �ش�ȴ�.
			DataSource ds = (DataSource) envctx.lookup("jdbc/Oracle11g");

			// ������ �ҽ��� �������� Ŀ�ؼ��� ������ �ּ���
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
	
	// JDBC�� �̿��Ͽ� ������ ���̽��� �����ϱ�
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
			
			// ���̵� �ִ��� ���� Ȯ���ϱ�!!
			// select * from member where id='abc2';
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getId());
			
			//������ ������ ����� ����
			rs = pstmt.executeQuery(); // select�� ����ϴ� �޼ҵ�
			if(rs.next())
				return -1;
			
			sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//?�� �°� �����͸� ����
			pstmt.setString(1,bean.getId());
			pstmt.setString(2,bean.getPass1());
			pstmt.setString(3,bean.getEmail());
			pstmt.setString(4,bean.getTel());
			pstmt.setString(5,bean.getHobby());
			pstmt.setString(6,bean.getJob());
			pstmt.setString(7,bean.getAge());
			pstmt.setString(8,bean.getInfo());
			//4.����Ŭ���� ������ ���� �Ͻÿ� 
			pstmt.executeUpdate(); // insert,update,delete �� ����ϴ� �޼ҵ� 
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
			//������ ������ ����� ����
			rs = pstmt.executeQuery(); // select�� ����ϴ� �޼ҵ�
			
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
		
		return null; // ������ �߻��� ���!!!
	}
	
	// �ϳ��� ȸ�� ���� ���
	public MemberBean getOneMember(String id) {
		try {
			getcon();
			
			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//������ ������ ����� ����
			rs = pstmt.executeQuery(); // select�� ����ϴ� �޼ҵ�
			
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
	
	// ��й�ȣ ���
	public String getPass(String id) {
		try {
			getcon();
			
			String sql = "select pass1 from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			//������ ������ ����� ����
			rs = pstmt.executeQuery(); // select�� ����ϴ� �޼ҵ�
			
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
	
	// �����ϱ�
	public int update(MemberBean bean) {
		try {
			getcon();
			String sql = "update member set email = ? ,tel = ? where id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getEmail());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getId());
			
			pstmt.executeUpdate(); // insert,update,delete �� ����ϴ� �޼ҵ� 
			return 1; // update�� ������ ���
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
		
		return -1; // update�� ������ ���
	}
	
	// �����ϱ�
	public int delete(String id) {
		try {
			getcon();
			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate(); // insert,update,delete �� ����ϴ� �޼ҵ� 
			return 1; // delete�� ������ ���
			
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
		
		return -1; // delete�� ������ ���
	}
	
	
}
