/**
 *This is baoliang project
 *@author:baoliang
 **/
package com.baoliang.Tools;

import java.util.Calendar;

public class produceacnum {
/**
 * ����������id
 * @return
 */
	public static String getacnum()
	{
		
		String str="";
		Calendar now = Calendar.getInstance(); 
		str+=now.get(Calendar.YEAR);
		str+=now.get(Calendar.MONTH);
		str+=now.get(Calendar.DAY_OF_MONTH);
		str+=now.get(Calendar.HOUR_OF_DAY);
		str+=now.get(Calendar.MINUTE);
		str+=now.getTimeInMillis();
		return str;
		
	}
	
	public static String getdriverid()
	{
		
		String str="d";
		Calendar now = Calendar.getInstance(); 
		str+=now.get(Calendar.YEAR);
		str+=now.get(Calendar.MONTH);
		str+=now.get(Calendar.DAY_OF_MONTH);
		str+=now.get(Calendar.HOUR_OF_DAY);
		str+=now.get(Calendar.MINUTE);
		str+=now.getTimeInMillis();
		return str;
		
	}
	
}
