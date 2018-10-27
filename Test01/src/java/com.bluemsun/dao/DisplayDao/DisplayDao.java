package com.bluemsun.dao.DisplayDao;

import com.bluemsun.entity.Display;

import java.sql.SQLException;
import java.util.List;

public interface DisplayDao  {
	/*
	增加
	 */
	List<Display> findByTitleAndUsername(Display display);
	
	int add(Display display) throws SQLException;
	
	int delete(int id) throws SQLException;
	
	int findIdByTitle(String title);
	
	int update(Display display) throws SQLException;
	
	List<Display> findById(Display display) throws SQLException;
	
	List<Display> queryAll() throws SQLException;
}
