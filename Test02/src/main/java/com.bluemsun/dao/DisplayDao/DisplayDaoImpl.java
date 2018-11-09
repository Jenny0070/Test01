package com.bluemsun.dao.DisplayDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.Display;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisplayDaoImpl implements DisplayDao {
	private QueryRunner runner=new QueryRunner();
	
	//找id
	
	@Override
	public List<Display> findByTitleAndUsername(Display display){
		String sql="select id from display where title=? and username=?";
		Connection conn=DBUtils.getConnection();
		List<Display> list=new ArrayList<>();
		Object[] params={display.getTitle(),display.getUsername()};
		try {
			list=runner.query(conn,sql,new BeanListHandler<Display>(Display.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//增加
	
	@Override
	public int add(Display display) throws SQLException {
		int line;
		Connection conn= DBUtils.getConnection();
		String sql="INSERT INTO user (issuer,username,title,pictures,description,keyWord,date) VALUES(?,?,?,?,?,?.?)";
		
		Object[] param={display.getIssuer(),display.getUsername(),display.getTitle(),display.getPictures(),
				display.getDescription(),display.getKeyWord(),display.getDate()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from display where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	
	//修改
	
	@Override
	public int findIdByTitle(String title){
		String sql="select id from display where title=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		try {
			List list=runner.query(conn,sql,new BeanListHandler<Display>(Display.class),title);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(Display display) throws SQLException {
		int flag=0;
		String sql="UPDATE display set issuer=?,username=?,title=?,pictures=?,description=?,keyWord=?,date=?WHERE id=?";
		//参数最好按照？顺序
		Object[] params={display.getIssuer(),display.getUsername(),display.getTitle(),display.getPictures(),
				display.getDescription(),display.getKeyWord(),display.getDate(),display.getId(),
				display.getLike(),display.getHits()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	//查看一个
	
	@Override
	public List<Display> findById(Display display) throws SQLException {
		String sql = "select * from display where username=? or title=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<Display>(Display.class),display.getUsername(),display.getTitle());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<Display> queryAll() throws SQLException {
		String sql = "select issuer,username,title,pictures,description,keyWord,date from display";
		List<Display> displays = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Display>(Display.class));
		return displays;
		
	}
	
	//查看点击量
	
	@Override
	public int selectHits(String username, String title){
		int hits=0;
		List<Display> displays=new ArrayList<>();
		String sql="select hits from display where username=?and title=?";
		try {
			 displays=runner.query(DBUtils.getConnection(),sql,new BeanListHandler<Display>(Display.class),username,title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Display display:displays){
			hits=display.getHits();
		}
		return hits;
	}
	
	//更新点击量
	
	@Override
	public int updateHits(Display display){
		int flag=0;
		String sql="update display set hits =? where username=? and title=?";
		Object[] params={display.getHits(),display.getUsername(),display.getTitle()};
		try {
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//查看点赞数
	
	@Override
	public int selectLike(String username, String title){
		int like=0;
		List<Display> displays=new ArrayList<>();
		String sql="select like from display where username=?and title=?";
		try {
			displays=runner.query(DBUtils.getConnection(),sql,new BeanListHandler<Display>(Display.class),username,title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Display display:displays){
			like=display.getHits();
		}
		return like;
	}
	
	//更新点击量
	
	@Override
	public int updateLike(Display display){
		int flag=0;
		String sql="update display set like =? where username=? and title=?";
		Object[] params={display.getLike(),display.getUsername(),display.getTitle()};
		try {
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}

