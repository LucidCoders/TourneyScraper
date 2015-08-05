package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.SeriesService;
import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class AtlasSeriesFullScrapeMultiThread {
    public void execute() {
	ScrapeLogger logger = new ScrapeLogger("SeriesScrape");
	if (!logger.initialize())
	    return;
	logger.writeToLog("**************************************** ATLAS SERIES SCRAPE LOG ****************************************");
	logger.appendToLog("*********************************************************************************************************\n");

	List<SeriesResult> seriesResults = new AtlasSeriesScrape(logger).execute().getSeriesResults();
	if (seriesResults.size() > 0) {
	    logger.appendLogEntry("********** Success getting Series Results **********\n");

	    AtlasSeriesDetailsScrape seriesDetailsScrape = new AtlasSeriesDetailsScrape(seriesResults, logger).execute();
	    List<Series> seriesList = seriesDetailsScrape.getSeriesDetails();
	    if (seriesList.size() > 0) {
		logger.appendLogEntry("********** Success getting Series Details **********\n");
		for (Series series : seriesList) {
		    try {
			SeriesService.getInstance().updateSeries(series);
		    } catch (IOException | GeneralSecurityException e) {
			e.printStackTrace();
		    }
		}
	    } else {
		logger.appendLogEntry("********** Failed getting Series Details **********\n");
	    }
	    
	    // Retry failed results
	    List<SeriesResult> failedSeriesList = seriesDetailsScrape.getFailedSeriesResults();
	    if (failedSeriesList.size() > 0) {
		logger.appendLogEntry("********** Begin Retry Series Details **********");
		
		AtlasSeriesDetailsScrape seriesDetailsScrapeRetry = new AtlasSeriesDetailsScrape(failedSeriesList, logger).execute();
		List<Series> seriesListRetry = seriesDetailsScrapeRetry.getSeriesDetails();
		
		if (seriesListRetry.size() > 0) {
		    logger.appendLogEntry("********** Success getting Series Details on Retry **********\n");
		    for (Series series : seriesListRetry) {
			try {
			    SeriesService.getInstance().updateSeries(series);
			} catch (IOException | GeneralSecurityException e) {
			    e.printStackTrace();
			}
		    }
		} else {
		    logger.appendLogEntry("********** Failed getting Series Details on Retry **********\n");
		}
	    }
	    
	    // get the details from the seriesResults
	    SeriesEventScrape seriesEventScrape = new SeriesEventScrape(seriesResults, logger).execute();
	    List<ArrayList<String>> eventLinksArray = seriesEventScrape.getEventLinksArray();
	    if (eventLinksArray.size() > 0) {
		logger.appendLogEntry("********** Success getting Series Event Links **********\n");

		int count = 1;
		for (final ArrayList<String> eventLinks : eventLinksArray) {

		    final int counter = count;
		    Thread thread = new Thread(new Runnable() {
		        
			@Override
			public void run() {
			    ScrapeLogger eventLogger = new ScrapeLogger("EventDetailsScrape" + counter);
			    eventLogger.initialize();
			    eventLogger.writeToLog(
				    "**************************************** ATLAS EVENT SCRAPE LOG ****************************************");
			    eventLogger.appendToLog(
				    "*********************************************************************************************************\n");

		            AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks, eventLogger).execute();

			    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
			    if (eventDetails.size() > 0) {

				eventLogger.appendLogEntry("********** Success getting Event Details **********\n");

				for (TourneyDetails tourneyDetails : eventDetails) {
				    try {
					TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				    } catch (IOException | GeneralSecurityException e) {
					e.printStackTrace();
				    }
				}
			    } else {
				eventLogger.appendLogEntry("********** Failed to get Event Details **********\n");
			    }

			    if (detailScrape.getFailedUrls().size() > 0) {
				eventLogger.appendLogEntry("********** Begin getting Series Event Links Retry **********\n");

				AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(detailScrape.getFailedUrls(), eventLogger)
					.execute();

				final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
				if (eventDetailsRetry.size() > 0) {

				    eventLogger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

				    for (TourneyDetails tourneyDetails : eventDetailsRetry) {
					try {
					    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
					} catch (IOException | GeneralSecurityException e) {
					    e.printStackTrace();
					}
				    }
				} else {
				    eventLogger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
				}
			    }
			    eventLogger.closeFile();
		        }
		    });
		    thread.start();
		    try {
			Thread.sleep(5 * 1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    count++;
		}
		
	    } else {
		logger.appendLogEntry("********** Failed getting Series Event Links **********\n");
	    }
	    
	    // Retry failed Series Events
	    List<SeriesResult> failedSeriesResults = seriesEventScrape.getFailedSeriesResults();
	    if (failedSeriesResults.size() > 0) {
		logger.appendLogEntry("********** Begin Failed Series Event Links Retry **********\n");
		SeriesEventScrape seriesEventScrapeRetry = new SeriesEventScrape(failedSeriesResults, logger).execute();
		
		List<ArrayList<String>> eventLinksArrayRetry = seriesEventScrapeRetry.getEventLinksArray();

		int count = 1;
		for (final List<String> eventLinksRetry : eventLinksArrayRetry) {
		    
		    final int counter = count;
		    
		    Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
			    ScrapeLogger eventLogger = new ScrapeLogger("EventDetailsScrapeRetry" + counter);
			    eventLogger.initialize();
			    eventLogger.writeToLog(
				    "**************************************** ATLAS EVENT SCRAPE LOG ****************************************");
			    eventLogger.appendToLog(
				    "*********************************************************************************************************\n");
			    
			    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinksRetry, eventLogger).execute();

			    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
			    if (eventDetails.size() > 0) {

				eventLogger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

				for (TourneyDetails tourneyDetails : eventDetails) {
				    try {
					TourneyDetailService.getInstance().updateEvent(tourneyDetails);
//					dsLogger.appendLogEntry("Updated: " + tourneyDetails.getAtlasId());
				    } catch (IOException | GeneralSecurityException e) {
//					dsLogger.appendLogEntry("Failed to Update: " + tourneyDetails.getAtlasId()
//						+ " : " + e.getClass() + " : " + e.getMessage());
					e.printStackTrace();
				    }
				}
			    } else {
				eventLogger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
			    }

			    if (detailScrape.getFailedUrls().size() > 0) {
				eventLogger.appendLogEntry("********** Begin getting Series Event Links Retry **********\n");

				AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(
					detailScrape.getFailedUrls(), eventLogger).execute();

				final List<TourneyDetails> eventDetailsRetry = detailScrapeRetry.getEventDetails();
				if (eventDetailsRetry.size() > 0) {

				    eventLogger.appendLogEntry(
					    "********** Success getting Event Details on Retry **********\n");

				    for (TourneyDetails tourneyDetails : eventDetailsRetry) {
					try {
					    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
					} catch (IOException | GeneralSecurityException e) {
					    e.printStackTrace();
					}
				    }
				} else {
				    eventLogger.appendLogEntry(
					    "********** Failed to get Event Details on Retry **********\n");
				}
			    }
			    eventLogger.closeFile();
			}
		    });
		    thread.start();
		    try {
			Thread.sleep(5 * 1000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		    count++;
		}
	    }
	} else {
	    logger.appendLogEntry("********** Failed to get Series Results **********\n");
	}

	logger.closeFile();
    }
}
