package com.bluemsun.controller;

import com.bluemsun.entity.CommentBoard;
import com.bluemsun.service.CommentBoardService;
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
public class CommentBoardServlet extends HttpServlet {
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
			case "addMessage":
				addMessageServlet(req,resp);
				break;
			case "deleteMessage":
				deleteMessageServlet(req,resp);
				break;
			case "updateMessage":
				updateMessageServlet(req,resp);
				break;
			case "queryMessage":
				queryMessageServlet(req,resp);
				break;
			case "checkCommentBoard":
				checkCommentBoard(req,resp);
				break;
			case "queryCheck":
				queryCheck(req,resp);
				break;
			default:	break;
		}
	}
	
	
	
	
	private void addMessageServlet(HttpServletRequest req, HttpServletResponse resp) {
		CommentBoard commentBoard=new CommentBoard();
		commentBoard.setContent(req.getParameter("content"));
		commentBoard.setKeyWord(req.getParameter("keyWord"));
		commentBoard.setUsername(req.getParameter("title"));
		commentBoard.setUsername(req.getParameter("username"));
		commentBoard.setDate(req.getParameter("date"));
		
		CommentBoardService commentBoardService=new CommentBoardService();
		int id=commentBoardService.findId(commentBoard);
		commentBoard.setId(id);
		int flag=commentBoardService.add(commentBoard);
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
	
	
	private void deleteMessageServlet(HttpServletRequest req, HttpServletResponse resp) {
		CommentBoard commentBoard=new CommentBoard();
		
		commentBoard.setContent(req.getParameter("content"));
		commentBoard.setKeyWord(req.getParameter("keyWord"));
		commentBoard.setTarget(req.getParameter("title"));
		commentBoard.setUsername(req.getParameter("username"));
		commentBoard.setDate(req.getParameter("date"));
		
		
		CommentBoardService commentBoardService=new CommentBoardService();
		int id=commentBoardService.findId(commentBoard);
		int flag=commentBoardService.delete(id);
		
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
	
	
	private void updateMessageServlet(HttpServletRequest req, HttpServletResponse resp) {
		CommentBoard commentBoard=new CommentBoard();
		commentBoard.setContent(req.getParameter("content"));
		commentBoard.setKeyWord(req.getParameter("keyWord"));
		commentBoard.setTarget(req.getParameter("title"));
		commentBoard.setUsername(req.getParameter("username"));
		commentBoard.setDate(req.getParameter("date"));
		
		CommentBoardService commentBoardService=new CommentBoardService();
		int id=commentBoardService.findId(commentBoard);
		commentBoard.setId(id);
		
		int flag=commentBoardService.update(commentBoard);
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
	
	private void queryMessageServlet(HttpServletRequest req, HttpServletResponse resp) {
		List<CommentBoard> list=new ArrayList<>();
		CommentBoardService commentBoardService=new CommentBoardService();
		list=commentBoardService.queryAll();
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//审核
	
	private void checkCommentBoard(HttpServletRequest req, HttpServletResponse resp) {
		CommentBoard commentBoard=new CommentBoard();
		commentBoard.setUsername(req.getParameter("username"));
		commentBoard.setTitle(req.getParameter("title"));
		String check=req.getParameter("check");
		
		CommentBoardService commentBoardService=new CommentBoardService();
		int flag=commentBoardService.checkCommentBoard(check,commentBoard);
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
	
	//只能展示审核通过的
	
	private void queryCheck(HttpServletRequest req, HttpServletResponse resp) {
		List<CommentBoard> commentBoards=new ArrayList<>();
		CommentBoardService commentBoardService=new CommentBoardService();
		commentBoards=commentBoardService.queryCheck();
		JSONArray jsonArray=JSONArray.fromObject(commentBoards);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
