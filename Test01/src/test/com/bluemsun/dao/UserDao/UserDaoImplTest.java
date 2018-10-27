package com.bluemsun.dao.UserDao;

import com.bluemsun.entity.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {
	
	@Test
	public void update() {
		UserDao userDao=new UserDaoImpl();
		User user=new User("qwe","qwe","1123","女","111","1552682",2);
		int flag=0;
		try {
			flag=userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flag);
	}
	
	@Test
	public void add() {
	}
	
	@Test
	public void  delete() {
		int flag=0;
		UserDao userDao=new UserDaoImpl();
		try {
			flag=userDao.delete(8);
			System.out.println(flag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findById() {
		User user=new User();
		user.setUsername("dage");
//		user.setPassword(111111);
		System.out.println(user.getUsername());
	}
	@Test
	public void queryAll() {
		UserDao userDao=new UserDaoImpl();
		List<User> list = new ArrayList<>();
		try {
			list=userDao.queryAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (User x : list) {
			System.out.println(x.getUsername()+","+x.getId()+","+x.getPassword());
			
		}
	}
	
	@Test
	public void personCount() {
		long count=0L;
		UserDao userDao=new UserDaoImpl();
		try {
			count=userDao.personCount();
			System.out.println(count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}