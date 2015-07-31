package com.lucidcoders.tournamentscraper.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.api.client.util.DateTime;

public class Util {
    
    public static int mapCasinoId(String casino) {
	
	
	
	return 0;
    }
    
    public static DateTime dateToDateTime(Date date) {
	return new DateTime(date.getTime());
    }
    
    public static String formatEventTime(String eventTime) {
	
	if (eventTime.contains("In Late Reg")) {
	    eventTime = eventTime.replace("In Late Reg", "Late Reg");
	}
	
	if (eventTime.contains("�")) {
	    eventTime = eventTime.replace("�", "-");
	}
	
	return eventTime.replaceFirst("m", "m,");
    }
    
    public static String getLogTimeStamp() {
	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": ";
    }
}
