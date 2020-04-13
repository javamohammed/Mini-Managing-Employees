package com.managing.employees.utils;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class Useful {

	private static final Pattern DOUBLE_PATTERN = Pattern.compile(
		    "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
		    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
		    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
		    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
	private static final Format formatter = new SimpleDateFormat("yyyy-MM-dd");
	public static boolean isValidEmail(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	public static boolean isValidPhone(String tel) {
	      String regex  = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
	      return tel.matches(regex);
	   }
	public static boolean isFloat(String s)
	{
	    return DOUBLE_PATTERN.matcher(s).matches();
	}
	public static String getTodayDate() {
		String today = formatter.format(new Date());
		return today;
	}
	public static boolean CompareTwoDates(String HireDate) {
		try {
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
		      Date d1 = sdformat.parse(HireDate);
		      Date d2 = sdformat.parse(getTodayDate());
		      if(d2.compareTo(d1) < 0) {
		          return false;
		       }
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
	public static Date getMyHireDate(String HireDate) {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.parse(HireDate);
		} catch (ParseException e) {
			return null;
		}

	}
	public static boolean isDate(String s)
	{
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			return false;
		}
	    return true;
	}
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
}
