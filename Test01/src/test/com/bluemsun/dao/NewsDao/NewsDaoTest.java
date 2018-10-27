package com.bluemsun.dao.NewsDao;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsDaoTest {
	
	@Test
	public void getNewsDao() {
	}
	
	@Test
	public void getCount() {
		NewsDao newsDao=new NewsDaoImpl();
		Long count=newsDao.getCount();
		System.out.println(count);
	}
}