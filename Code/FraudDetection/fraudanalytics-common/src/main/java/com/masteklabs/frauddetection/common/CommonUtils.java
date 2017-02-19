package com.masteklabs.frauddetection.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.masteklabs.frauddetection.exception.ObjectMappingException;


public class CommonUtils {
	/** The Constant OBJECT_MAPPER. */
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	/** The Constant DD_MMM_YYYY. */
	public static final String YYYYMMDDHHMMSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static <T> Object fromJsonUnchecked(String jsonAsString,
			Class<T> pojoClass) throws ObjectMappingException {
		TimeZone.setDefault(TimeZone.getTimeZone(CommonConstants.TimeZoneConstants.LONDONTIMEZONE));
		
		final DateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSSZ);
		df.setTimeZone(TimeZone.getTimeZone(CommonConstants.TimeZoneConstants.UTCTIMEZONE));
		OBJECT_MAPPER.setDateFormat(df);
		try {
			return OBJECT_MAPPER.readValue(jsonAsString, pojoClass);
		} catch (Exception e) {
			throw new ObjectMappingException(
					"Error in fromJsonUnchecked() for class="
							+ pojoClass.getName() + " and source="
							+ jsonAsString, e);
		}
	}
	public static String getStringFromDateTime(String dateFormat,Long dateTime) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String  dtString =sdf.format(dateTime);
		return dtString;
	}
}
