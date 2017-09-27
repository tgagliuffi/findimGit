package com.bbva.findim.dom.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {
	
	public static BigDecimal formaterBigDecimal(Double dbNumber, BigDecimal bgNumber, String strNumber, Object objNumber) {
		BigDecimal bgAmount = new BigDecimal(0.00); 
		DecimalFormat df = new DecimalFormat("#.00");
		if(dbNumber!=null)
			bgAmount= new BigDecimal(df.format(dbNumber));
		else if (bgNumber!=null)
			bgAmount= new BigDecimal(df.format(bgNumber));
		else if (objNumber!=null)
			bgAmount= new BigDecimal(df.format(objNumber));
		else {
			strNumber = strNumber.replace(",",".");
			if(isNumeric(strNumber)) {
				bgAmount= new BigDecimal(df.format(strNumber));
			}
		}
		return bgAmount;
	}

	public static String formaterString(Double dbNumber, BigDecimal bgNumber, String strNumber, Object objNumber) {
		String strAmount = "0.00"; 
		DecimalFormat df = new DecimalFormat("#.00");
		if(dbNumber!=null)
			strAmount= df.format(dbNumber);
		else if (bgNumber!=null)
			strAmount= df.format(bgNumber);
		else if (objNumber!=null)
			strAmount= df.format(objNumber);
		else {
			strNumber = strNumber.replace(",",".");
			if(isNumeric(strNumber)) 
				strAmount= df.format(strNumber);
		}
		return strAmount;
	}
	
	public static Double formaterDouble(Double dbNumber, BigDecimal bgNumber, String strNumber,Object objNumber) {
		Double dbAmount = 0.00; 
		DecimalFormat df = new DecimalFormat("#.00");
		if(dbNumber!=null)
			dbAmount= Double.parseDouble(df.format(dbNumber)) ;
		else if (bgNumber!=null)
			dbAmount= Double.parseDouble(df.format(bgNumber));
		else if (objNumber!=null)
			dbAmount= Double.parseDouble(df.format(objNumber));
		else {
			strNumber = strNumber.replace(",",".");
			if(isNumeric(strNumber)) {
				dbAmount= Double.parseDouble(df.format(strNumber));;
			}
		}
		return dbAmount;
	}
	
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
