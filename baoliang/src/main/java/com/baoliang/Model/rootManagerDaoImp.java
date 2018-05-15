/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class rootManagerDaoImp extends JdbcDaoSupport implements rootManagerDao{

	public void save(String name, String pass) {
		this.getJdbcTemplate().update("insert into rootmanager(rootname,rootpass) values(?,?)",new Object[] {name,pass});
		
	}

	public void update(String name, String pass) {
		this.getJdbcTemplate().update("update rootmanager set rootpass=? where rootname=?",new Object[] {pass,name});
		
	}

	public void delete(String name, String pass) {
	this.getJdbcTemplate().update("deleted from rootmanager where rootname=?",new Object[] {name});
		
	}

	public rootManager query(String name, String pass) {
		
		
		
		return this.getJdbcTemplate().queryForObject("select * from rootmanager where name=? and pass=?",new Object[] {name,pass} ,rootManager.class);
	
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean confirm(String name, String pass) {
		boolean flag=true;

		
		flag=this.getJdbcTemplate().query("select rootname from rootmanager where rootname=? and rootpass = ?  ", new Object[] {name,pass},new ResultSetExtractor() {

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
		/*rootManager ro=this.getJdbcTemplate().queryForObject("select * from rootmanager where rootname=? and rootpass = ?",new Object[] {name,pass} ,rootManager.class);
		if(ro.equals(null))
		{
			flag=false;
		}*/
		return flag;
	}
}
