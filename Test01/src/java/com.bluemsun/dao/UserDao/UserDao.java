package com.bluemsun.dao.UserDao;

import com.bluemsun.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao{
	
	// 添加方法_注册
	
	public int add(User user) throws SQLException;
	
	// 更新方法
	
	//修改
	int findIdByUsername(String username);
	
	public int update(User user) throws SQLException;
	
	// 删除方法
	
	public int delete(int id) throws SQLException;
	
	// 查找方法
	
	public List findById(User user) throws SQLException;
	
	// 查找所有
	
	List<User> queryAll() throws SQLException;
	
	// 查询有几条记录
	
	
	
	public long personCount() throws SQLException;
	
	
}


