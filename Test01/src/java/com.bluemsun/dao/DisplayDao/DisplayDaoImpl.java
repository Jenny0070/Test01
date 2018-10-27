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
	/*
	增加
	 */
	
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
	
	@Override
	public int add(Display display) throws SQLException {
		int line;
		Connection conn= DBUtils.getConnection();
		String sql="INSERT INTO user (username=?,title=?,pictures=?,description=?) VALUES(?,?,?,?)";
		Object[] param={display.getUsername(),display.getTitle(),display.getPictures(),display.getDescription()};
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
		String sql="UPDATE display set username=?,title=?,pictures=?,description=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={display.getUsername(),display.getTitle(),display.getPictures(),display.getDescription(),display.getId()};
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
		String sql = "select username,title,pictures,description from display";
		List<Display> displays = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Display>(Display.class));
		return displays;
		
	}
}

