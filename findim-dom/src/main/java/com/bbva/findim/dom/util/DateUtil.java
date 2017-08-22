package com.bbva.findim.dom.util;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class DateUtil {

	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String LOCALE_NAME = "es";
	public static final String COUNTRY_NAME = "PE";
	public static final DateFormat DF_FECHA = new SimpleDateFormat(FORMAT_DATE, new Locale(LOCALE_NAME));
	private DateUtil() {
	}

	 public static String getFecha(Date date, String format){
	    	SimpleDateFormat sdfPeru = new SimpleDateFormat(format, new Locale(LOCALE_NAME, COUNTRY_NAME));
	    	return getFecha(date, sdfPeru);
	    }
	 
	 public static String getFecha(Date date, DateFormat formateador){
	    	try{
	    		return formateador.format(date);
	        }catch(Exception e){
	        	return "";
	        }
	        
	    }
	public static Date getCurrentDateWithoutTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date truncateDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	public  static String convertFormatDate(String inFormat,String outFormat,String inDate) throws ParseException{
		 String outDate = null;
		 
		 SimpleDateFormat fromUser = new SimpleDateFormat(inFormat);
		 SimpleDateFormat myFormat = new SimpleDateFormat(outFormat);
		  
		    if (inDate != null) {
		        try {
		        	outDate=myFormat.format(fromUser.parse(inDate));
		            
		        } catch (ParseException ex){ 
		        }
		    }
		    return outDate;
	 }
	
	
	
}
