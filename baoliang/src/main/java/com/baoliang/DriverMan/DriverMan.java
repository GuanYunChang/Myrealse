package com.baoliang.DriverMan;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baoliang.Model.Drivers;
import com.baoliang.Model.DriversDaoImp;
import com.baoliang.Model.bossDaoImp;
import com.baoliang.Tools.jsontools;
import com.baoliang.Tools.netTools;
import com.baoliang.Tools.produceacnum;
import com.opensymphony.xwork2.ActionSupport;

public class DriverMan extends ActionSupport {
		
	private String drivernums;
	private String name;
	private String phone;
	private String carnum;
	private String cargo;
	private String statue;
	private String pass;
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getStatue() {
		return statue;
	}

	public void setStatue(String statue) {
		this.statue = statue;
	}

	public String getDrivernums() {
		return drivernums;
	}

	public void setDrivernums(String drivernums) {
		this.drivernums = drivernums;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	private String jsonString;
	
	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
/**
 * 在司机管理页面查找所有的司机
 * @return
 * @throws JSONException
 * @throws IllegalArgumentException
 * @throws IllegalAccessException
 */
	public String getdriversdata() 
	{

		try {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		setJsonString(jsontools.tojsonForNoArray(Drimp.finallDriver(), Drivers.class));
		return SUCCESS;
		}catch(Exception e)
		{
			System.out.println("����");
			return ERROR;
		}
	}
	
	/**
	 * 司机信息编辑页面过渡
	 * @return
	 */
	
	public String  editedriver()
	{
		System.out.println("司机信息页面过渡"+statue);
		return SUCCESS;
	}
	
	/**
	 * 修改司机信息
	 * @return
	 */
	public String updateuserinfo()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		Drimp.updateuserinfoForManager(drivernums, name, phone, carnum, Double.parseDouble(cargo));
		return SUCCESS;
	}
	
	public String deldriver()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		Drimp.delete(drivernums);
		System.out.println("删除"+drivernums);
		return SUCCESS;
	}
	
	
	public String submitdriver()
	{
		try {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		Drimp.save(produceacnum.getdriverid(), name, phone,produceacnum.getdriverid(), carnum, Double.parseDouble(cargo), "1", "0");
		
		}catch(Exception e)
		{
			
			return ERROR;
		}
		return SUCCESS;
	}
	
	private String keys;
	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}
private String searchdriver;
	public String getSearchdriver() {
	return searchdriver;
}

public void setSearchdriver(String searchdriver) {
	this.searchdriver = searchdriver;
}

	public String searchdriver()
	{
		
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
			DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
			setSearchdriver(jsontools.tojsonForNoArray(Drimp.getDriverLike(getKeys()), Drivers.class));
			return SUCCESS;
			}catch(Exception e)
			{
				System.out.println("����");
				return ERROR;
			}
	}
	
	
	public String setdriverinfo()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		Drimp.setdriverinfo(drivernums, name, phone, carnum, Double.parseDouble(cargo), pass);
		HttpServletResponse response = ServletActionContext.getResponse();
		netTools.json(response, "{\"statue\":\"true\"}", "utf-8");
		return SUCCESS;
		
	}
}
