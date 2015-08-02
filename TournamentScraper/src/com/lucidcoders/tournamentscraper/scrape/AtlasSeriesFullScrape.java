package com.lucidcoders.tournamentscraper.scrape;

import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.util.DatastoreLogger;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;

public class AtlasSeriesFullScrape {

    public void execute() {
	DatastoreLogger dsLogger = DatastoreLogger.getInstance();
	if (!dsLogger.initialize())
	    return;

	ScrapeLogger logger = ScrapeLogger.getInstance();
	if (!logger.initialize())
	    return;

	dsLogger.writeToLog("**************************************** SERIES UPDATE LOG ****************************************");
	dsLogger.appendToLog("***************************************************************************************************\n");

	logger.writeToLog("**************************************** ATLAS SERIES SCRAPE LOG ****************************************");
	logger.appendToLog("*********************************************************************************************************\n");

	List<SeriesResult> seriesResults = new AtlasSeriesScrape().execute().getSeriesResults();
	if (seriesResults.size() > 0) {
	    logger.appendLogEntry("********** Success getting Series Results **********\n");

	    List<SeriesResult> pokerRooms = new ArrayList<SeriesResult>();
	    List<String> failedSeriesUrls = new ArrayList<String>();

	    new AtlasSeriesDetailsScrape(seriesResults).execute();
	    //TODO get the series result and insert
	    
	    //TODO retry failed results
	    
	    //TODO get the details from the seriesResults
	    
	} else {
	    logger.appendLogEntry("********** Failed to get Series Results **********\n");
	}

	logger.closeFile();
	dsLogger.closeFile();
    }
}





















