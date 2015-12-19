package com.lucidcoders.tournamentscraper.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//import org.apache.commons.




import org.apache.commons.io.IOUtils;

import com.google.api.client.util.DateTime;

public class Util {
    
    public static int mapCasinoId(String casino) {
	
	
	
	return 0;
    }
    
    public static DateTime dateToDateTimeDateOnly(Date date) {
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	Date dateNoTime = null;
	try {
	    dateNoTime = formatter.parse(formatter.format(date));
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	
	return new DateTime(dateNoTime != null ? dateNoTime.getTime() : date.getTime());
    }
    
    public static DateTime dateToDateTime(Date date) {
	DateTime dateTime = new DateTime(date.getTime());
	return dateTime;
    }
    
    public static String formatEventTime(String eventTime) {
	
	if (eventTime.contains("In Late Reg")) {
	    eventTime = eventTime.replace("In Late Reg", "Late Reg");
	}
	
	if (eventTime.contains("–")) {
	    eventTime = eventTime.replace("–", "-");
	}
	
	return eventTime.replaceFirst("m", "m,");
    }
    
    public static String getLogTimeStamp() {
	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": ";
    }
    
    public static DateTime stringToDateTime(String dateString, String dateFormat) {
	DateTime dateTime = null;
	
	try {
	    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
	    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
	    Date date = formatter.parse(dateString);
	    dateTime = dateToDateTime(date);
	} catch (ParseException e) {
	    e.printStackTrace();
	}
	
	return dateTime;
    }
    
    public static byte[] downloadImageUrl(URL url) {
	byte[] imageBytes = null;
	InputStream is = null;
	try {
	  is = url.openStream();
	  imageBytes = IOUtils.toByteArray(is);
	  if (is != null) { is.close(); }
	}
	catch (IOException e) {
	  e.printStackTrace ();
	}
	
	return imageBytes;
    }
}
