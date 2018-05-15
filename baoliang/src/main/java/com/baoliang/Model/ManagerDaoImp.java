/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Model;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ManagerDaoImp extends JdbcDaoSupport implements ManagerDao{

	 

	public void save(String phone,String pass,String name) {
		
		this.getJdbcTemplate().update("insert into manager(phone,pass,name) values(?,?,?);", new Object[] {phone,pass,name});
		
	}

	public void update(String passold ,String phone,String pass,String name) {
		this.getJdbcTemplate().update("update manager set pass= ? where phone = ? and pass= ?",new Object[] {pass,phone,passold});
		
	}
	public void updateinfo(String phone,String name)
	{
		
		this.getJdbcTemplate().update("update manager set name= ? where phone = ? ",new Object[] {name,phone});
	}

	public void delete(String phone) {
		
		this.getJdbcTemplate().update("delete from manager where phone =?",new Object[] {phone});
		
	}

	public Manager findByphone(String phone) {
		
		return this.getJdbcTemplate().queryForObject("select * from manager where phone= ?",new Object[] {phone}, Manager.class);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Manager> findAll() {
		
		return this.getJdbcTemplate().query("select * from manager",new BeanPropertyRowMapper(Manager.class));
	}
@SuppressWarnings(value = { "unchecked" })
	public boolean confirm(String phone, String pass) {
		
		boolean flag=true;

		flag=this.getJdbcTemplate().query("select name from manager where phone=? and pass = ?  ", new Object[] {phone,pass},new ResultSetExtractor() {

			public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				try {
					
					rs.last();
					int count=rs.getRow();
					if(count==0)
					{
						
						return false;
					}
					return true;
				}catch(NullPointerException e) {
					
					return false;
					
				}
			}
		
		
		});
		return flag;
	}
	  
	  
}
