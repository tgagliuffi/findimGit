package com.bbva.findim.bck.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilDate {

	 public  static Date convertStringToJUtilDate(String str_date, String strFormat) throws ParseException{
		 DateFormat format = new SimpleDateFormat(strFormat);
		 Date date = format.parse(str_date);
		 return date;
	 }
	 
	 public  static String convertJSqlDateToString(java.sql.Date str_date, String strFormat) throws ParseException{
		 DateFormat df = new SimpleDateFormat(strFormat);  
		 String text = df.format(str_date);  
		 return text;
	 }
	 
	 public  static String convertJUtilDateToString(Date str_date, String strFormat) throws ParseException{
		 DateFormat df = new SimpleDateFormat(strFormat);  
		 String text = df.format(str_date);  
		 return text;
	 }
	 
	 public  static java.sql.Date  convertStringToSqlDate(String str_date, String strFormat) throws ParseException{
		 SimpleDateFormat sdf1 = new SimpleDateFormat(strFormat);
		 java.util.Date date = sdf1.parse(str_date);
		 java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
		 return sqlStartDate;
	 }
	 public static String changeFormatString(String str_date, String strFormat) throws ParseException{
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 Date date = format.parse(str_date);
		 DateFormat formatFinal = new SimpleDateFormat(strFormat);
		 String text = formatFinal.format(date);  
		 return text;
	 }
	 
		
	 public static Date sumarRestarHorasFecha(Date fecha, int horas){
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(fecha); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.YEAR, horas);  // numero de horas a añadir, o restar en caso de horas<0
	      return calendar.getTime(); // Devuelve el objeto Date con las nuevas horas añadidas
	 }
}
