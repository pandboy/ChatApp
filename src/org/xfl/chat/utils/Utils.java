package org.xfl.chat.utils;

import java.util.Calendar;

public class Utils {
	
	public static String getCurrDateTime() {
		StringBuffer sb = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		sb.append(cal.get(Calendar.DAY_OF_YEAR))
		  .append("-")
		  .append(cal.get(Calendar.DAY_OF_MONTH))
		  .append("-")
		  .append(cal.get(Calendar.DATE))
		  .append(" ")
		  .append(cal.get(Calendar.HOUR_OF_DAY))
		  .append(":")
		  .append(cal.get(Calendar.MINUTE))
		  ;
		
		return sb.toString();
	}
}
