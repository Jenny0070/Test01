package com.bluemsun.dao.UserDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.NewMember;
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
	
	/**
	 * 新成员报名
	 */
	
	@Override
	public int newMember(NewMember newMember) {
		int flag=0;
		String sql="insert into newMemberApplication (username,gender,nation,grade,age,major,myQQ,email,phoneNum,picture,aim,selfInstruction,opinion) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params={newMember.getUsername(),newMember.getGender(),newMember.getNation(),newMember.getGrade(),newMember.getAge(),newMember.getMajor(),newMember.getMyQQ(),newMember.getEmail(),newMember.getPhoneNum(),newMember.getPicture(),newMember.getAim(),newMember.getSelfInstruction(),newMember.getOpinion()};
		try {
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	//新成员审核不通过
	
	@Override
	public int deleteNewMember(String username) {
		int flag=0;
		String sql="delete from newMemberApplication where username=?";
		try {
			flag=runner.update(DBUtils.getConnection(),sql,username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	//查看所有新成员以便管理
	
	@Override
	public List<NewMember> queryNewMember() {
		List<NewMember> list=new ArrayList<>();
		String sql="select * from newMemberApplication ";
		try {
			list=runner.query(DBUtils.getConnection(),sql, new BeanListHandler<NewMember>(NewMember.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//确认成为工作室成员
	
	@Override
	
	public int addFullMember(NewMember newMember){
		int flag=0;
		String sql="INSERT INTO user (username,password,email,gender,identity,phoneNum) VALUES(?,?,?,?,?,?)";
		Object[] params={newMember.getUsername(),"123456",newMember.getEmail(),newMember.getGender(),"工作室成员",newMember.getPhoneNum()};
		try {
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
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
		String sql="UPDATE user set username=?,password=?,email=?,gender=?,identity=?,phoneNum=?,nation=?,signature=?,studentId WHERE id=?";
		//参数最好按照？顺序
		Object[] params={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getIdentity(),user.getPhoneNum(),user.getNation(),user.getSignature(),user.getStudentId(),user.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	//注册判断
	
	public List<User> findByUsername(User user){
		Connection conn=DBUtils.getConnection();
		String sql="select username,email,gender,identity,phoneNum,nation,signature,studentId from user where username=?";
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
		System.out.println(conn);
		String sql="INSERT INTO user (username,password,email,gender,identity,phoneNum,nation,signature,studentId) VALUES(?,?,?,?,?,?,?,?,?)";
		Object[] param={user.getUsername(),user.getPassword(),user.getEmail(),user.getGender(),user.getIdentity(),user.getPhoneNum(),user.getNation(),user.getSignature(),user.getStudentId()};
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
		String sql = "select id,username,password,email,gender,identity,phoneNum,nation,signature,studentId from user";
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
	//报名表状态更改
	@Override
	public int updateApplicationState(String state){
		String sql="update applicationState set isOn=?";
		int flag= 0;
		try {
			flag = runner.update(DBUtils.getConnection(),sql,state);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
		
	}
//	报名表状态查看(未测试
	
	@Override
	public List<NewMember> queryApplicationState(){
		String sql="select isOn from applicationState";
		List<NewMember> list=new ArrayList();
		try {
			list= runner.query(DBUtils.getConnection(),sql,new BeanListHandler<NewMember>(NewMember.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}