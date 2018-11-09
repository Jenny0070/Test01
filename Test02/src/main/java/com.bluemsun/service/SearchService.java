package com.bluemsun.service;

import com.bluemsun.dao.SearchDao.SearchDao;
import com.bluemsun.dao.SearchDao.SearchDaoImpl;
import com.bluemsun.entity.*;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
	SearchDao searchDao=new SearchDaoImpl();
	
	public List<User> userSearch(String param) {
		List<User> userList=new ArrayList<>();
		userList=searchDao.searchUser(param);
		return userList;
	}
	
	public List<News> newsSearch(String param) {
		List<News> newsList=new ArrayList<>();
		newsList=searchDao.searchNews(param);
		return newsList;
	}
	
	public List<Display> displaySearch(String param) {
		List<Display> displayList=new ArrayList<>();
		displayList=searchDao.searchDisplay(param);
		return displayList;
	}
	
	public List<CommentBoard> commentBoardSearch(String param) {
		List<CommentBoard> commentBoardList=new ArrayList<>();
		commentBoardList=searchDao.searchCommentBoard(param);
		return commentBoardList;
	}
	
	public List<Blog> blogSearch(String param) {
		List<Blog> blogList=new ArrayList<>();
		blogList=searchDao.searchBlog(param);
		return blogList;
	}
}
