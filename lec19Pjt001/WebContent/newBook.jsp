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
			book location : <input type="text" name="book_loc"></br>
			<input type="submit" value="book register">
		</form>
		
    </body>
</html>


<!-- 
	DAO(Data Access Object)  VS DTO (Data Transfer Object)

	1) DAO(Data Access Object) : ex) BookDAO.java
	데이터베이스의 데이터에 접근하기 위해 생성하는 객체로써 데이터베이스에 접근하기 위한 로직과 비즈니스 로직을 분리하기 위해 사용한다. 
	간단하게 말하자면 DAO는 DB에 접속하는 작업과 DB에 있는 데이터를  CRUD(생성, 읽기, 갱신, 삭제) 작업을 시행하는 클래스라고 생각하면 된다.
	
	JSP 및 Servlet 페이지 내에 기술하여 사용할 수 있지만, 코드의 간결화 및 모듈화, 유지보수 등의 목적을 위해 별도의 DAO 클래스를 생성하여 사용하는 것이 좋다.
	
	한 줄 요약 : DAO는 DB를 사용하여 데이터를  CRUD(생성, 읽기, 갱신, 삭제) 하는 기능을 전담하는 오브젝트이다.

	2) DTO(Data Transfer Object) : ex) BookDTO.java
	계층간 데이터 교환을 하기 위해 사용하는 객체로, DTO는 로직을 가지지 않는 순수한 데이터 객체(getter & setter 만 가진 클래스)이다.
	DB에 있는 영역을 자바로 변환시켜 주는 것이다.
	
	DTO의 예시이다.

	public class PersonDTO {
	
	    private String name;
	    private int age;
	
	    public String getName() {
	        return name;
	    }
	
	    public void setName(String name) {
	        this.name = name;
	    }
	
	    public int getAge() {
	        return age;
	    }
	
	    public void setAge(int age) {
	        this.age = age;
	    }
	}
 -->