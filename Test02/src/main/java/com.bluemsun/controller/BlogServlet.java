package com.bluemsun.controller;

import com.bluemsun.entity.Blog;
import com.bluemsun.service.BlogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sunny
 */
public class BlogServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String state = req.getParameter("state");
		switch (state){
			case "addBlog":
				addBlogServlet(req,resp);
				break;
			case "deleteBlog":
				deleteBlogServlet(req,resp);
				break;
			case "updateBlog":
				updateBlogServlet(req,resp);
				break;
			case "queryBlog":
				queryBlogServlet(req,resp);
				break;
			case "findBlog":
				findBlog(req,resp);
				break;
		}
	}
	//查找一个
	
	private void findBlog(HttpServletRequest req, HttpServletResponse resp) {
		Blog blog=new Blog();
		blog.setUsername(req.getParameter("username"));
		blog.setTitle(req.getParameter("title"));
		
		BlogService blogService=new BlogService();
		List<Blog> blogs=new ArrayList<>();
		blogs=blogService.findBlog(blog);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		JSONArray jsonArray=JSONArray.fromObject(blogs);
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addBlogServlet(HttpServletRequest req, HttpServletResponse resp) {
		Blog blog=new Blog();
		blog.setContent(req.getParameter("content"));
		blog.setKeyWord1(req.getParameter("keyWord"));
		blog.setTitle(req.getParameter("title"));
		blog.setUsername(req.getParameter("username"));
		blog.setDate(req.getParameter("date"));
		blog.setKeyWord1(req.getParameter("keyWord1"));
		blog.setKeyWord2(req.getParameter("keyWord2"));
		BlogService blogService=new BlogService();
		int id=blogService.findId(blog);
		blog.setId(id);
		int flag=blogService.add(blog);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
		}
		else{
			jsonObject.put("flag","false");
		}
		try {
			resp.getWriter().write(String.valueOf(jsonObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void deleteBlogServlet(HttpServletRequest req, HttpServletResponse resp) {
		Blog blog=new Blog();
		blog.setTitle(req.getParameter("title"));
		blog.setUsername(req.getParameter("username"));
		BlogService blogService=new BlogService();
		int id=blogService.findId(blog);
		int flag=blogService.delete(id);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
		}
		else{
			jsonObject.put("flag","false");
		}
		try {
			resp.getWriter().write(String.valueOf(jsonObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void updateBlogServlet(HttpServletRequest req, HttpServletResponse resp) {
		Blog blog=new Blog();
		blog.setContent(req.getParameter("content"));
		blog.setDate(req.getParameter("date"));
		blog.setKeyWord1(req.getParameter("keyWord1"));
		blog.setKeyWord2(req.getParameter("keyWord2"));
		blog.setTitle(req.getParameter("title"));
		blog.setUsername(req.getParameter("username"));
		BlogService blogService=new BlogService();
		int id=blogService.findId(blog);
		blog.setId(id);
		int flag=blogService.update(blog);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
		}
		else{
			jsonObject.put("flag","false");
		}
		try {
			resp.getWriter().write(String.valueOf(jsonObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void queryBlogServlet(HttpServletRequest req, HttpServletResponse resp) {
		List<Blog> list=new ArrayList<>();
		BlogService blogService=new BlogService();
		list=blogService.query();
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
