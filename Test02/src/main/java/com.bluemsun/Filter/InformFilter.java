package com.bluemsun.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class InformFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("部分通知可见的过滤");
		
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html;charset=UTF-8");
		
		System.out.println("InformFilter执行前！！！");
		//获取session对象
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		//让目标资源执行，放行
		if (password == null || "".equals(password)) {
			System.out.println("没有访问权限 ");
			response.sendRedirect("loginPage.jsp");
		} else {
			filterChain.doFilter(request, response);
			System.out.println("FilterDemo01执行后！！！");
			
		}
		
		
	}
}
