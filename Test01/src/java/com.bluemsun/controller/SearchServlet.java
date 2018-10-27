package com.bluemsun.controller;

import com.bluemsun.entity.*;
import com.bluemsun.service.SearchService;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String state=req.getParameter("state");
		
		switch (state){
			case "userSearch":
				userSearch(req,resp);
				break;
			case "newsSearch":
				newsSearch(req,resp);
				break;
			case "displaySearch":
				displaySearch(req,resp);
				break;
			case "commentBoardSearch":
				commentBoardSearch(req,resp);
				break;
			case "blogSearch":
				blogSearch(req,resp);
				break;
			
		}
	}
	
	private void userSearch(HttpServletRequest req, HttpServletResponse resp) {
		SearchService searchService=new SearchService();
		List<User> userList=new ArrayList<>();
		userList=searchService.userSearch(req.getParameter("search"));
		JSONArray jsonArray= JSONArray.fromObject(userList);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void newsSearch(HttpServletRequest req, HttpServletResponse resp) {
		SearchService searchService=new SearchService();
		List<News> newsList=new ArrayList<>();
		newsList=searchService.newsSearch(req.getParameter("search"));
		JSONArray jsonArray= JSONArray.fromObject(newsList);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void displaySearch(HttpServletRequest req, HttpServletResponse resp) {
		SearchService searchService=new SearchService();
		List<Display> displayList=new ArrayList<>();
		displayList=searchService.displaySearch(req.getParameter("search"));
		JSONArray jsonArray= JSONArray.fromObject(displayList);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void commentBoardSearch(HttpServletRequest req, HttpServletResponse resp) {
		SearchService searchService=new SearchService();
		List<CommentBoard> commentBoardList=new ArrayList<>();
		commentBoardList=searchService.commentBoardSearch(req.getParameter("search"));
		JSONArray jsonArray= JSONArray.fromObject(commentBoardList);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void blogSearch(HttpServletRequest req, HttpServletResponse resp) {
		SearchService searchService=new SearchService();
		List<Blog> blogList=new ArrayList<>();
		blogList=searchService.blogSearch(req.getParameter("search"));
		JSONArray jsonArray= JSONArray.fromObject(blogList);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
