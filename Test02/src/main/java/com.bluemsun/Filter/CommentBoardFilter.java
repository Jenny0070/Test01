package com.bluemsun.Filter;

import net.sf.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommentBoardFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("留言板过滤器初始化————————");
	}
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		servletRequest.setCharacterEncoding("UTF-8");
		servletResponse.setCharacterEncoding("UTF-8");
		servletResponse.setContentType("text/html;charset=UTF-8");
		
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;
		HttpSession session=request.getSession();
		
		String username= (String) session.getAttribute("username");
		String password= (String) session.getAttribute("password");
		//让目标资源执行，放行
		if (password == null || "".equals(password)) {
			response.getWriter().write("没有访问权限");
			
		} else {
			filterChain.doFilter(request, response);
		}

	}
	
	@Override
	public void destroy() {
		System.out.println("留言板过滤器销毁————————");
		
	}
}
