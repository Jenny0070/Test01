package com.bluemsun.controller;

import com.bluemsun.entity.Inform;
import com.bluemsun.entity.News;
import com.bluemsun.entity.Page;
import com.bluemsun.service.InformService;
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
public class InformServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
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
			case "addInform":
				addInform(req,resp);
				break;
			case "deleteInform":
				deleteInform(req,resp);
				break;
			case "updateInform":
				updateInform(req,resp);
				break;
			case "queryInform":
				queryInform(req,resp);
				break;
		}
	}
	
	private void pagination(HttpServletRequest req, HttpServletResponse resp) {
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		int pageSize=5;
		InformService informService=new InformService();
		Page page=informService.getPageService(pageNum,pageSize);
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
	
	private void addInform(HttpServletRequest req, HttpServletResponse resp) {
		Inform inform=new Inform();
		inform.setTitle(req.getParameter("title"));
		inform.setDate(req.getParameter("date"));
		inform.setContent(req.getParameter("content"));
		inform.setHits(req.getParameter("hits"));
		inform.setIssuer(req.getParameter("issuer"));
		inform.setLimit(req.getParameter("limit"));
		inform.setKeyWord(req.getParameter("keyWord"));
		
		InformService informService=new InformService();
		int flag=informService.add(inform);
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
	
	private void deleteInform(HttpServletRequest req, HttpServletResponse resp) {
		InformService informService=new InformService();
		int id=informService.findIdByTitle(req.getParameter("title"));
		
		int flag=informService.delete(id);
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
	
	private void updateInform(HttpServletRequest req, HttpServletResponse resp) {
		Inform inform=new Inform();
		inform.setTitle(req.getParameter("title"));
		inform.setDate(req.getParameter("date"));
		inform.setContent(req.getParameter("content"));
		inform.setHits(req.getParameter("hits"));
		inform.setIssuer(req.getParameter("issuer"));
		inform.setLimit(req.getParameter("limit"));
		inform.setKeyWord(req.getParameter("keyWord"));
		
		InformService informService=new InformService();
		inform.setId(informService.findIdByTitle(req.getParameter("title")));
		
		int flag=informService.update(inform);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (flag>0){
			jsonObject.put("flag","修改成功");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			jsonObject.put("flag","修改失败");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void queryInform(HttpServletRequest req, HttpServletResponse resp) {
		
		
		
		List<Inform> list=new ArrayList<>();
		InformService informService=new InformService();
		list=informService.queryAll();
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
	
}

