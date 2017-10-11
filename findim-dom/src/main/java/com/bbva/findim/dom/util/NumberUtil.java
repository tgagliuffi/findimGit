package com.bbva.findim.dom.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtil {
	
	public static BigDecimal formaterBigDecimal(Double dbNumber, BigDecimal bgNumber, String strNumber, Object objNumber) {
		BigDecimal bgAmount = new BigDecimal(0.00); 
		RoundingMode scale = RoundingMode.HALF_UP;
		if(dbNumber!=null)
			bgAmount= new BigDecimal(dbNumber).setScale(2, scale);
		else if (bgNumber!=null) 
			bgAmount= bgNumber.setScale(2, scale);
		else if (objNumber!=null) {
			if(isNumeric(objNumber.toString())) 
				bgAmount= new BigDecimal(objNumber.toString()).setScale(2, scale);
		}else {
			if(isNumeric(strNumber)) {
				bgAmount= new BigDecimal(strNumber).setScale(2, scale);
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
			if(isNumeric(objNumber.toString())) 
			strAmount= df.format(objNumber);
		else {
			if(isNumeric(strNumber)) 
				strAmount= df.format(strNumber);
		}
		return strAmount.replaceAll(",", ".");
	}
	
	public static Double formaterDouble(Double dbNumber, BigDecimal bgNumber, String strNumber,Object objNumber) {
		double dbAmount = 0.00; 
		if(dbNumber!=null)
			dbAmount = (double)Math.round(dbNumber * 100d) / 100d;
		else if (bgNumber!=null)
			dbAmount= (double)Math.round(bgNumber.doubleValue() * 100d) / 100d;
		else if (objNumber!=null)
			if(isNumeric(objNumber.toString())) 
			dbAmount = (double)Math.round(new Double(objNumber.toString()) * 100d) / 100d;
		else {
			if(isNumeric(strNumber)) {
				dbAmount= (double)Math.round(new Double(strNumber) * 100d) / 100d;
			}
		}
		return dbAmount;
	}
	
	private static boolean isNumeric(String cadena){
		try {
			new BigDecimal(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
}
