/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class Sessionverify {

	
	public static boolean verify()
	{
		HttpServletRequest request = ServletActionContext.getRequest(); 
		HttpSession session = request.getSession(); 
		try {
			if(session.getAttribute("username").equals(null))
				{
				System.out.println("�ݴ���Ϣ��֤ʧ��");
				return false;
				}
			else
				return true;
		}catch(Exception e) {
			
			System.out.println("�ݴ���Ϣ��֤�ɹ�");
			return false;
		}
	}
}
