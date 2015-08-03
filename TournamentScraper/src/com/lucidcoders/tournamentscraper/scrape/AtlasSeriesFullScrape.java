package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.SeriesService;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.util.DatastoreLogger;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;

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

	    AtlasSeriesDetailsScrape seriesDetailsScrape = new AtlasSeriesDetailsScrape(seriesResults).execute();
	    List<Series> seriesList = seriesDetailsScrape.getSeriesDetails();
	    if (seriesList.size() > 0) {
		logger.appendLogEntry("********** Success getting Series Details **********\n");
		for (Series series : seriesList) {
		    try {
			SeriesService.getInstance().updateSeries(series);
			dsLogger.appendLogEntry("Updated: " + series.getSeriesId());
		    } catch (IOException | GeneralSecurityException e) {
			dsLogger.appendLogEntry("Failed to Update: " + series.getSeriesId() + " : "
				+ e.getClass() + " : " + e.getMessage());
			e.printStackTrace();
		    }
		}
	    } else {
		logger.appendLogEntry("********** Failed getting Series Details **********\n");
	    }
	    
	    //TODO retry failed results
	    List<SeriesResult> failedSeriesList = seriesDetailsScrape.getFailedSeriesResults();
	    if (failedSeriesList.size() > 0) {
		logger.appendLogEntry("********** Begin Retry Series Details **********");
		
		AtlasSeriesDetailsScrape seriesDetailsScrapeRetry = new AtlasSeriesDetailsScrape(failedSeriesList).execute();
		List<Series> seriesListRetry = seriesDetailsScrapeRetry.getSeriesDetails();
		
		if (seriesListRetry.size() > 0) {
		    logger.appendLogEntry("********** Success getting Series Details on Retry **********\n");
		    for (Series series : seriesListRetry) {
			try {
			    SeriesService.getInstance().updateSeries(series);
			    dsLogger.appendLogEntry("Updated: " + series.getSeriesId());
			} catch (IOException | GeneralSecurityException e) {
			    dsLogger.appendLogEntry("Failed to Update: " + series.getSeriesId() + " : " + e.getClass()
				    + " : " + e.getMessage());
			    e.printStackTrace();
			}
		    }
		} else {
		    logger.appendLogEntry("********** Failed getting Series Details on Retry **********\n");
		}
	    }
	    
	    //TODO get the details from the seriesResults
	    
	} else {
	    logger.appendLogEntry("********** Failed to get Series Results **********\n");
	}

	logger.closeFile();
	dsLogger.closeFile();
    }
}





















