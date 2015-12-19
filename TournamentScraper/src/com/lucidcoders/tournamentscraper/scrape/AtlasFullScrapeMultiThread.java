package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class AtlasFullScrapeMultiThread {

public void execute() {
	
	ScrapeLogger logger = ScrapeLogger.getInstance();
	if (!logger.initialize()) return;
	
	logger.writeToLog("**************************************** ATLAS FULL SCRAPE LOG ****************************************");
	logger.appendToLog("*******************************************************************************************************\n");

	AtlasAreasScrape areaScrape = new AtlasAreasScrape();
	areaScrape.execute();

	List<String> areaUrls = areaScrape.getAreaUrls();
	if (areaUrls.size() > 0) {
	    
	    logger.appendLogEntry("********** Success getting Area Urls **********\n");
	    
	    int count = 1;
	    for (final String url : areaUrls) {
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
			int pages = 10;
			if (url.contains("las-vegas-nevada")) {
			    pages = 40;
			}
			
			AtlasUpcomingScrape upcomingScrape = new AtlasUpcomingScrape(url, pages, eventLogger);
			upcomingScrape.execute();

			List<String> eventLinks = upcomingScrape.getEventLinks();
			if (eventLinks.size() > 0) {

			    eventLogger.appendLogEntry("********** Success getting Event Links : " + url + " **********\n");

			    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks).execute();

			    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
			    if (eventDetails.size() > 0) {

				eventLogger.appendLogEntry(
					"********** Success getting Event Details : " + url + " **********\n");

				for (TourneyDetails tourneyDetails : eventDetails) {
				    try {
					TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				    } catch (IOException | GeneralSecurityException e) {
					e.printStackTrace();
				    }
				}
			    } else {
				eventLogger.appendLogEntry(
					"********** Failed to get Event Details : " + url + " **********\n");
			    }

			    List<String> failedEventUrls = detailScrape.getFailedUrls();
			    if (failedEventUrls.size() > 0) {
				eventLogger.appendLogEntry("********** Begin Failed Events Retry **********");

				AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(failedEventUrls);
				detailScrapeRetry.execute();

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

			} else {
			    eventLogger.appendLogEntry("********** Failed to get Event Links : " + url + " **********\n");
			    // ****************************************************************************
			    // retry failed area urls + event details and retries

			    eventLogger.appendLogEntry("********** Begin Failed Area Retry : " + url + " **********");

			    AtlasUpcomingScrape upcomingScrapeRetry = new AtlasUpcomingScrape(url, 10, eventLogger);
			    upcomingScrapeRetry.execute();

			    List<String> eventLinksRetry = upcomingScrapeRetry.getEventLinks();
			    if (eventLinksRetry.size() > 0) {

				eventLogger.appendLogEntry(
					"********** Success getting Event Links : " + url + " **********\n");

				AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinksRetry).execute();

				final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
				if (eventDetails.size() > 0) {

				    eventLogger.appendLogEntry(
					    "********** Success getting Event Details : " + url + " **********\n");

				    for (TourneyDetails tourneyDetails : eventDetails) {
					try {
					    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
					} catch (IOException | GeneralSecurityException e) {
					    e.printStackTrace();
					}
				    }
				} else {
				    eventLogger.appendLogEntry(
					    "********** Failed to get Event Details : " + url + " **********\n");
				}

				List<String> failedEventUrls = detailScrape.getFailedUrls();
				if (failedEventUrls.size() > 0) {
				    eventLogger.appendLogEntry("********** Begin Failed Events Retry **********");

				    AtlasDetailsScrape detailScrapeRetry = new AtlasDetailsScrape(failedEventUrls);
				    detailScrapeRetry.execute();

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
	   logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	
	logger.closeFile();
    }
}












