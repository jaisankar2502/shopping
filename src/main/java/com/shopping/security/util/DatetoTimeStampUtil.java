/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shopping.security.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author muhmin
 */
public class DatetoTimeStampUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DatetoTimeStampUtil.class);
	public static Timestamp convertStringToTimestamp(String str_date, String dateformat) {
	    try {
	      DateFormat formatter;
	      formatter = new SimpleDateFormat(dateformat);
	      Date date = formatter.parse(str_date);
	      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());
	      return timeStampDate;
	    } catch (ParseException e) {
	    	return null;
	  }
	}
	public static String convertDateToString(Date givendate, String dateformat) {
	    try {
	      DateFormat formatter;
	      
	      formatter = new SimpleDateFormat(dateformat);
	      String dateString = formatter.format(givendate);
	      return dateString;
	    } catch (Exception e) {
	    	return null;
	  }
	}

	public static String convertTimeStampStringToDate(String timeStamp, String pattern) {
		String date = null;
		try {
			DateFormat formatter;
		      formatter = new SimpleDateFormat(pattern);
			Date date1 = formatter.parse(timeStamp);
			date = formatter.format(date1);
		} catch (ParseException e) {
			LOGGER.warn("TimeStamp Conversion : "+e.getMessage());
		}
		return date;
	}
	public static Date convertStringToDate(String date) {
		Date dateConverted = null;
		try {
			DateFormat formatter;
		      formatter = new SimpleDateFormat("yyyy-MM-dd");
		      dateConverted = formatter.parse(date);
		} catch (ParseException e) {
			LOGGER.warn("Date Conversion : "+e.getMessage());
		}
		return dateConverted;
	}
	public static boolean validateDate(String date, String pattern) {
		Matcher m = Pattern.compile(pattern).matcher(date);
		return m.find();
	}
        public  static Time convertingStringToTime(String str_date, String timeformat){
		try{
			DateFormat formatter;
			formatter =new SimpleDateFormat(timeformat);
			Date date = formatter.parse(str_date);
			java.sql.Time time = new Time(date.getTime());
			return time;
		}
		catch (Exception e) {
			return null;
		}
	}
    
}
