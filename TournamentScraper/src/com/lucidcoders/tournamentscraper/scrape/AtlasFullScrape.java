package com.lucidcoders.tournamentscraper.scrape;

import java.util.List;

import com.lucidcoders.tournamentscraper.util.MyLogger;

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
		    // TODO get event/tourney details
		    
		} else {
		    logger.appendLogEntry("********** Failed to get Event Links : " + url + " **********\n");
		}
		
		break;
	    }
	    
	} else {
	   logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
    }
}






































