package com.lucidcoders.tournamentscraper.scrape;

import java.util.List;

import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasFullScrape {

    public void execute() {

	AtlasAreasScrape areaScrape = new AtlasAreasScrape();
	areaScrape.execute();
	
	List<String> areaUrls = areaScrape.getAreaUrls();
	if (areaUrls.size() > 0) {
	    
	    AtlasUpcomingScrape upcomingScrape;
	    
	    for (String url : areaUrls) {
		upcomingScrape = new AtlasUpcomingScrape(url);
	    }
	    
	} else {
	    MyLogger.getInstance().appendLogEntry("Failed to get Area Urls");
	}
    }
}






































