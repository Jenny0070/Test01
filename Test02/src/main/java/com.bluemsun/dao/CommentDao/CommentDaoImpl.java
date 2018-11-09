package com.bluemsun.dao.CommentDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.Comment;
import com.bluemsun.entity.CommentBoard;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentDaoImpl implements CommentDao {
	private QueryRunner runner=new QueryRunner();
	/*
	增加
	 */
	
	@Override
	public int add(Comment comment) throws SQLException {
		int line;
		Connection conn= DBUtils.getConnection();
		String sql="INSERT INTO comment (content,username,title,keyWord,date,target) VALUES(?,?,?,?,?,?)";
		Object[] param={comment.getContent(),comment.getUsername(),comment.getTitle(),comment.getDate(),comment.getTarget()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from comment where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	//修改
	@Override
	
	public int findId(Comment comment){
		String sql="select id from comment where username=? and title=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		Object[] params={comment.getUsername(),comment.getTitle()};
		try {
			List<Comment > list=runner.query(conn,sql,new BeanListHandler<Comment>(Comment.class),params);
			for(Comment x:list){
				id= x.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(Comment comment) throws SQLException {
		int flag=0;
		String sql="UPDATE comment set content=?,username=?,title=?,keyWord=?,date=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={comment.getContent(),comment.getUsername(),comment.getTitle(),
				comment.getKeyWord(),comment.getDate(),comment.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	
	//查看一个
	
	@Override
	public List<Comment> findById(Comment comment) throws SQLException {
		String sql = "select * from comment where username=? or title=? or keyWord=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<Comment>(Comment.class),comment.getUsername(), comment.getTitle(),comment.getKeyWord());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<Comment> queryAll() throws SQLException {
		String sql = "select id,content,username,title,keyWord,date,target from comment";
		List<Comment> comment = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Comment>(Comment.class));
		return comment;
		
	}
	
	
}
