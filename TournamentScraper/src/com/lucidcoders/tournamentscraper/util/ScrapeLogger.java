package com.lucidcoders.tournamentscraper.util;


public class ScrapeLogger extends MyLogger {

    private static ScrapeLogger mScrapeLogger;

    public static synchronized ScrapeLogger getInstance() {
	if (mScrapeLogger == null) {
	    mScrapeLogger = new ScrapeLogger();
	}
	return mScrapeLogger;
    }

    @Override
    public boolean initialize() {
	return initializeHelper("Scrape");
    }
}
