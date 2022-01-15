package com.servlet;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEx
 */
@WebServlet(name="ServletEx", urlPatterns = {"/SE","/SE2"})

// 실전 JSP (renew ver) - 신입 프로그래머를 위한 강좌 ===> 6강 : Servlet 맵핑
// 실전 JSP (renew ver) - 신입 프로그래머를 위한 강좌 ===> 8강 : Servlet Life-Cycle
public class ServletEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /* 
	  	Servlet의 생명 주기 ==> 웹 Container인 톰캣이 자동으로 호출해 준다.
	  	 1) 유저로 부터 요청이 오면 Servlet 객체(ServletEx 객체)가 생성이 된다. (최초 한번)
	  	 2) @PostConstruct() 메소드를 호출해 준다.
	     3) init() 메소드 호출(최초 한번) ==> Servlet을 초기화 시켜준다.
	     4) service() 메소드 호출 ==> Servlet이 브라우저의 요청을 처리하도록 한다.( 그러나 특별한 이유가 없으면 service() 메소드를 정의하지 않는다.)
	     (service() 메소드는 특정 HTTP 요청(GET, POST 등)을 처리하는 메서드(doGet(), doPost() 등)를 자동으로 호출한다.)
	     5) destroy() 메소드 호출
	     6) @PreDestroy() 메소드 호출
	     * 
	     *  service(), doGet(), doPost() ==> 요청시 매번 호출이 된다.
	     *  서블릿은 최초 요청시 서블릿 객체가 만들어져 메모리에 로딩되고 이후 요청시에는 기존의 객체를 재활용 -> 동작속도가 빠르다
	     *  
	     *   실행 결과
	        -- postConstruct() -- 
				init called
				service called
				doGet called
				service called <== F5(새로 고침)를 눌렀을 때. init() 메소드는 호출 되지 않는 것을 볼 수 있다.
				doGet called
     */
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@PostConstruct // 1번
	public void postConstruct() {
		System.out.println(" -- postConstruct() -- ");
	}

    
    @Override
    public void init(ServletConfig config) throws ServletException { // 2번 (최초 한번만)
        System.out.println("init called");
        super.init();
    }
    
    // 3번
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("service called"); // 요청 시 매번 호출
        super.service(request, response);
    }

    // 3번
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet called"); // 요청 시 매번 호출
    }

    // 3번
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called"); // 요청 시 매번 호출
    }
    
    // 4번
    @Override
    public void destroy() { // 마지막 한 번만 자원 해제를 위해서 호출된다.
        System.out.println("destroy called");
        super.destroy();
    }
    
    // 5번
	@PreDestroy
	public void preDestory() {
		System.out.println(" -- preDestory() -- ");
	}

}
