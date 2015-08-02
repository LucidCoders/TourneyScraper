package com.lucidcoders.tournamentscraper.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.commons.



import org.apache.commons.io.IOUtils;

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
	
	if (eventTime.contains("–")) {
	    eventTime = eventTime.replace("–", "-");
	}
	
	return eventTime.replaceFirst("m", "m,");
    }
    
    public static String getLogTimeStamp() {
	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ": ";
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
