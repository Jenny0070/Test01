package com.bluemsun.service;

import com.bluemsun.dao.NewsDao.NewsDao;
import com.bluemsun.dao.NewsDao.NewsDaoImpl;
import com.bluemsun.entity.News;
import com.bluemsun.entity.Page;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsService<T> {
	//分页
	
	public Page getPageService(int pageNum,int pageSize){
		NewsDao newsDao=new NewsDaoImpl();
		//totalRecord
		Long count=newsDao.getCount();
		Page page=new Page(pageNum,pageSize,count);
		List list=newsDao.getNewsDao(pageNum,pageSize);
		page.setPageList(list);
		return page;
	}
	
	//增加
	public int add(News news){
		NewsDao newsDao=new NewsDaoImpl();
		List<News> list= null;
		try {
			list = newsDao.findByTitleOrKeyWord(news);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int flag=0;
		if (list.size()==0){
			try {
				flag=newsDao.add(news);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
		
		
	}
	//删除修改查id用
	
	public int findIdByTitle(String title){
		NewsDao newsDao=new NewsDaoImpl();
		int id=newsDao.findIdByTitle(title);
		return id;
	}
	
	//删除
	
	public int delete(int id){
		int flag=0;
		NewsDao  newsDao=new NewsDaoImpl();
		try {
			flag=newsDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	//修改
	
	public int update(News news){
		NewsDao newsDao=new NewsDaoImpl();
		int flag=0;
		try {
			flag=newsDao.update(news);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//查询所有
	
	public List<News> queryAll(){
		List<News> list=new ArrayList<>();
		NewsDao newsDao=new NewsDaoImpl();
		
		try {
			list=newsDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//	按时间排序进行全部查看
	
	public List<News> queryOrderedByDate(){
		List<News> list=new ArrayList<>();
		NewsDao newsDao=new NewsDaoImpl();
		list=newsDao.queryOrderedByDate();
		return list;
	}
	
	//更新点击数
	
	public int updateHits(News news){
		int flag=0;
		NewsDao newsDao=new NewsDaoImpl();
		int hits=0;
		hits=((NewsDaoImpl) newsDao).selectHits(news);
		hits=hits+1;
		news.setHits(hits);
		flag=newsDao.updateHits(news);
		return flag;
	}
	
	//查看一个
	
	public List<News> queryByTitle(News news)  {
		List<News> newsList=new ArrayList<>();
		NewsDao newsDao=new NewsDaoImpl();
		try {
			newsList=newsDao.findByTitleOrKeyWord(news);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newsList;
	}
	
	//按照热度排序进行查看
	
	public List<News> queryOrderedByHits(){
		List<News> list=new ArrayList<>();
		NewsDao newsDao=new NewsDaoImpl();
		list=newsDao.queryOrderedByHits();
		return list;
	}
	
	
	
	
	}
