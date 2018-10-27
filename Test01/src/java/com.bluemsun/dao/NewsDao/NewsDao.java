package com.bluemsun.dao.NewsDao;

import com.bluemsun.entity.News;

import java.sql.SQLException;
import java.util.List;

public interface NewsDao {
	
	List<News> getNewsDao(int pageNum, int pageSize);
	
	//查找有几条记录
	Long getCount();
	
	int add(News news) throws SQLException;
	
	//删除
	
	int delete(int id) throws SQLException;
	
	//查看一个
	
	int findIdByTitle(String title);
	
	int update(News news) throws SQLException;
	
	List<News> findByTitleOrKeyWord(News news) throws SQLException;
	
	List<News> queryAll() throws SQLException;
}
