package com.bluemsun.Filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FilterDemo02 implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----过滤器02初始化----");
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
		System.out.println("FilterDemo02执行前！！！");
		filterChain.doFilter(servletRequest, servletResponse);
		System.out.println("FilterDemo02执行后！！！");
		
	}
	
	@Override
	public void destroy() {
		System.out.println("----过滤器02销毁----");
		
	}
}
