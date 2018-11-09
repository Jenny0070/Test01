package com.bluemsun.service;

import com.bluemsun.dao.UserDao.UserDao;
import com.bluemsun.dao.UserDao.UserDaoImpl;
import com.bluemsun.entity.NewMember;
import com.bluemsun.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
	
	/**
	 * 新用户
	 * @param newMember
	 * @return
	 */
	//报名表
	
	public int newMember(NewMember newMember){
		UserDao userDao=new UserDaoImpl();
		int flag;
		flag=userDao.newMember(newMember);
		return flag;
	}
	
	//新用户删除
	
	public int deleteNewMember(String studentId){
		int flag;
		UserDao userDao=new UserDaoImpl();
		flag=userDao.deleteNewMember(studentId);
		return flag;
	}
	
	//新用户查看
	
	public List<NewMember> queryNewMember(){
		List<NewMember> list=new ArrayList<>();
		UserDao userDao=new UserDaoImpl();
		list=userDao.queryNewMember();
		return list;
	}
	
	 public List<NewMember> queryNewMemberSpecial(String special){
		 List<NewMember> list=new ArrayList<>();
		 UserDao userDao=new UserDaoImpl();
		 list=userDao.queryNewMemberSpecial(special);
		 return list;
	 }
	 
	 //审核报名表
	
	public int newMemberCheck(String studentId ,String check){
		UserDao userDao=new UserDaoImpl();
		int flag=userDao.checkNewMember(check,studentId);
		return flag;
		
	}
	
	/**
	 * 普通用户
	 * @param user
	 * @return
	 */
	//注册
	
	public int addUser(User user){
		UserDao userDao=new UserDaoImpl();
		List<User> list=((UserDaoImpl) userDao).findByUsername(user);
		int flag=0;
		if (list.size()==0){
			try {
				flag=userDao.add(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
		
		
	}
	//登录
	
	public List findUser(User user) {
		UserDao userDao=new UserDaoImpl();
		List list=null;
		try {
			list=userDao.findById(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  list;
	}
	//查询所有
	
	public List<User> queryAll(){
		List<User> list=new ArrayList<>();
		UserDao userDao=new UserDaoImpl();
		try {
			list=userDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	//删除
	
	public int deleteById(int id){
		int flag=0;
		UserDao userDao=new UserDaoImpl();
		try {
			flag=userDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//修改
	
	public int findIdByUsername(String studentId){
		UserDao userDao=new UserDaoImpl();
		int id=userDao.findIdByUsername(studentId);
		return id;
	}
	
	public int updateById(User user){
		UserDao userDao=new UserDaoImpl();
		int flag=0;
		try {
			flag=userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	

	public List<User> findUserByUsername(String username){
		UserDao userDao=new UserDaoImpl();
		List<User> users=new ArrayList<>();
		User user=new User();
		user.setUsername(username);
		users=((UserDaoImpl) userDao).findByUsername(user);
		return users;
	}
	
	//改变报名表状态
	
	public int updateApplicationState(String state){
		UserDao userDao=new UserDaoImpl();
		int flag=userDao.updateApplicationState(state);
		return flag;
	}
	
//	查看状态
	public String queryApplicationState(){
		String state = null;
		UserDao userDao=new UserDaoImpl();
		List<NewMember> list=userDao.queryApplicationState();
		for (NewMember x:list){
			state=x.getIsOn();
		}
		return state;
	}
}
