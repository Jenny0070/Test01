package com.bluemsun.service;

import com.bluemsun.dao.InformDao.InformDao;
import com.bluemsun.dao.InformDao.InformDaoImpl;
import com.bluemsun.entity.Inform;
import com.bluemsun.entity.Page;
import com.bluemsun.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformService {
	public Page getPageService(int pageNum, int pageSize){
		InformDao informDao=new InformDaoImpl();
		//totalRecord
		Long count=informDao.getCount();
		Page page=new Page(pageNum,pageSize,count);
		List list=informDao.getInformDao(pageNum,pageSize);
		page.setPageList(list);
		return page;
	}
	
	//限制部分用户可见，核查是否为可见用户
	//一个inform的限制
	
	public int isPass(User user,String title){
		int flag=0;
		InformDao informDao=new InformDaoImpl();
		String identity=informDao.checkIdentity(user);
		String limit=informDao.checkLimit(title);
		if(limit.equals(identity)){
			flag=1;
		}
		return flag;
	}
	
//	所有inform的限制
	
	public int isPassAll(User user){
		int flag=0;
		InformDao informDao=new InformDaoImpl();
		String identity=informDao.checkIdentity(user);
		String limit="正式成员";
		if(limit.equals(identity)){
			flag=1;
		}
		return flag;
	}
	//查看公共可看
	
	public List<Inform> seletCommon(){
		List<Inform> list=new ArrayList<>();
		InformDao informDao=new InformDaoImpl();
		list=informDao.selectPart();
		return list;
	}
	
	//增加
	public int add(Inform inform){
		InformDao informDao=new InformDaoImpl();
		List<Inform> list= null;
		try {
			list = informDao.findByTitleOrKeyWord(inform);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int flag=0;
		if (list.size()==0){
			try {
				flag=informDao.add(inform);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
		
		
	}
	//删除修改查id用
	
	public int findIdByTitle(String title){
		InformDao informDao=new InformDaoImpl();
		int id=informDao.findIdByTitle(title);
		return id;
	}
	
	//删除
	
	public int delete(int id){
		int flag=0;
		InformDao informDao=new InformDaoImpl();
		try {
			flag=informDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	//修改
	
	public int update(Inform inform){
		InformDao informDao=new InformDaoImpl();
		int flag=0;
		try {
			flag=informDao.update(inform);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//查询所有
	
	public List<Inform> queryAll(){
		List<Inform> list=new ArrayList<>();
		InformDao informDao=new InformDaoImpl();
		try {
			list=informDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
