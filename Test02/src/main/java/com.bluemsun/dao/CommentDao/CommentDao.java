package com.bluemsun.dao.CommentDao;

import com.bluemsun.entity.Comment;
import com.bluemsun.entity.CommentBoard;

import java.sql.SQLException;
import java.util.List;

public interface CommentDao  {
	
	int add(Comment comment) throws SQLException;
	
	int delete(int id) throws SQLException;
	
	//修改
	int findId(Comment comment);
	
	int update(Comment comment) throws SQLException;
	
	List<Comment> findById(Comment comment) throws SQLException;
	
	List<Comment> queryAll() throws SQLException;
}
