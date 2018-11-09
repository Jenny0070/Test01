package com.bluemsun.dao.SearchDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDaoImpl implements SearchDao {
	private QueryRunner queryRunner=new QueryRunner();
	Connection conn=DBUtils.getConnection();
	
	@Override
	public List<User> searchUser(String param) {
		String sql="select username,email,gender,identity,phoneNum from user where username like ? or identity like ? ";
		List<User> userList=new ArrayList<>();
		String search="%"+param+"%";
		Object[] params={search,search};
		try {
			userList=queryRunner.query(conn,sql,new BeanListHandler<User>(User.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	@Override
	public List<News> searchNews(String param) {
		String sql="select * from news where title like ? or keyWord like ? or promulgator like ? or type like ? ";
		List<News> newsList=new ArrayList<>();
		String search="%"+param+"%";
		Object[] params={search,search,search,search};
		try {
			newsList=queryRunner.query(conn,sql,new BeanListHandler<News>(News.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newsList;
		
	}
	
	@Override
	public List<Display> searchDisplay(String param) {
		String sql="select * from display where username like ? or title like ?  ";
		List<Display> displayList=new ArrayList<>();
		String search="%"+param+"%";
		Object[] params={search,search};
		try {
			displayList=queryRunner.query(conn,sql,new BeanListHandler<Display>(Display.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return displayList;
	}
	
	@Override
	public List<CommentBoard> searchCommentBoard(String param) {
		String sql="select * from commentBoard where username like ? or target like ? or keyWord like ? ";
		List<CommentBoard> displayList=new ArrayList<>();
		String search="%"+param+"%";
		Object[] params={search,search,search};
		try {
			displayList=queryRunner.query(conn,sql,new BeanListHandler<CommentBoard>(CommentBoard.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return displayList;
	}
	
	@Override
	public List<Blog> searchBlog(String param) {
		String sql="select * from blog where username like ? or title like ? or keyWord like ? ";
		List<Blog> blogList=new ArrayList<>();
		String search="%"+param+"%";
		Object[] params={search,search,search};
		try {
			blogList=queryRunner.query(conn,sql,new BeanListHandler<Blog>(Blog.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogList;
	}
}
