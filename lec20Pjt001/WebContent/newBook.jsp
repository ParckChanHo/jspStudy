<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="newBook" method="post">
		book name : <input type="text" name="book_name"></br> 
		book location : <input type="text" name="book_loc"></br> <input type="submit"
			value="book register">
	</form>

</body>
</html>

<!-- 
	오라클 커넥션 풀(DBCP) 사용하기
	1번째 C:\Program Files\Java\jre1.8.0_251\lib\ext 에 ojdbc6.jar 파일 붙여넣기
	2번째 Servers 폴더 - context.xml 파일에 다음과 같은 문장을 적어놓기
	
	<Resource
       auth = "Container"
       driverClassName = "oracle.jdbc.driver.OracleDriver"
       url = "jdbc:oracle:thin:@localhost:1521:orcl"
       username = "scott"
       password = "1234"
       name = "jdbc/Oracle11g"
       type = "javax.sql.DataSource"
       maxActive = "4"
       maxWait = "10000" 
     />
	
	커넥션 풀(Connection Pool)
 	 --  커넥션 풀(DBCP) --

	커넥션 풀이란 데이터베이스와 연결된 커넥션을 미리 만들어서 플속에 저장해 두고 있다가 필요할 때 커넥션을 풀에서 쓰고(대여한다.) 다시 풀에 반환하는 기법을 말한다.
	웹 프로그램에서는 데이터베이스의 환경설정과 연결 관리 등을 따로 XML파일이나 속성 파일을 사용해서 관리하고, 이렇게 설정된 정보를 이름을 사용하여 획득하는 방법을 사용한다.
	- 웹 컨테이너(tomcat)가 실행되면 커넥션(connection) 객체를 미리 풀에 생성해 둔다.
	- DB와 연결된 커넥션(connection)을 미리 생성해서 풀 속에 저장해 두고 있다가 필요할 때에 가져다 쓰고 반환한다.
	- DB와 연결된 커넥션(connection)을 미리 생성해두었기 때문에 데이터베이스에 부하를 줄이고 유동적으로 연결을 관리 할 수 있다.
	이렇게 풀 속에 미리 생성되어 있는 커넥션을 가져다가 사용(대여한다.)하고, 사용이 끝나면 커넥션을 풀에 반환한다.

	2) JDBC를 사용하지 않고 커넥션 풀(DBCP)을 사용하는 이유 :
 	JDBC를 통하여 DB에 연결하기 위해서는 드라이버(Driver)를 로드하고 커넥션(connection) 객체를 받아와야 한다. 
 	또한 JDBC를 사용하면 사용자가 요청을 할 때마다 매번 드라이버를 로드하고 커넥션 객체를 생성하여 연결하고 종료하기 때문에 매우 비효율적이다. 
 	이런 문제를 해결하기 위해서 커넥션풀(DBCP)을 사용한다.

	3) 커넥션풀(DBCP)의 특징
	 (1) 속에 미리 커넥션이 생성되어 있기 때문에 커넥션을 생성하는 데 드는 연결 시간이 소비되지 않는다.
	 (2) 커넥션을 계속해서 재사용하기 때문에 생성되는 커넥션 수가 많지 않다.
	 (3) 커넥션 풀을 사용하면 커넥션을 생성하고 닫는 시간이 소모되지 않기 때문에 그만큼 어플리케이션의 실행 속도가
	     빨라지며, 또한 한 번에 생성될 수 있는 커넥션 수를 제어하기 때문에 동시 접속자 수가 몰려도 웹 어플리케이션이 쉽게 다운되지 않는다.
	
	4) 커넥션풀(DBCP)의 동시 접속자 처리 방법 :
 
	    커넥션 풀에서 생성되어 있는 커넥션의 갯수는 한정적이다. 
	    커넥션 풀은 누군자 접속하면 커넥션 풀에 남아 있는 커넥션을 제공하는 식이다. 하지만 남아있는 커넥션이 없을 경우 해당 클라이언트는 대기 상태로 전환이 되고, 
	    커넥션이 반환되면 대기하고 있는 순서대로 커넥션이 제공된다.

 -->