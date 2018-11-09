package com.bluemsun.dao.InformDao;

import com.bluemsun.entity.Inform;

import java.sql.SQLException;
import java.util.List;

public interface InformDao {
	List<Inform> getInformDao(int pageNum, int pageSize);
	
	//查找有几条记录
	Long getCount();
	
	int add(Inform inform) throws SQLException;
	
	//删除
	
	int delete(int id) throws SQLException;
	
	//查看一个
	
	int findIdByTitle(String title);
	
	int update(Inform news) throws SQLException;
	
	List<Inform> findByTitleOrKeyWord(Inform inform) throws SQLException;
	
	List<Inform> queryAll() throws SQLException;
}
