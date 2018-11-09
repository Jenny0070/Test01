package com.bluemsun.service;

import com.bluemsun.dao.DisplayDao.DisplayDao;
import com.bluemsun.dao.DisplayDao.DisplayDaoImpl;
import com.bluemsun.entity.Display;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisplayService {
	DisplayDao displayDao=new DisplayDaoImpl();
	public int add(Display display){
		List<Display> list=displayDao.findByTitleAndUsername(display);
		int flag=0;
		if (list.size()!=0){
			try {
				flag=displayDao.add(display);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	public int findIdByTitle(String title){
		int id=0;
		id=displayDao.findIdByTitle(title);
		return id;
	}
	public int delete(int id){
		int flag=0;
		try {
			 flag=displayDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public int update(Display display){
		int flag=0;
		try {
			flag=displayDao.update(display);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public List<Display> findById(Display display){
		List<Display> list=new ArrayList<>();
		try {
			list=displayDao.findById(display);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public List<Display> queryAll(){
		List<Display> list=new ArrayList<>();
		try {
			list=displayDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
