package com.bluemsun.dao.CommentBoardDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.CommentBoard;
import com.bluemsun.entity.News;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql="INSERT INTO commentBoard (content,username,title,keyWord,date) VALUES(?,?,?,?,?)";
		Object[] param={commentBoard.getContent(),commentBoard.getUsername(),commentBoard.getTitle(),commentBoard.getDate()};
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
		String sql="select id from commentBoard where username=? and title=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		Object[] params={commentBoard.getUsername(),commentBoard.getTitle()};
		try {
			List<CommentBoard > list=runner.query(conn,sql,new BeanListHandler<CommentBoard>(CommentBoard.class),params);
			for(CommentBoard x:list){
				id= x.getId();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(CommentBoard commentBoard) throws SQLException {
		int flag=0;
		String sql="UPDATE blog set content=?,username=?,title=?,keyWord=?,date=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={commentBoard.getContent(),commentBoard.getUsername(),commentBoard.getTitle(),
				commentBoard.getKeyWord(),commentBoard.getDate(),commentBoard.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	
	//查看一个
	
	@Override
	public List<CommentBoard> findById(CommentBoard commentBoard) throws SQLException {
		String sql = "select * from news where username=? or title=? or keyWord=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<CommentBoard>(CommentBoard.class),commentBoard.getUsername(), commentBoard.getTitle(),commentBoard.getKeyWord());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<CommentBoard> queryAll() throws SQLException {
		String sql = "select id,content,username,title,keyWord,date from commentBoard";
		List<CommentBoard> commentBoard = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<CommentBoard>(CommentBoard.class));
		return commentBoard;
		
	}
	
	//只能展示已经通过审核的
	
	@Override
	public List<CommentBoard> queryCheck(){
		String sql="select content,username,title,keyWord,date from commentBoard where state=?";
		List<CommentBoard> commentBoards=new ArrayList<>();
		try {
			commentBoards=runner.query(DBUtils.getConnection(),sql,new BeanListHandler<CommentBoard>(CommentBoard.class),"通过");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentBoards;
	}
	
	//审核
	
	@Override
	public int checkCommentBoard(String check, int id){
		String sql="update commentBoard set state=? where id=?";
		int flag=0;
		try {
			flag=runner.update(DBUtils.getConnection(),sql,check,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
}


