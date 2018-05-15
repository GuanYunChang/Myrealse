/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Model;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.baoliang.Tools.produceacnum;

public class ApplicationDaoImp extends JdbcDaoSupport implements ApplicationDao {

	public void save( String boss, String phone, String goods, String start, String destination,String statue,String weight,String longstart,String latistart,String longdes,String latides,String receiver,String recephone) {
		
		System.out.println(latistart+"========================");
		this.getJdbcTemplate().update("insert into  application(acnum,boss,phone,goods,start,destination,statue,weight,car,drivernum,lat,longt,latdes,longdes,receiver,recephone) values(?,?,?,?,?,?,?,?,' ',' ',?,?,?,?,?,?)",new Object[]{produceacnum.getacnum(),boss,phone,goods,start,destination,statue,Double.parseDouble(weight),latistart,longstart,latides,longdes,receiver,recephone});
		
	}
	public void lockapplication(String acnum)
	{
		this.getJdbcTemplate().update("update application set lockversion=2 where acnum=?",new String[] {acnum});
		
	}
	public void setdriverforapplication(String carnum,String drivernum,String acnum)
	{
		
		System.out.println("the select driver is"+carnum+","+drivernum+","+acnum);
		this.getJdbcTemplate().update("update application set statue=?,car=?,drivernum=? where acnum=?",new Object[] {"3",carnum,drivernum,acnum});
		
	}
	public void unlockapplication(String acnum) {
		
		this.getJdbcTemplate().update("update application set lockversion=1 where acnum=?",new String[] {acnum});
	}
	public void modifyfortrans(String drivernum,String car,double weight,String statue,String acnum)
	{
		
		this.getJdbcTemplate().update("update application set drivernum= ?,car=?,weight=?,statue=? where acnum=?",new Object[]{drivernum,car,weight,statue,acnum});
	}
	
	public void update(String acnum, String boss, String phone, String goods, String start, String destination,String weight,String longstart,String latistart,String longdes,String latides) {
		System.out.println(longstart+","+latistart+","+longstart+","+longdes);
		this.getJdbcTemplate().update("update application set boss=?,phone=?,goods=?,start=?,destination=?,weight=?,longt=?,lat=?,longdes=?,latdes=? where acnum=?",new Object[]{boss,phone,goods,start,destination,weight,longstart,latistart,longdes,latides,acnum});
	}

	
	
	public void updatetableyes(String acnum, String boss, String phone, String goods, String start, String destination,String weight,String car,String drivernum,String longstart,String latistart,String longdes,String latides) {
		this.getJdbcTemplate().update("update application set boss=?,phone=?,goods=?,start=?,destination=?,weight=?,car=?,drivernum=?,longt=?,lat=?,longdes=?,latdes=? where acnum=?",new Object[]{boss,phone,goods,start,destination,weight,car,drivernum,longstart,latistart,longdes,latides,acnum});
		System.out.println(car);
	}
	
	public void delete(String acnum) {
		this.getJdbcTemplate().update("delete from application where acnum =?",new Object[] {acnum});
		
	}

	public Application findByAcnum(String acnum) {
		
		return this.getJdbcTemplate().queryForObject("select * from application where acnum=?", new Object[] {acnum},Application.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Application> findAll(String statue) {
		
		return this.getJdbcTemplate().query("select * from application where statue=?", new Object[]{statue},new BeanPropertyRowMapper(Application.class));
		
	}
	/**
	 *
	 * @param drivernum
	 * @return
	 */
	public List<Application> Mobile_findUserDoing(String drivernum)
	{
		return this.getJdbcTemplate().query("select * from application where statue='3' and drivernum=?", new Object[]{drivernum},new BeanPropertyRowMapper(Application.class));
	}
	/**
	 * 
	 * @param acnum
	 * @return
	 */
	public boolean Mobile_setStatue(String drivernum)
	{
		try {
		List<Application> ap=	 this.getJdbcTemplate().query("select * from application where statue=? and drivernum=?", new Object[]{"3",drivernum},new BeanPropertyRowMapper(Application.class));
			System.out.println("tst");
		this.getJdbcTemplate().update("update application set statue =? where acnum=?",new String[] {"2",ap.get(0).getAcnum()});
		this.getJdbcTemplate().update("update drivers set statue ='1' where drivernums=?",new String[] {ap.get(0).getDrivernum()});
			System.out.println("the acnum is:"+drivernum);
		}catch(Exception e)
		{
			
			System.out.println("修改订单状态失败");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 
	 * @return
	 */
	public List<Application> FindFinishedApBydrivernum(String drivernum)
	{
		return this.getJdbcTemplate().query("select * from application where statue=? and drivernum=?", new Object[]{"2",drivernum},new BeanPropertyRowMapper(Application.class));
		
	}
public List<Application> findAllunlock(String statue) {
		
		return this.getJdbcTemplate().query("select * from application where statue=? and lockversion=1", new Object[]{statue},new BeanPropertyRowMapper(Application.class));
		
	}
	
	public Integer[] getcountofmaintable()
	{
		Integer[] counts = {this.getJdbcTemplate().queryForObject("select count(*) from application where statue='1'", Integer.class),this.getJdbcTemplate().queryForObject("select count(*) from drivers where statue='2'",Integer.class), this.getJdbcTemplate().queryForObject("select count(*) from application where statue='3'", Integer.class)};
		
		return counts;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Application> findAllbyacmnum(String acnum) {
		
		String str= "select * from application where acnum like'%"+acnum+"%'";
		return this.getJdbcTemplate().query(str,new BeanPropertyRowMapper(Application.class));
		
	}
	
	
	//浠ヤ笅鐢ㄤ簬鐢ㄦ埛鑾峰彇鏁版嵁淇℃伅
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Application> FindApplicationByUserPhoneForFinishedApplication(String phone) {
		
		String str= "select * from application where statue='2' and phone='"+phone+"'";
		System.out.println(str);
		return this.getJdbcTemplate().query(str,new BeanPropertyRowMapper(Application.class));
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Application> FindApplicationByUserPhoneForNotFinishedApplication(String phone) {
		String str= "select * from application where statue='1' and phone='"+phone+"'";
		System.out.println(str);
		return this.getJdbcTemplate().query(str,new BeanPropertyRowMapper(Application.class));
		
		
		
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Application> FindApplicationByUserPhoneForFinishedDistributionApplication(String phone) {
		
		String str= "select * from application where statue='3' and phone='"+phone+"'";
		System.out.println(str);
		return this.getJdbcTemplate().query(str,new BeanPropertyRowMapper(Application.class));
		
	}

}
