package com.bluemsun.dao.UserDao;

import com.bluemsun.entity.NewMember;
import com.bluemsun.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao{
	
	
	
	//新成员申请表
	
	 int newMember(NewMember newMember);
	 
	//报名表查看
	
	
	
	List<NewMember> queryNewMember();
	
	List<NewMember> queryNewMemberSpecial(String special);
	
	int deleteNewMember(String studentId);
	
	//审核报名表
	int checkNewMember(String check, String studentId);
	
	//	报名表状态
	
	List<NewMember> queryApplicationState();
	
	int  updateApplicationState(String state);
	
	
	
	
	// 添加方法_注册
	
	int add(User user) throws SQLException;
	
	//修改
	
	
	
	
	int findIdByUsername(String username);
	
	 int update(User user) throws SQLException;
	
	// 删除方法
	
	 int delete(int id) throws SQLException;
	
	// 查找方法
	
	 List findById(User user) throws SQLException;
	
	// 查找所有
	
	List<User> queryAll() throws SQLException;
	
	// 查询有几条记录
	
	
	
	 long personCount() throws SQLException;
	
	
	
	
}


