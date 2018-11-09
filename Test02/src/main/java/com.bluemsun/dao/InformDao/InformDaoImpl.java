package com.bluemsun.dao.InformDao;

import com.bluemsun.dbutils.DBUtils;
import com.bluemsun.entity.Inform;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformDaoImpl implements InformDao {
	private QueryRunner runner=new QueryRunner();
	@Override
	public List<Inform> getInformDao(int pageNum, int pageSize) {
		String sql = "select * from inform limit ?,?";
		List<Inform> list=new ArrayList<>();
		try {
			list=runner.query(DBUtils.getConnection(),sql,new BeanListHandler<Inform>(Inform.class),pageNum,pageSize);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Long getCount() {
		String sql = "select count(id) from inform";
		Long count=0L;
		try {
			//ScalarHandler()方法只能返回long类型的数据
			count=runner.query(DBUtils.getConnection(),sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public int add(Inform inform) throws SQLException {
		int line;
		Connection conn=DBUtils.getConnection();
		String sql="INSERT INTO inform (title,content,issuer,hits,date,limit,keyWord) VALUES(?,?,?,?,?,?,?)";
		Object[] param={inform.getTitle(),inform.getContent(),inform.getIssuer(),inform.getHits(),inform.getDate(),inform.getLimit(),inform.getKeyWord()};
		line=runner.update(conn,sql,param);
		DBUtils.closeConnection(null,null,conn);
		return line;
	}
	
	@Override
	public int delete(int id) throws SQLException {
		int flag=0;
		String sql = "delete from inform where id=?";
		flag=runner.update(DBUtils.getConnection(), sql, id);
		return  flag;
	}
	
	@Override
	public int findIdByTitle(String title) {
		String sql="select id from inform where title=?";
		Connection conn=DBUtils.getConnection();
		int id=0;
		try {
			List list=runner.query(conn,sql,new BeanListHandler<Inform>(Inform.class),title);
			for(Object x:list){
				id= (int) x;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	@Override
	public int update(Inform inform) throws SQLException {
		int flag=0;
		String sql="UPDATE inform set title=?,content=?,issuer=?,hits=?,date=?,limit=?,keyWord=? WHERE id=?";
		//参数最好按照？顺序
		Object[] params={inform.getTitle(),inform.getContent(),inform.getIssuer(),inform.getHits(),inform.getDate(),inform.getLimit(),inform.getKeyWord()};
		flag=runner.update(DBUtils.getConnection(),sql,params);
		return flag;
	}
	
	@Override
	public List<Inform> findByTitleOrKeyWord(Inform inform) throws SQLException {
		String sql = "select * from inform where title=? or keyWord=? ";
		List list= runner.query(DBUtils.getConnection(), sql,new BeanListHandler<Inform>(Inform.class),inform.getTitle(),inform.getKeyWord());
		return list;
	}
	
	@Override
	public List<Inform> queryAll() throws SQLException {
		String sql = "select title,content,issuer,hits,date,limit,keyWord from inform";
		List<Inform> informs = runner.query(DBUtils.getConnection(), sql, new BeanListHandler<Inform>(Inform.class));
		return informs;
	}
}
