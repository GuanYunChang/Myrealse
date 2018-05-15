/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.MainAction;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;

import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baoliang.Model.Application;
import com.baoliang.Model.ApplicationDaoImp;
import com.baoliang.Model.Drivers;
import com.baoliang.Model.DriversDaoImp;
import com.baoliang.Model.Manager;
import com.baoliang.Model.ManagerDaoImp;
import com.baoliang.Tools.jsontools;

import com.opensymphony.xwork2.ActionSupport;

public class Refresheforbindtable  extends ActionSupport {

	
	private String carnum;
	public String getCarnum() {
		return carnum;
	}



	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}



	private String drivernum;
	public String getDrivernum() {
		return drivernum;
	}



	public void setDrivernum(String drivernum) {
		this.drivernum = drivernum;
	}



	private String acnum;
	public String getAcnum() {
		return acnum;
	}



	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}



	private int pagesum;
	public int getPagesum() {
		return pagesum;
	}



	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}



	private int indexup;
	public int getIndexup() {
		return indexup;
	}



	public void setIndexup(int indexup) {
		this.indexup = indexup;
	}



	public int getIndexdown() {
		return indexdown;
	}



	public void setIndexdown(int indexdown) {
		this.indexdown = indexdown;
	}



	public String getDataup() {
		return dataup;
	}



	public void setDataup(String dataup) {
		this.dataup = dataup;
	}



	public String getDatadown() {
		return datadown;
	}



	public void setDatadown(String datadown) {
		this.datadown = datadown;
	}



	private int indexdown;
	private String dataup;
	private String datadown;
	public String getFlag() {
		return flag;
	}



	public void setFlag(String flag) {
		this.flag = flag;
	}



	private String flag="false";
	
	
	public String refreshtableup() 
	{
		try {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		ApplicationDaoImp apc= (ApplicationDaoImp) context.getBean("ApplicationDaoImp");
		List objlist=apc.findAllunlock("1");
		
		Integer sum=objlist.size();System.out.println("总数为"+sum);
		Integer start=(getPagesum())*(getIndexup()-1)+1;
		
		if(start<1)
			start=1;
		
		Integer end=start+getPagesum();
		if(end>sum)
		{
			end=sum+1;
			
		}
		if(end>sum&&start>end-getPagesum())
		{
			start=end-(end%getPagesum())+1;
			
		}
			objlist=objlist.subList(start-1, end-1);
			if(objlist.size()==0)
			{
				
				setFlag("true");
			}
			System.out.println("start:"+(start-1)+",end:"+(end));
		setDataup(jsontools.tojsonForNoArray(objlist,Application.class ));
		}catch(Exception e)
		{
			
			return ERROR;
			
		}
		return SUCCESS;
		
		
	}
	
	public String selectcar()
	{
		
		Timer selecttime=new Timer();
		Locktask task= new Locktask();
		task.setTasktimer(selecttime);
		task.setAcnum(getAcnum());
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		ApplicationDaoImp apc= (ApplicationDaoImp) context.getBean("ApplicationDaoImp");
		apc.lockapplication(getAcnum());//锁定订单
		System.out.println(getAcnum()+"已锁定");
		selecttime.schedule(task, 60000);//300000 自动解锁
		return SUCCESS;
	}
	
	public String refreshtabledown() throws JSONException, IllegalArgumentException, IllegalAccessException
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp apc= (DriversDaoImp) context.getBean("DriversDaoImp");
		List objlist=apc.findAll("1");
		System.out.println(jsontools.tojsonForNoArray(objlist,Drivers.class )+"!!!");
		Integer sum=objlist.size();
		Integer start=(getPagesum()-1)*getIndexup();
		if(start<0)
			start=0;
		
		Integer end=start+getPagesum();
		if(end>sum)
		{
			end=sum;
			setFlag("true");
		}
		if(end>sum&&start>end-getPagesum())
			start=end-getPagesum();
		//System.out.println("start:"+start+",end:"+end);
			objlist=objlist.subList(start, end);
			System.out.println("start:"+start+",end:"+end);
		setDatadown(jsontools.tojsonForNoArray(objlist,Drivers.class ));
		
		return SUCCESS;
	}
	
	public String selectdriver()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp apc= (DriversDaoImp) context.getBean("DriversDaoImp");
		apc.setStatue(getDrivernum());
		//ApplicationContext context1 = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		ApplicationDaoImp ap= (ApplicationDaoImp) context.getBean("ApplicationDaoImp");
		ap.setdriverforapplication(getCarnum(), getDrivernum(), getAcnum());
		
		return SUCCESS;
	}
}
