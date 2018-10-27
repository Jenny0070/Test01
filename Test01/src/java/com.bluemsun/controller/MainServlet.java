package com.bluemsun.controller;

import com.bluemsun.dao.NewsDao.NewsDao;
import com.bluemsun.dao.UserDao.UserDao;
import com.bluemsun.dao.UserDao.UserDaoImpl;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;
import com.bluemsun.service.NewsService;
import com.bluemsun.service.UserService;
import javafx.scene.control.Pagination;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//如若没有下面这句话，则需要自己在js中进行解析
		//resp.setContentType("application/json;charset=ust-8");
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		String state = req.getParameter("state");
		
		switch (state){
			case "loginPage":
				loginPage(req,resp);
				break;
			case "registerPage":
				registerPage(req,resp);
				break;
			case "queryAll":
				queryAll(req,resp);
				break;
			case "delete":
				delete(req,resp);
				break;
			case  "update":
				update(req,resp);
				break;
			case "pagination":
				pagination(req,resp);
				break;
		}
		
	}
	//分页
	private void pagination(HttpServletRequest req, HttpServletResponse resp) {
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		int pageSize=5;
		NewsService ts=new NewsService();
		Page page=ts.getPageService(pageNum,pageSize);
		req.setAttribute("page",page);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonObject));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改————后台已实现，前台无传参，无法实现
	private void update(HttpServletRequest req, HttpServletResponse resp) {
		//获得id
		UserService userService=new UserService();
		int id=userService.findIdByUsername(req.getParameter("username"));
		int flag=0;
		User user=new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setEmail(req.getParameter("email"));
		user.setGender(req.getParameter("gender"));
		user.setIdentity(req.getParameter("identity"));
		user.setPhoneNum(req.getParameter("phoneNum"));
		user.setId(id);
		
		flag=userService.updateById(user);
		if (flag>0){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","true");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","false");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	//删除————后台已实现，前台无传参，无法实现
	private void delete(HttpServletRequest req, HttpServletResponse resp) {
		int flag=0;
		int id= Integer.parseInt(req.getParameter("id"));
		UserService userService=new UserService();
		flag=userService.deleteById(id);
		if (flag>0){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","true");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","false");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			try {
				resp.getWriter().write(String.valueOf(jsonObject));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//查询所有
	private void queryAll(HttpServletRequest req, HttpServletResponse resp) {
		List<User> list=new ArrayList<>();
		UserService userService=new UserService();
		list=userService.queryAll();
		//json对象
//		JSONObject jsonObject=new JSONObject(list);
////		jsonObject.put("username","zhangsan");
		//json数组<-list
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
	//增加
	
	private void registerPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		User user=new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setEmail(req.getParameter("email"));
		user.setGender(req.getParameter("gender"));
		user.setIdentity(req.getParameter("identity"));
		user.setPhoneNum(req.getParameter("phoneNum"));
		
		UserService userService=new UserService();
		int flag=userService.addUser(user);
		if (flag>0){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","true");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().write(String.valueOf(jsonObject));
		}
		else{
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("flag","false");
			resp.setContentType("application/json;charset=utf-8");
			resp.setContentType("text/json;charset=utf-8");
			resp.getWriter().write(String.valueOf(jsonObject));
		}
		
	}
	//登录
	private void loginPage(HttpServletRequest req, HttpServletResponse resp) {
		User user=new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		UserService userService=new UserService();
		List list=userService.findUser(user);
		
		//System.out.println(list);
		
		if(list!=null && !list.isEmpty()){
			
			try {
				req.getRequestDispatcher("/success.jsp").forward(req,resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				req.getRequestDispatcher("/fail.jsp").forward(req,resp);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
	}
}
