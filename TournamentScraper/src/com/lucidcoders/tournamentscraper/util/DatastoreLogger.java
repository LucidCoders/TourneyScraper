package com.lucidcoders.tournamentscraper.util;

public class DatastoreLogger extends MyLogger {
    
    private static DatastoreLogger mDatastoreLogger;
    private String mFileName;
    
    public DatastoreLogger(String fileName) {
	mFileName = fileName;
    }
    
    public static synchronized DatastoreLogger getInstance() {
	if (mDatastoreLogger == null) {
	    mDatastoreLogger = new DatastoreLogger("Datastore");
	}
	
	return mDatastoreLogger;
    }

    @Override
    public boolean initialize() {
	return initializeHelper(mFileName);
    }
}
