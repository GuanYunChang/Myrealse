package com.baoliang.Mobile;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baoliang.Model.Drivers;
import com.baoliang.Model.DriversDaoImp;
import com.baoliang.Tools.netTools;
import com.opensymphony.xwork2.ActionSupport;

public class mobile_setdriver extends ActionSupport{

	private String drivernums;
	private String name;
	private String phone;
	private String carnum;
	private String cargo;
	private String pass;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public void setDrivernums(String drivernums) {
		this.drivernums = drivernums;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setCarnum(String carnum) {
		this.carnum = carnum;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getDrivernums() {
		return drivernums;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getCarnum() {
		return carnum;
	}
	public String getCargo() {
		return cargo;
	}
	public String getPass() {
		return pass;
	}
	public void setdriverinfo()
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		Drimp.setdriverinfo(drivernums, name, phone, carnum, Double.parseDouble(cargo), pass);
		HttpServletResponse response = ServletActionContext.getResponse();
		netTools.json(response, "{\"statue\":\"true\"}", "utf-8");
		
		
	}
	
	public void setdriverstatue()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		if(flag.equals("true"))
			Drimp.setdriverstatueweifenpei(drivernums);
		else if(flag.equals("false"))
		{
			Drimp.setdriverstatuezhengxiu(drivernums);
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		netTools.json(response, "{\"statue\":\"true\"}", "utf-8");
		
	}
	
	public void getdriverstatue()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
		DriversDaoImp Drimp=(DriversDaoImp)context.getBean("DriversDaoImp");
		List<Drivers> dr=Drimp.findstatue(drivernums);
		HttpServletResponse response = ServletActionContext.getResponse();
		netTools.json(response, "{\"statue\":\""+dr.get(0).getStatue()+"\"}", "utf-8");
		
	}
}
