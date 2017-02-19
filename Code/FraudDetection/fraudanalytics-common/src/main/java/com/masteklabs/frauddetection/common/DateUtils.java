package com.masteklabs.frauddetection.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static Double determineAge(Date dob) {
		int age = 0;
		Calendar born = Calendar.getInstance();
		Calendar now = Calendar.getInstance();
		if (dob != null) {
			now.setTime(new Date());
			born.setTime(dob);
			if (born.after(now)) {
				throw new IllegalArgumentException("Can't be born in the future");
			}
			age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
			if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
				age -= 1;
			}
		}
		return Double.valueOf(age);
	}
	public static Long getCurrentTimeStamp() {
		return System.currentTimeMillis();
	}
	public static Date getDateFromString(String dateFormat,String dateString) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date outDate =sdf.parse(dateString);
		return outDate;
	}
}
