package com.bluemsun.dao.BlogDao;

import com.bluemsun.entity.Blog;

import java.sql.SQLException;
import java.util.List;

public interface BlogDao {
	int add(Blog blog) throws SQLException;
	
	int delete(int id) throws SQLException;
	
	//修改
	
	int findId(Blog blog);
	
	int update(Blog blog) throws SQLException;
	
	List<Blog> findById(Blog blog) throws SQLException;
	
	List<Blog> queryAll() throws SQLException;
}
