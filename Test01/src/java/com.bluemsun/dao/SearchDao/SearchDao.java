package com.bluemsun.dao.SearchDao;

import com.bluemsun.entity.*;

import java.util.List;

public  interface SearchDao {
	//查找user从username和identity
	
	public List<User> searchUser(String param);
	
	//查找news从title,keyWord,promulgator,type
	
	public List<News> searchNews(String param);
	
	//查找display从username,title
	
	public List<Display> searchDisplay(String param);
	
	//查找commentBoard从username，target,ketWord
	
	public List<CommentBoard> searchCommentBoard(String param);
	
	//查找blog从username,title,keyword
	
	public List<Blog> searchBlog(String param);
}
