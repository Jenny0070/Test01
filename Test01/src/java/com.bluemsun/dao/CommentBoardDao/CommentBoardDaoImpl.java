package com.bluemsun.dao.CommentBoardDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.CommentBoard;
import com.bluemsun.entity.News;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CommentBoardDaoImpl implements CommentBoardDao {
	private QueryRunner runner=new QueryRunner();
	/*
	增加
	 */
	
	@Override
	public int add(CommentBoard commentBoard) throws SQLException {
		int line;
		Connection conn= DBUtils.getConnection();
		String sql="INSERT INTO commentBoard (content,username,target,keyWord) VALUES(?,?,?,?)";
		Object[] param={commentBoard.getContent(),commentBoard.getUsername(),commentBoard.getTarget(),commentBoard.getKeyWord()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from commentBoard where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	//修改
	@Override
	
	public int findId(CommentBoard commentBoard){
		String sql="select id from commentBoard where username=? and target=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		Object[] params={commentBoard.getUsername(),commentBoard.getTarget()};
		try {
			List list=runner.query(conn,sql,new BeanListHandler<CommentBoard>(CommentBoard.class),params);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(CommentBoard commentBoard) throws SQLException {
		int flag=0;
		String sql="UPDATE blog set content=?,username=?,target=?,keyWord=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={commentBoard.getContent(),commentBoard.getUsername(),commentBoard.getTarget(),commentBoard.getKeyWord(),commentBoard.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	
	//查看一个
	
	@Override
	public List<CommentBoard> findById(CommentBoard commentBoard) throws SQLException {
		String sql = "select * from news where username=? or target=? or keyWord=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<CommentBoard>(CommentBoard.class),commentBoard.getUsername(), commentBoard.getTarget(),commentBoard.getKeyWord());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<CommentBoard> queryAll() throws SQLException {
		String sql = "select id,content,username,target,keyWord from commentBoard";
		List<CommentBoard> commentBoard = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<CommentBoard>(CommentBoard.class));
		return commentBoard;
		
	}
}


