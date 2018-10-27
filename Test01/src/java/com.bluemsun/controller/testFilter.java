package com.bluemsun.controller;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class testFilter implements Filter {
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}
	
	/**
	 * 只有直接访问对应页面时才可出现“没有访问权限”，从别的路径进入，过滤器不起作用？？？？？？
	 * @param servletRequest
	 * @param servletResponse
	 * @param filterChain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		//对request和response进行一些预处理
		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html;charset=UTF-8");
		
		System.out.println("FilterDemo01执行前！！！");
		//获取session对象
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		String username= (String) session.getAttribute("username");
		String password= (String) session.getAttribute("password");
		//让目标资源执行，放行
		if (password == null || "".equals(password)) {
			System.out.println("没有访问权限 ");
			response.sendRedirect("loginPage.jsp");
		} else {
			filterChain.doFilter(request, response);
		}
		System.out.println("FilterDemo01执行后！！！");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("----过滤器销毁----");
		
	}
}
