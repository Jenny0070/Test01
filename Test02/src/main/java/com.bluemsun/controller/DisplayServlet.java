package com.bluemsun.controller;

import com.bluemsun.entity.Display;
import com.bluemsun.service.DisplayService;
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
public class DisplayServlet extends HttpServlet {
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
			case "addWorks":
				addWorksServlet(req,resp);
				break;
			case "deleteWorks":
				deleteWorksServlet(req,resp);
				break;
			case "updateWorks":
				updateWorksServlet(req,resp);
				break;
			case "queryWorks":
				queryWorksServlet(req,resp);
				break;
			default:
				break;
		}
	}
	private void addWorksServlet(HttpServletRequest req, HttpServletResponse resp) {
		Display display=new Display();
		display.setUsername(req.getParameter("username"));
		display.setDescription(req.getParameter("description"));
		display.setPictures(req.getParameter("pictures"));
		display.setTitle(req.getParameter("title"));
		
		DisplayService displayService=new DisplayService();
		
		int flag=displayService.add(display);
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
	
	private void deleteWorksServlet(HttpServletRequest req, HttpServletResponse resp) {
		DisplayService displayService=new DisplayService();
		int id=displayService.findIdByTitle(req.getParameter("title"));
		int flag=displayService.delete(id);
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
	
	private void updateWorksServlet(HttpServletRequest req, HttpServletResponse resp) {
		Display display=new Display();
		display.setUsername(req.getParameter("username"));
		display.setDescription(req.getParameter("description"));
		display.setPictures(req.getParameter("pictures"));
		display.setTitle(req.getParameter("title"));
		
		DisplayService displayService=new DisplayService();
		display.setId(displayService.findIdByTitle(req.getParameter("title")));
		int flag=displayService.update(display);
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
	
	private void queryWorksServlet(HttpServletRequest req, HttpServletResponse resp) {
		DisplayService displayService=new DisplayService();
		List<Display> list=new ArrayList<>();
		list = displayService.queryAll();
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
