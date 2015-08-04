package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.SeriesService;
import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.util.DatastoreLogger;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

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
	    
	    //Retry failed SeriesResults
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
	    
	    // Get the details from the seriesEventResults
	    SeriesEventScrape seriesEventScrape = new SeriesEventScrape(seriesResults).execute();
	    List<String> eventLinks = seriesEventScrape.getEventLinks();
	    if (eventLinks.size() > 0) {
		logger.appendLogEntry("********** Success getting Series Event Links **********\n");
		
		AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks).execute();

		final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		if (eventDetails.size() > 0) {

		    logger.appendLogEntry("********** Success getting Event Details **********\n");

		    for (TourneyDetails tourneyDetails : eventDetails) {
			try {
			    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
			    dsLogger.appendLogEntry("Updated: " + tourneyDetails.getAtlasId());
			} catch (IOException | GeneralSecurityException e) {
			    dsLogger.appendLogEntry("Failed to Update: " + tourneyDetails.getAtlasId() + " : "
				    + e.getClass() + " : " + e.getMessage());
			    e.printStackTrace();
			}
		    }
		} else {
		    logger.appendLogEntry("********** Failed to get Event Details **********\n");
		}

		if (detailScrape.getFailedUrls().size() > 0) {
		    logger.appendLogEntry("********** Begin getting Series Event Links Retry **********\n");

		    AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(detailScrape.getFailedUrls()).execute();

		    final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
		    if (eventDetailsRetry.size() > 0) {

			logger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

			for (TourneyDetails tourneyDetails : eventDetailsRetry) {
			    try {
				TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				dsLogger.appendLogEntry("Updated: " + tourneyDetails.getAtlasId());
			    } catch (IOException | GeneralSecurityException e) {
				dsLogger.appendLogEntry("Failed to Update: " + tourneyDetails.getAtlasId() + " : "
					+ e.getClass() + " : " + e.getMessage());
				e.printStackTrace();
			    }
			}
		    } else {
			logger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
		    }
		}
	    } else {
		logger.appendLogEntry("********** Failed getting Series Event Links **********\n");
	    }
	    
	    // Retry Failed SeriesEvent Scrape
	    List<SeriesResult> failedSeriesResults = seriesEventScrape.getFailedSeriesResults();
	    if (failedSeriesResults.size() > 0) {
		logger.appendLogEntry("********** Begin Failed Series Event Links Retry **********\n");
		SeriesEventScrape seriesEventScrapeRetry = new SeriesEventScrape(failedSeriesResults).execute();
		List<String> eventLinksRetry = seriesEventScrapeRetry.getEventLinks();
		if (eventLinksRetry.size() > 0) {
		    logger.appendLogEntry("********** Success getting Series Event Links on Retry **********\n");

		    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinksRetry).execute();

		    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		    if (eventDetails.size() > 0) {

			logger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

			for (TourneyDetails tourneyDetails : eventDetails) {
			    try {
				TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				dsLogger.appendLogEntry("Updated: " + tourneyDetails.getAtlasId());
			    } catch (IOException | GeneralSecurityException e) {
				dsLogger.appendLogEntry("Failed to Update: " + tourneyDetails.getAtlasId() + " : "
					+ e.getClass() + " : " + e.getMessage());
				e.printStackTrace();
			    }
			}
		    } else {
			logger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
		    }

		    if (detailScrape.getFailedUrls().size() > 0) {
			logger.appendLogEntry("********** Begin getting Series Event Links Retry **********\n");

			AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(detailScrape.getFailedUrls()).execute();

			final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
			if (eventDetailsRetry.size() > 0) {

			    logger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

			    for (TourneyDetails tourneyDetails : eventDetailsRetry) {
				try {
				    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				    dsLogger.appendLogEntry("Updated: " + tourneyDetails.getAtlasId());
				} catch (IOException | GeneralSecurityException e) {
				    dsLogger.appendLogEntry("Failed to Update: " + tourneyDetails.getAtlasId() + " : "
					    + e.getClass() + " : " + e.getMessage());
				    e.printStackTrace();
				}
			    }
			} else {
			    logger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
			}
		    }
		} else {
		    logger.appendLogEntry("********** Failed getting Series Event Links **********\n");
		}
	    }
	    
	} else {
	    logger.appendLogEntry("********** Failed to get Series Results **********\n");
	}

	logger.closeFile();
	dsLogger.closeFile();
    }
}





















