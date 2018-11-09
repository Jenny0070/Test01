package com.bluemsun.controller;

import com.bluemsun.entity.Comment;
import com.bluemsun.service.CommentService;
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
public class CommentServlet extends HttpServlet {
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
			case "addComment":
				addComment(req,resp);
				break;
			case "deleteComment":
				deleteComment(req,resp);
				break;
			case "updateComment":
				updateComment(req,resp);
				break;
			case "queryComment":
				queryComment(req,resp);
				break;
			default:
				break;
				
		}
	}
	
	private void queryComment(HttpServletRequest req, HttpServletResponse resp) {
		CommentService commentService=new CommentService();
		List<Comment> comments=new ArrayList<>();
		comments=commentService.queryAll();
		JSONArray jsonArray=JSONArray.fromObject(comments);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateComment(HttpServletRequest req, HttpServletResponse resp) {
		Comment comment=new Comment();
		comment.setUsername(req.getParameter("username"));
		comment.setTitle(req.getParameter("title"));
		comment.setContent(req.getParameter("content"));
		comment.setDate(req.getParameter("date"));
		comment.setKeyWord(req.getParameter("keyWord"));
		comment.setTarget(req.getParameter("target"));
		
		int flag=0;
		CommentService commentService=new CommentService();
		flag=commentService.update(comment);
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
	
	private void deleteComment(HttpServletRequest req, HttpServletResponse resp) {
		Comment comment=new Comment();
		comment.setUsername(req.getParameter("username"));
		comment.setTitle(req.getParameter("title"));
		comment.setContent(req.getParameter("content"));
		comment.setDate(req.getParameter("date"));
		comment.setKeyWord(req.getParameter("keyWord"));
		comment.setTarget(req.getParameter("target"));
		
		int flag=0;
		CommentService commentService=new CommentService();
		int id=commentService.findId(comment);
		flag=commentService.delete(id);
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
	
	private void addComment(HttpServletRequest req, HttpServletResponse resp) {
		Comment comment=new Comment();
		comment.setUsername(req.getParameter("username"));
		comment.setTitle(req.getParameter("title"));
		comment.setContent(req.getParameter("content"));
		comment.setDate(req.getParameter("date"));
		comment.setKeyWord(req.getParameter("keyWord"));
		comment.setTarget(req.getParameter("target"));
		
		int flag=0;
		CommentService commentService=new CommentService();
		flag=commentService.add(comment);
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
}
