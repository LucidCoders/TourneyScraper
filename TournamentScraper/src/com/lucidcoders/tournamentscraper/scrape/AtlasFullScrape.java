package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class AtlasFullScrape {

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
	    
	    AtlasUpcomingScrape upcomingScrape;
	    
	    List<String> failedEventUrls = new ArrayList<String>();
	    
	    for (String url : areaUrls) {
		upcomingScrape = new AtlasUpcomingScrape(url, 2);
		upcomingScrape.execute();
		
		List<String> eventLinks = upcomingScrape.getEventLinks();
		if (eventLinks.size() > 0) {
		    
		    logger.appendLogEntry("********** Success getting Event Links : " + url + " **********\n");
		    
		    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks);
		    detailScrape.execute();
		    
		    final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		    if (eventDetails.size() > 0) {
			
			logger.appendLogEntry("********** Success getting Event Details : " + url + " **********\n");
			
			new Thread(new Runnable() {
			    
			    @Override
			    public void run() {
				
				for (TourneyDetails tourneyDetails : eventDetails) {
				    try {
					TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				    } catch (IOException | GeneralSecurityException e) {
					e.printStackTrace();
					// TODO add gae logging
				    }
				}
			    }
			}).start();
			
			
		    } else {
			logger.appendLogEntry("********** Failed to get Event Details : " + url + " **********\n");
		    }
		    
		    for (String failedUrl : detailScrape.getFailedUrls()) {
			failedEventUrls.add(failedUrl);
		    }
		    
		} else {
		    logger.appendLogEntry("********** Failed to get Event Links : " + url + " **********\n");
		}
		
		break; //TODO breaking after one area for testing.  Remove for full scrape
	    }
	    
	    if (failedEventUrls.size() > 0) {
		logger.appendLogEntry("********** Begin Failed Events Retry **********");
		
		AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(failedEventUrls);
		detailScrape.execute();

		final List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		if (eventDetails.size() > 0) {

		    logger.appendLogEntry("********** Success getting Event Details on Retry **********\n");

		    new Thread(new Runnable() {

			@Override
			public void run() {

			    for (TourneyDetails tourneyDetails : eventDetails) {
				try {
				    TourneyDetailService.getInstance().updateEvent(tourneyDetails);
				} catch (IOException | GeneralSecurityException e) {
				    // TODO add gae logging
				    e.printStackTrace();
				}
			    }
			}
		    }).start();

		} else {
		    logger.appendLogEntry("********** Failed to get Event Details on Retry **********\n");
		}

	    } else {
		logger.appendLogEntry("********** No Failed Event Urls **********\n");
	    }
	    
	} else {
	   logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	
	logger.closeFile();
    }
}






































