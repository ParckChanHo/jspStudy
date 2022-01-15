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

// ���� JSP (renew ver) - ���� ���α׷��Ӹ� ���� ���� ===> 6�� : Servlet ����
// ���� JSP (renew ver) - ���� ���α׷��Ӹ� ���� ���� ===> 8�� : Servlet Life-Cycle
public class ServletEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /* 
	  	Servlet�� ���� �ֱ� ==> �� Container�� ��Ĺ�� �ڵ����� ȣ���� �ش�.
	  	 1) ������ ���� ��û�� ���� Servlet ��ü(ServletEx ��ü)�� ������ �ȴ�. (���� �ѹ�)
	  	 2) @PostConstruct() �޼ҵ带 ȣ���� �ش�.
	     3) init() �޼ҵ� ȣ��(���� �ѹ�) ==> Servlet�� �ʱ�ȭ �����ش�.
	     4) service() �޼ҵ� ȣ�� ==> Servlet�� �������� ��û�� ó���ϵ��� �Ѵ�.( �׷��� Ư���� ������ ������ service() �޼ҵ带 �������� �ʴ´�.)
	     (service() �޼ҵ�� Ư�� HTTP ��û(GET, POST ��)�� ó���ϴ� �޼���(doGet(), doPost() ��)�� �ڵ����� ȣ���Ѵ�.)
	     5) destroy() �޼ҵ� ȣ��
	     6) @PreDestroy() �޼ҵ� ȣ��
	     * 
	     *  service(), doGet(), doPost() ==> ��û�� �Ź� ȣ���� �ȴ�.
	     *  ������ ���� ��û�� ���� ��ü�� ������� �޸𸮿� �ε��ǰ� ���� ��û�ÿ��� ������ ��ü�� ��Ȱ�� -> ���ۼӵ��� ������
	     *  
	     *   ���� ���
	        -- postConstruct() -- 
				init called
				service called
				doGet called
				service called <== F5(���� ��ħ)�� ������ ��. init() �޼ҵ�� ȣ�� ���� �ʴ� ���� �� �� �ִ�.
				doGet called
     */
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEx() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@PostConstruct // 1��
	public void postConstruct() {
		System.out.println(" -- postConstruct() -- ");
	}

    
    @Override
    public void init(ServletConfig config) throws ServletException { // 2�� (���� �ѹ���)
        System.out.println("init called");
        super.init();
    }
    
    // 3��
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("service called"); // ��û �� �Ź� ȣ��
        super.service(request, response);
    }

    // 3��
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet called"); // ��û �� �Ź� ȣ��
    }

    // 3��
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost called"); // ��û �� �Ź� ȣ��
    }
    
    // 4��
    @Override
    public void destroy() { // ������ �� ���� �ڿ� ������ ���ؼ� ȣ��ȴ�.
        System.out.println("destroy called");
        super.destroy();
    }
    
    // 5��
	@PreDestroy
	public void preDestory() {
		System.out.println(" -- preDestory() -- ");
	}

}
