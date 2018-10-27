package com.bluemsun.dao.NewsDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.News;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
	//分页功能
	
	private QueryRunner runner=new QueryRunner();
	@Override
	public List<News> getNewsDao(int pageNum, int pageSize) {
		String sql = "select * from news limit ?,?";
		List<News> list=new ArrayList<>();
		try {
			 list=runner.query(DBUtils.getConnection(),sql,new BeanListHandler<News>(News.class),pageNum,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	//查找有几条记录
	@Override
	public Long getCount() {
		String sql = "select count(*) from news";
		Long count=0L;
		try {
			//ScalarHandler()方法只能返回long类型的数据
			count=runner.query(DBUtils.getConnection(),sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	增加
	 */
	
	@Override
	public int add(News news) throws SQLException {
		int line;
		Connection conn=DBUtils.getConnection();
		String sql="INSERT INTO user (title,keyWord,promulgator,content,type,data) VALUES(?,?,?,?,?,?)";
		Object[] param={news.getTitle(),news.getKeyWord(),news.getPromulgator(),news.getContent(),news.getType(),news.getDate()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from news where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	
	//修改
	
	@Override
	public int findIdByTitle(String title){
		String sql="select id from news where title=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		try {
			List list=runner.query(conn,sql,new BeanListHandler<News>(News.class),title);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(News news) throws SQLException {
		int flag=0;
		String sql="UPDATE blog set title=?,keyWord=?,promulgator=?content=?,type=?,data=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={news.getTitle(),news.getKeyWord(),news.getPromulgator(),news.getContent(),news.getContent(),news.getType(),news.getDate(),news.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	//查看一个
	
	@Override
	public List<News> findByTitleOrKeyWord(News news) throws SQLException {
		String sql = "select * from news where title=? or keyWord=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<News>(News.class),news.getTitle(),news.getKeyWord());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<News> queryAll() throws SQLException {
		String sql = "select title,keyWord,promulgator,content,type,data from news";
		List<News> news = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<News>(News.class));
		return news;
		
	}
}
