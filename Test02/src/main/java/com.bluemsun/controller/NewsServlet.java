package com.bluemsun.controller;

import com.bluemsun.entity.News;
import com.bluemsun.entity.Page;
import com.bluemsun.service.NewsService;
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
public class NewsServlet extends HttpServlet {

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
			case "pagination":
				pagination(req,resp);
			case "addNews":
				addNews(req,resp);
				break;
			case "deleteNews":
				deleteNewsServlet(req,resp);
				break;
			case "updateNews":
				updateNewsServlet(req,resp);
				break;
			case "queryOneNews":
				queryOneNews(req,resp);
				break;
			case "queryNews":
				queryNewsServlet(req,resp);
				break;
			case "queryOrderedByDate":
				queryOrderedByDate(req,resp);
				break;
			case "queryOrderedByHits":
				queryOrderedByHits(req,resp);
				break;
			default:
				break;
		}
	}

	
	//新闻分页
	
	private void pagination(HttpServletRequest req, HttpServletResponse resp) {
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		int pageSize=5;
		NewsService newsService=new NewsService();
		Page page=newsService.getPageService(pageNum,pageSize);
		List<News> list=new ArrayList<>();
		list=page.getPageList();
		
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		JSONArray jsonArray=JSONArray.fromObject(list);
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//增加
	
	private void addNews(HttpServletRequest req, HttpServletResponse resp) {
		News news=new News();
		news.setTitle(req.getParameter("title"));
		news.setDate(req.getParameter("date"));
		news.setPromulgator(req.getParameter("promulgator"));
		news.setType(req.getParameter("type"));
		news.setContent(req.getParameter("content"));
		news.setKeyWord(req.getParameter("keyWord"));
		
		NewsService newsService=new NewsService();
		int flag=newsService.add(news);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			jsonObject.put("flag","false");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//删除
	
	private void deleteNewsServlet(HttpServletRequest req, HttpServletResponse resp) {
		NewsService newsService=new NewsService();
		int id=newsService.findIdByTitle(req.getParameter("title"));
		
		int flag=newsService.delete(id);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			jsonObject.put("flag","false");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void updateNewsServlet(HttpServletRequest req, HttpServletResponse resp) {
		News news=new News();
		news.setTitle(req.getParameter("title"));
		news.setDate(req.getParameter("date"));
		news.setPromulgator(req.getParameter("promulgator"));
		news.setType(req.getParameter("type"));
		news.setContent(req.getParameter("content"));
		news.setKeyWord(req.getParameter("keyWord"));
		
		NewsService newsService=new NewsService();
		news.setId(newsService.findIdByTitle(req.getParameter("title")));
		
		int flag=newsService.update(news);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","true");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			jsonObject.put("flag","false");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void queryOneNews(HttpServletRequest req, HttpServletResponse resp) {
		News news=new News();
		news.setTitle(req.getParameter("title"));
		NewsService newsService=new NewsService();
		JSONObject jsonObject=new JSONObject();
		int flag=newsService.updateHits(news);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		List<News> list=newsService.queryByTitle(news);
		JSONArray jsonArray=JSONArray.fromObject(list);
		if (flag>0){
			jsonObject.put("flag","true");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
				resp.getWriter().write(String.valueOf(jsonArray));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			jsonObject.put("flag","false");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
				resp.getWriter().write(String.valueOf(jsonArray));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	private void queryNewsServlet(HttpServletRequest req, HttpServletResponse resp) {
		
		
		List<News> list=new ArrayList<>();
		NewsService newsService=new NewsService();
		list=newsService.queryAll();
		JSONArray jsonArray=JSONArray.fromObject(list);
		//json传数据的关键语句3
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//按热度进行排序
	
	private void queryOrderedByHits(HttpServletRequest req, HttpServletResponse resp) {
		List<News> list=new ArrayList<>();
		NewsService newsService=new NewsService();
		list=newsService.queryOrderedByHits();
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	按时间进行排序
	
	private void queryOrderedByDate(HttpServletRequest req, HttpServletResponse resp) {
		List<News> list=new ArrayList<>();
		NewsService newsService=new NewsService();
		list=newsService.queryOrderedByDate();
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
