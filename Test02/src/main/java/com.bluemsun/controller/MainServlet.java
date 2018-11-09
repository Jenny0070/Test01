package com.bluemsun.controller;


import com.bluemsun.entity.NewMember;
import com.bluemsun.entity.User;
import com.bluemsun.service.UserService;
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
			case "findUser":
				findUser(req,resp);
				break;
			case "applicationState":
				applicationState(req,resp);
				break;
			case "queryApplicationState":
				queryApplicationState(req,resp);
				break;
			
			case "pagination":
				pagination(req,resp);
				break;
			case "newMember":
				newMemberApplication(req,resp);
				break;
			case "newMemberDelete":
				newMemberDelete(req,resp);
				break;
			case "newMemberQuery":
				newMemberQuery(req,resp);
				break;
			case "newMemberQuerySpecial":
				newMemberQuerySpecial(req,resp);
				break;
			case  "checkNewMember":
				checkNewMember(req,resp);
				break;
			case "checkMember":
				checkMember(req,resp);
				break;
			case "updateIdentity":
				updateIdentity(req,resp);
				break;
			case "queryMemberSpecial":
				queryMemberSpecial(req,resp);
				break;
			
			default:
				resp.getWriter().write("无方法正在执行");
				
		}
		
	}
	

	
	
	//申请表的开关
	
	
	//改变状态
	
	private void applicationState(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String state=req.getParameter("applicationState");
		UserService userService=new UserService();
		int flag=userService.updateApplicationState(state);
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
			resp.getWriter().write(String.valueOf(jsonObject));
		}
	}
	
	//查看状态即是否通过
	
	private void queryApplicationState(HttpServletRequest req, HttpServletResponse resp) {
		UserService userService=new UserService();
		String state=userService.queryApplicationState();
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if (state.equals("on")){
			jsonObject.put("state","true");
		}
		else{
			jsonObject.put("state","false");
		}
		try {
			resp.getWriter().write(String.valueOf(jsonObject));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 新用户
	 * @param req
	 * @param resp
	 */
	//新成员申请
	private void newMemberApplication(HttpServletRequest req, HttpServletResponse resp) {
		NewMember newMember=new NewMember();
		
		newMember.setStudentId(req.getParameter("studentId"));
		newMember.setUsername(req.getParameter("username"));
		newMember.setGender(req.getParameter("gender"));
		newMember.setNation(req.getParameter("nation"));
		newMember.setGrade(Integer.parseInt(req.getParameter("grade")));
		newMember.setBirth(req.getParameter("birth"));
		newMember.setMajor(req.getParameter("major"));
		newMember.setAcademy(req.getParameter("academy"));
		newMember.setMyQQ(req.getParameter("myQQ"));
		newMember.setEmail(req.getParameter("email"));
		newMember.setPhoneNum(req.getParameter("phoneNum"));
		newMember.setPicture(req.getParameter("picture"));
		newMember.setAim(req.getParameter("aim"));
		newMember.setOpinion(req.getParameter("opinion"));
		newMember.setSelfInstruction(req.getParameter("selfInstruction"));
		newMember.setIsWork(req.getParameter("isWork"));
		newMember.setSkills(req.getParameter("skills"));
		
		UserService userService=new UserService();
		int flag=userService.newMember(newMember);
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
	
	//新用户删除
	
	private void newMemberDelete(HttpServletRequest req, HttpServletResponse resp) {
		String studentId=req.getParameter("studentId");
		UserService userService=new UserService();
		int flag=userService.deleteNewMember(studentId);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if(flag>0){
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
	
	//新用户查看
	
	private void newMemberQuery(HttpServletRequest req, HttpServletResponse resp) {
		List<NewMember> list=new ArrayList<>();
		UserService userService=new UserService();
		list=userService.queryNewMember();
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//条件查看
	
	private void newMemberQuerySpecial(HttpServletRequest req, HttpServletResponse resp) {
		String special=req.getParameter("special");
		List<NewMember> list=new ArrayList<>();
		UserService userService=new UserService();
		list=userService.queryNewMemberSpecial(special);
		JSONArray jsonArray=JSONArray.fromObject(list);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//报名表审核
	private void checkNewMember(HttpServletRequest req, HttpServletResponse resp) {
		String check=req.getParameter("check");
		String studentId=req.getParameter("studentId");
		UserService userService=new UserService();
		int flag=userService.newMemberCheck(studentId,check);
		JSONObject jsonObject=new JSONObject();
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		if(flag>0){
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
	
	
	
	//分页
	private void pagination(HttpServletRequest req, HttpServletResponse resp) {
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		int pageSize=5;
		
	}
	
	//修改————后台已实现，前台无传参，无法实现
	private void update(HttpServletRequest req, HttpServletResponse resp) {
		//获得id
		UserService userService=new UserService();
		int id=userService.findIdByUsername(req.getParameter("studentId"));
		int flag=0;
		User user=new User();
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		user.setBirth(req.getParameter("birth"));
		user.setEmail(req.getParameter("email"));
		user.setGender(req.getParameter("gender"));
		user.setPhoneNum(req.getParameter("phoneNum"));
		user.setNation(req.getParameter("nation"));
		user.setSignature(req.getParameter("signature"));
		user.setStudentId(req.getParameter("studentId"));
		user.setMajor(req.getParameter("major"));
		user.setAcademy(req.getParameter("academy"));
		user.setMyQQ(req.getParameter("myQQ"));
		user.setPicture(req.getParameter("picture"));
		user.setGrade(Integer.parseInt(req.getParameter("grade")));
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
		UserService userService=new UserService();
		int id=userService.findIdByUsername(req.getParameter("studentId"));
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
	
	//查看一个
	
	
	private void findUser(HttpServletRequest req, HttpServletResponse resp) {
		String username=req.getParameter("username");
		UserService userService=new UserService();
		List<User> userList=new ArrayList<>();
		userList=userService.findUserByUsername(username);
		JSONArray jsonArray=JSONArray.fromObject(userList);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
			
		} catch (IOException e) {
			e.printStackTrace();
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
		user.setBirth(req.getParameter("birth"));
		user.setEmail(req.getParameter("email"));
		user.setGender(req.getParameter("gender"));
		user.setPhoneNum(req.getParameter("phoneNum"));
		user.setNation(req.getParameter("nation"));
		user.setSignature(req.getParameter("signature"));
		user.setStudentId(req.getParameter("studentId"));
		user.setMajor(req.getParameter("major"));
		user.setAcademy(req.getParameter("academy"));
		user.setMyQQ(req.getParameter("myQQ"));
		user.setPicture(req.getParameter("picture"));
		user.setGrade(Integer.parseInt(req.getParameter("grade")));
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
		else {
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
	//	条件查看
	
	private void queryMemberSpecial(HttpServletRequest req, HttpServletResponse resp) {
		String special=req.getParameter("special");
		UserService userService=new UserService();
		List<User> userList=new ArrayList<>();
		userList=userService.queryMemberSpecial(special);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		
		JSONArray jsonArray=JSONArray.fromObject(userList);
		try {
			resp.getWriter().write(String.valueOf(jsonArray));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateIdentity(HttpServletRequest req, HttpServletResponse resp) {
		String identity=req.getParameter("identity");
		String studentId=req.getParameter("studentId");
		UserService userService=new UserService();
		int flag=userService.updateIdentity(identity,studentId);
		
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		JSONObject jsonObject=new JSONObject();
		
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
	
	private void checkMember(HttpServletRequest req, HttpServletResponse resp) {
		User user=new User();
		user.setStudentId(req.getParameter("studentId"));
		user.setIdentity(req.getParameter("identity"));
		String check=req.getParameter("check");
		
		UserService userService=new UserService();
		int flag=userService.checkMember(check,user);
		resp.setContentType("application/json;charset=utf-8");
		resp.setContentType("text/json;charset=utf-8");
		JSONObject jsonObject=new JSONObject();
		
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
	
}
