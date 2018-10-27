package com.bluemsun.dao.CommentBoardDao;

import com.bluemsun.entity.CommentBoard;

import java.sql.SQLException;
import java.util.List;

public interface CommentBoardDao {
	
	int add(CommentBoard commentBoard) throws SQLException;
	
	int delete(int id) throws SQLException;
	
	//修改
	int findId(CommentBoard commentBoard);
	
	int update(CommentBoard commentBoard) throws SQLException;
	
	List<CommentBoard> findById(CommentBoard commentBoard) throws SQLException;
	
	List<CommentBoard> queryAll() throws SQLException;
}
