package com.bluemsun.service;

import com.bluemsun.dao.UserDao.UserDao;
import com.bluemsun.dao.UserDao.UserDaoImpl;
import com.bluemsun.entity.User;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
	
	@Test
	public void queryAll() {
		List<User> list = new ArrayList<>();
		for (User x : list) {
			System.out.println(x);
			
		}
	}
	
	@Test
	public void updateById(){//right
		User user=new User("142","123","111","111","111","111",2);
		UserDao userDao=new UserDaoImpl();
		int flag=0;
		try {
			flag=userDao.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flag);
		
	}
}