package com.bluemsun.service;

import com.bluemsun.dao.CommentBoardDao.CommentBoardDao;
import com.bluemsun.dao.CommentBoardDao.CommentBoardDaoImpl;
import com.bluemsun.entity.CommentBoard;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentBoardService {
	
	CommentBoardDao commentBoardDao=new CommentBoardDaoImpl();
	public int add(CommentBoard commentBoard){
		int flag= 0;
		try {
			flag = commentBoardDao.add(commentBoard);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int findId(CommentBoard commentBoard){
		int id=0;
		id=commentBoardDao.findId(commentBoard);
		return id;
	}
	public int delete(int id){
		int flag=0;
		try {
			flag=commentBoardDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int update(CommentBoard commentBoard){
		int flag=0;
		try {
			flag=commentBoardDao.update(commentBoard);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
//
	
	public List<CommentBoard> queryAll(){
		List<CommentBoard> list=new ArrayList<>();
		try {
			list=commentBoardDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
