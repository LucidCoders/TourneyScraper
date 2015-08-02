package com.lucidcoders.tournamentscraper.util;

public class DatastoreLogger extends MyLogger {
    
    private static DatastoreLogger mDatastoreLogger;
    
    public static synchronized DatastoreLogger getInstance() {
	if (mDatastoreLogger == null) {
	    mDatastoreLogger = new DatastoreLogger();
	}
	
	return mDatastoreLogger;
    }

    @Override
    public boolean initialize() {
	return initializeHelper("Datastore");
    }
}
