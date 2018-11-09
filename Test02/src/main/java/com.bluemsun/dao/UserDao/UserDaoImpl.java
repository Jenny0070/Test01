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
		String sql="insert into newMemberApplication (studentId,username,gender,nation,grade,birth,major,academy,myQQ,email,phoneNum,picture,aim,skills,selfInstruction,opinion,isWork) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params={newMember.getStudentId(),newMember.getUsername(),newMember.getGender(),newMember.getNation(),newMember.getGrade(),newMember.getBirth(),newMember.getMajor(),newMember.getAcademy(),newMember.getMyQQ(),newMember.getEmail(),newMember.getPhoneNum(),newMember.getPicture(),newMember.getAim(),newMember.getSkills(),newMember.getSelfInstruction(),newMember.getOpinion(),newMember.getIsWork()};
		try {
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//报名表删除_通过学号
	
	@Override
	public int deleteNewMember(String studentId) {
		int flag=0;
		String sql="delete from newMemberApplication where studentId ";
		try {
			flag=runner.update(DBUtils.getConnection(),sql,studentId);
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
	
	
//	查看所有未审核、通过的、不通过的报名者
	
	@Override
	
	public List<NewMember> queryNewMemberSpecial(String special) {
		List<NewMember> list=new ArrayList<>();
		String sql="select * from newMemberApplication where state=? ";
		try {
			list=runner.query(DBUtils.getConnection(),sql, new BeanListHandler<NewMember>(NewMember.class),special);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//审核报名表
	@Override
	
	public int checkNewMember(String check, String studentId){
		String sql="update newMemberApplication set state=? where studentId=?";
		int flag=0;
		try {
			flag=runner.update(DBUtils.getConnection(),sql,check,studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
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
	
	
	//所有工作室成员均有的权限——登录、注册、注销自己用户、查看并修改自身信息
	
	
	//注册判断
	
	public List<User> findByUsername(User user){
		Connection conn=DBUtils.getConnection();
		String sql="select studentId,username,password,birth,email,gender,phoneNum,nation,signature,picture,myQQ,major,academy,grade from tranee where username=?";
		List<User> list =new ArrayList<>();
		try {
			list= runner.query(conn,sql,new BeanListHandler<User>(User.class),user.getUsername());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//	注册

	@Override
	public int add(User user) throws SQLException {
		int line;
		Connection conn=DBUtils.getConnection();
		System.out.println(conn);
		String sql="INSERT INTO user (studentId,username,password,birth,email,gender,phoneNum,nation,signature,picture,myQQ,major,academy,grade,identity) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param={user.getStudentId(),user.getUsername(),user.getPassword(),user.getBirth(),
				user.getEmail(),user.getGender(),user.getPhoneNum(),user.getNation(), user.getSignature(),
				user.getPicture(),user.getMyQQ(),user.getMajor(),user.getAcademy(),user.getGrade(),"见习工作室成员"};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	
	//登录实现
	
	@Override
	public List<User> findById(User user) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<User>(User.class),user.getUsername(),user.getPassword());
		return list;
		
	}
	
	//删除
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from user where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
		
	}
	
	//	修改
	
	@Override
	public int findIdByUsername(String studentId){
		String sql="select id from user where studentId=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		
		try {
			List list=runner.query(conn,sql,new BeanListHandler<User>(User.class),studentId);
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
		String sql="UPDATE user set studentId=?,username=?,password=?,birth=?,email=?,gender=?,phoneNum=?,nation=?,signature=?,picture=?,myQQ=?,major=?,academy=?,grade=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={user.getStudentId(),user.getUsername(),user.getPassword(),user.getBirth(),
				user.getEmail(),user.getGender(),user.getPhoneNum(),user.getNation(), user.getSignature(),
				user.getPicture(),user.getMyQQ(),user.getMajor(),user.getAcademy(),user.getGrade(),user.getId()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}//自己的身份是不可以修改的
	
	
	
	//	管理员权限
	
	
	//对注册的新成员的审核_审核状态的改变+身份由见习->正式成员
	
	@Override
	public int checkMember(String check, User user){
		String sql="update user set state=?,identity=? where studentId=?";
		int flag=0;
		try {
			Object[] params={user.getStudentId(),user.getIdentity()};
			flag=runner.update(DBUtils.getConnection(),sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//	超级管理员可以调整工作室成员的身份
	
	@Override
	public int updateIdentity(String identity,String studentId){
		String sql="update user set identity=? where studentId=?";
		int flag=0;
		try {
			flag=runner.update(DBUtils.getConnection(),sql,identity,studentId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	//条件查看
	
	@Override
	public List<User> queryMemberSpecial(String special) {
		List<User> list=new ArrayList<>();
		String sql="select * from user where state=? ";
		try {
			list=runner.query(DBUtils.getConnection(),sql, new BeanListHandler<User>(User.class),special);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查找成员
	
	@Override
	public List<User> queryAll() throws SQLException {
		String sql = "select studentId,username,password,birth,email,gender,phoneNum,nation,signature,picture,myQQ,major,academy,grade from user";
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