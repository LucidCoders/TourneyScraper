package com.lucidcoders.tournamentscraper.util;


public class ScrapeLogger extends MyLogger {

    private static ScrapeLogger mScrapeLogger;
    private String mFileName;
    
    public ScrapeLogger(String fileName) {
	mFileName = fileName;
    }

    public static synchronized ScrapeLogger getInstance() {
	if (mScrapeLogger == null) {
	    mScrapeLogger = new ScrapeLogger("Scrape");
	}
	return mScrapeLogger;
    }

    @Override
    public boolean initialize() {
	return initializeHelper(mFileName);
    }
}
