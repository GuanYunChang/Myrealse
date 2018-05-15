/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Model;

import java.util.List;

public interface ManagerDao {
	
	public void save(String phone,String pass,String name);
	public void update(String passold ,String phone,String pass,String name);
	public void delete(String phone);
	Manager findByphone(String phone);
	List<Manager>findAll();
	boolean confirm(String phone,String pass);

}
