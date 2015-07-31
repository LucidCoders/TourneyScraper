package com.lucidcoders.tournamentscraper.scrape;

import java.util.List;

import com.lucidcoders.tournamentscraper.gae.TourneyDetailApi;
import com.lucidcoders.tournamentscraper.util.MyLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TourneyDetails;

public class AtlasFullScrape {

    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("**************************************** ATLAS FULL SCRAPE LOG ****************************************");
	logger.appendToLog("*******************************************************************************************************\n");

	AtlasAreasScrape areaScrape = new AtlasAreasScrape();
	areaScrape.execute();
	
	List<String> areaUrls = areaScrape.getAreaUrls();
	if (areaUrls.size() > 0) {
	    
	    logger.appendLogEntry("********** Success getting Area Urls **********\n");
	    
	    AtlasUpcomingScrape upcomingScrape;
	    
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
				    TourneyDetailApi.getInstance().updateEvent(tourneyDetails);
				}
			    }
			}).start();
			
			
		    } else {
			logger.appendLogEntry("********** Failed to get Event Details : " + url + " **********\n");
		    }
		    
		} else {
		    logger.appendLogEntry("********** Failed to get Event Links : " + url + " **********\n");
		}
		
		break; //TODO breaking after one area for testing.  Remove for full scrape
	    }
	    
	} else {
	   logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	
	logger.closeFile();
    }
}






































