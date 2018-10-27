package com.bluemsun.dao.BlogDao;
import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.Blog;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BlogDaoImpl implements BlogDao {
	private QueryRunner runner=new QueryRunner();
	//增加
	
	@Override
	public int add(Blog blog) throws SQLException {
		int line;
		Connection conn= DBUtils.getConnection();
		String sql="INSERT INTO blog (username,title,content,keyWord) VALUES(?,?,?,?)";
		Object[] param={blog.getUsername(),blog.getTitle(),blog.getContent(),blog.getKeyWord()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from blog where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	//修改
	
	@Override
	
	public int findId(Blog blog){
		String sql="select id from blog where title=? and username=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		try {
			Object[] params={blog.getTitle(),blog.getUsername()};
			List list=runner.query(conn,sql,new BeanListHandler<Blog>(Blog.class),params);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(Blog blog) throws SQLException {
		int flag=0;
		String sql="UPDATE blog set username=?,title=?,content=?,keyWord=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={blog.getUsername(),blog.getTitle(),blog.getContent(),blog.getKeyWord(),blog.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	//查看一个
	
	@Override
	public List<Blog> findById(Blog blog) throws SQLException {
		String sql = "select * from blog where title=? or keyWord=? or username=?";
		Object[] params={blog.getTitle(),blog.getKeyWord(),blog.getUsername()};
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<Blog>(Blog.class),params);
		return list;
		
	}
	//查找全部
	
	@Override
	public List<Blog> queryAll() throws SQLException {
		String sql = "select id,username,title,content,keyWord from blog";
		List<Blog> blogs = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Blog>(Blog.class));
		return blogs;
		
	}
}

