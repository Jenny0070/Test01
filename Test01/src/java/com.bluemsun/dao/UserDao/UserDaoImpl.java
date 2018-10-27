package com.bluemsun.dao.UserDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{
	//创建核心类QueryRunner
	
	private QueryRunner runner=null;
	
	public UserDaoImpl() {
		runner=new QueryRunner();
	}
	
	@Override
	//修改
	
	public int findIdByUsername(String username){
		String sql="select id from user where username=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		
		try {
			List list=runner.query(conn,sql,new BeanListHandler<User>(User.class),username);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	@Override
	public int update(User user) throws SQLException {
		int flag=0;
		String sql="UPDATE user set username=?,password=?,email=?,gender=?,identity=?,phoneNum=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getIdentity(),user.getPhoneNum(),user.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	//注册判断
	
	public List<User> findByUsername(User user){
		Connection conn=DBUtils.getConnection();
		String sql="select * from user where username=?";
		List<User> list =new ArrayList<>();
		try {
			list= runner.query(conn,sql,new BeanListHandler<User>(User.class),user.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
		
		
	}
	/*
	增加
	 */
	
	@Override
	public int add(User user) throws SQLException {
		int line;
		Connection conn=DBUtils.getConnection();
		String sql="INSERT INTO user (username,password,email,gender,identity,phoneNum) VALUES(?,?,?,?,?,?)";
		Object[] param={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getIdentity(),user.getPhoneNum()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	//删除
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from user where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	//登录实现
	@Override
	public List<User> findById(User user) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<User>(User.class),user.getUsername(),user.getPassword());
		return list;
		
	}
	//查找全部
	
	@Override
	public List<User> queryAll() throws SQLException {
		String sql = "select id,username,password,email,gender,identity,phoneNum from user";
		List<User> users = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<User>(User.class));
		return users;
		
	}
	
	
	@Override
	public long personCount() throws SQLException {
		String sql = "select count(*) from user";
		Long count=0L;
		try {
			//ScalarHandler()方法只能返回long类型的数据
			count=runner.query(DBUtils.getConnection(),sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
}