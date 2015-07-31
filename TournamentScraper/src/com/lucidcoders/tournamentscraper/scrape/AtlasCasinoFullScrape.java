package com.lucidcoders.tournamentscraper.scrape;

import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasCasinoFullScrape {

    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("**************************************** ATLAS CASINO SCRAPE LOG ****************************************");
	logger.appendToLog("*******************************************************************************************************\n");

	AtlasAreasScrape areaScrape = new AtlasAreasScrape();
	areaScrape.execute();
	List<String> areaUrls = areaScrape.getAreaUrls();
	
	if (areaUrls.size() > 0) {
	    
	    logger.appendLogEntry("********** Success getting Area Urls **********\n");
	    
	    List<String> pokerRoomUrls = new ArrayList<String>();
	    
	    for (String url : areaUrls) {
		//TODO make Casino
		
		AtlasPokerRoomsScrape pokerRoomsScrape = new AtlasPokerRoomsScrape(url);
		pokerRoomsScrape.execute();
	    }
	    
	} else {
	    logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	
	
	
	
	
	logger.closeFile();
    }
}

























