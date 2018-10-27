package com.bluemsun.service;

import com.bluemsun.dao.BlogDao.BlogDao;
import com.bluemsun.dao.BlogDao.BlogDaoImpl;
import com.bluemsun.entity.Blog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogService {
	BlogDao blogDao=new BlogDaoImpl();
	public int add(Blog blog){
		int flag=0;
		try {
			flag=blogDao.add(blog);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int findId(Blog blog){
		int id=0;
		id=blogDao.findId(blog);
		return id;
	}
	public int delete(int id){
		int flag=0;
		try {
			flag=blogDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int update(Blog blog){
		int flag=0;
		try {
			flag=blogDao.update(blog);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public List<Blog> query(){
		List<Blog> list=new ArrayList<>();
		try {
			list=blogDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
