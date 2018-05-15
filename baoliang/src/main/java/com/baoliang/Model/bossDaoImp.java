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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class bossDaoImp extends JdbcDaoSupport implements bossDao{

	public void save(String phone, String pass, String name) {
		this.getJdbcTemplate().update("insert into boss(bossphone,bosspass,bossname) values(?,?,?);", new Object[] {phone,pass,name});
		
	}
	/**
	 * ��֤�Ƿ�������ͨ�����û������û���״̬
	 * @param phone
	 * @return
	 */
	public Integer getVerifyOrNO(String phone)
	{
		Integer res=Integer.parseInt(this.getJdbcTemplate().queryForObject("select verify from boss where bossphone=?",new Object[] {phone},String.class));
		
		if(res.equals(1))
		{
			System.out.println("�Ѿ���֤����Ϣ�����");
			return 1;
		}
		else if(res.equals(2))
		{
			System.out.println("��δ��֤��Ϣ�����");
			return 2;
		}else
		{
			
			System.out.println("�û����ڵȴ���֤���");
			return 3;
		}
	}
	
	/***
	 * �����û�������״̬
	 */
	public void updateUserStatue(String statue,String phone)
	{
		
		this.getJdbcTemplate().update("update boss set verify=? where bossphone=?",new Object[] {statue,phone});
	}
	/**
	 * ������˽��
	 * @param phone
	 * @param description
	 * @param statue
	 */
	public void updateShenheRes(String phone,String description,int statue)
	{
		this.getJdbcTemplate().update("update boss set verify=?,description=? where bossphone=?",new Object[] {statue,description,phone});
	}
	/**
	 * �޸��û������֤ͼƬ·��
	 * @param path
	 * @param flag 0:���� 1:����
	 * @param phone
	 */
	public void updateUserPic(String path,int flag,String phone)
	{
		switch(flag)
		{
		case 0:this.getJdbcTemplate().update("update boss set carda=? where bossphone=?",new Object[] {path,phone});
				break;
		case 1:this.getJdbcTemplate().update("update boss set cardb=? where bossphone=?",new Object[] {path,phone});
				break;
		}
	}
	public String slectname(String phone)
	{
		
		
		return this.getJdbcTemplate().queryForObject("select bossname from boss where bossphone=?",new Object[] {phone},String.class);
	}

	public void update(String passold, String phone, String pass, String name) {
		this.getJdbcTemplate().update("update boss set bosspass= ? where bossphone = ? and bossname= ?",new Object[] {pass,phone,passold});
		
	}

	public void delete(String phone) {
		this.getJdbcTemplate().update("delete from boss where bossphone =?",new Object[] {phone});
		
	}

	
	public boss findByphoneandpass(String phone) {
		return this.getJdbcTemplate().queryForObject("select * from boss where bossphone= ?",new Object[] {phone}, boss.class);
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<boss> finduserbyphone(String phone) {
		
		return this.getJdbcTemplate().query("select * from boss where bossphone=?", new Object[]{phone},new BeanPropertyRowMapper(boss.class));
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<boss> findalluser(int statue) {
		
		return this.getJdbcTemplate().query("select * from boss where verify=?", new Object[]{statue},new BeanPropertyRowMapper(boss.class));
		
	}
	
	public List<boss> findAll() {
		return this.getJdbcTemplate().queryForList("select * from boss",boss.class);
	}
	@SuppressWarnings(value = { "unchecked" })
	public boolean confirm(String phone, String pass) {
		boolean flag=true;

		flag=this.getJdbcTemplate().query("select bossname from boss where bossphone=? and bosspass = ?  ", new Object[] {phone,pass},new ResultSetExtractor() {

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
