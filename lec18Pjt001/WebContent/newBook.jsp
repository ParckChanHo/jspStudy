<%@ page language="java" contentType="text/html; charset=UTF-8" 
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>
    <body>
		<!-- http://localhost:8080/lec18Pjt001/newBook.jsp 이 jsp를 실행했을 떄
		 NewBook.java를 실행했을 때 ===> http://localhost:8080/lec18Pjt001/newBook
		 -->
		<!-- <form action="newBook" method="post">
			book name : <input type="text" name="book_name"></br>
			book location : <input type="text" name="book_loc"></br>
			<input type="submit" value="book register">
		</form> -->
		
		<form action="modifyBook" method="post">
			수정할 book name : <input type="text" name="book_name"></br>
			수정할 book location : <input type="text" name="book_loc"></br>
			<input type="submit" value="book register">
		</form>
    </body>
</html>

<!-- 
	create table book (
    book_id number(4) primary key, /* 책의 아이디 */
    book_name varchar2(20), /* 책의 이름 */
    book_loc varchar2(20) /* 책의 위치 */
   );

	-- 시퀀스 생성 --
	-- 오라클에서는 자동 증가 컬럼을 사용할 수가 없다. 오라클에서는 컬럼의 값을 증가시키기 위해서는
	-- MAX(컬럼) + 1 또는 시퀀스를 사용하여 일련번호를 부여해야 한다.
	
	create SEQUENCE book_seq; -- 옵션이 없이 시퀀스 생성하기
	CREATE SEQUENCE book_seq
	       INCREMENT BY 1 -- 증가값 : 1
	       START WITH 1;  -- 시작값 : 1
	
	-- 테이블 초기화 시키기
	delete from book;
	TRUNCATE table book;
	
	-- 시퀀스 삭제하기
	drop SEQUENCE book_seq;
	
	오라클 테이블에는 데이터가 순서대로 저장되지 않습니다.
	또한 데이터가 순서대로 저장되어 있다고 하더라도 저장되어 있는 순서대로의 조회를 보장할 수 없습니다. 따라서
	정렬된 결과를 원한다면 반드시 정렬 구문(Order By)을 사용하셔야 합니다.
 -->