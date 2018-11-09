package com.bluemsun.service;

import com.bluemsun.dao.CommentDao.CommentDao;
import com.bluemsun.dao.CommentDao.CommentDaoImpl;
import com.bluemsun.entity.Comment;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService {
	
	CommentDao commentDao =new CommentDaoImpl();
	public int add(Comment comment){
		int flag= 0;
		try {
			flag = commentDao.add(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int findId(Comment comment){
		int id=0;
		id=commentDao.findId(comment);
		return id;
	}
	public int delete(int id){
		int flag=0;
		try {
			flag=commentDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int update(Comment comment){
		int flag=0;
		try {
			flag=commentDao.update(comment);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
//
	
	public List<Comment> queryAll(){
		List<Comment> list=new ArrayList<>();
		try {
			list=commentDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
