package com.lucidcoders.tournamentscraper.scrape;

import java.util.List;

import com.lucidcoders.tournamentscraper.util.MyLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetail.model.TourneyDetails;

public class AtlasFullScrape {

    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();

	AtlasAreasScrape areaScrape = new AtlasAreasScrape();
	areaScrape.execute();
	
	List<String> areaUrls = areaScrape.getAreaUrls();
	if (areaUrls.size() > 0) {
	    
	    logger.appendLogEntry("********** Success getting Area Urls **********\n");
	    
	    AtlasUpcomingScrape upcomingScrape;
	    
	    for (String url : areaUrls) {
		upcomingScrape = new AtlasUpcomingScrape(url, 10);
		upcomingScrape.execute();
		
		List<String> eventLinks = upcomingScrape.getEventLinks();
		if (eventLinks.size() > 0) {
		    
		    logger.appendLogEntry("********** Success getting Event Links : " + url + " **********\n");
		    
		    AtlasDetailsScrape detailScrape = new AtlasDetailsScrape(eventLinks);
		    detailScrape.execute();
		    
		    List<TourneyDetails> eventDetails = detailScrape.getEventDetails();
		    if (eventDetails.size() > 0) {
			
			logger.appendLogEntry("********** Success getting Event Details : " + url + " **********\n");
			
			//TODO Update/Insert EventDetails into GAE datastore
			
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
    }
}






































