package com.lucidcoders.tournamentscraper.scrape;

import java.util.ArrayList;
import java.util.List;

import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;
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
	    
	    List<Result> pokerRooms = new ArrayList<Result>();
	    List<String> failedRoomsUrls = new ArrayList<String>();
	    
	    for (String url : areaUrls) {
		
		AtlasPokerRoomsScrape pokerRoomsScrape = new AtlasPokerRoomsScrape(url);
		pokerRoomsScrape.execute();
		
		if (pokerRoomsScrape.getPokerRooms().size() > 0) {
		    
		    logger.appendLogEntry("********** Success getting Poker Rooms : " + url + " **********\n");
		    
		    for (Result pokerRoom : pokerRoomsScrape.getPokerRooms()) {
			pokerRooms.add(pokerRoom);
		    }
		    
		} else {
		    failedRoomsUrls.add(url);
		    logger.appendLogEntry("********** Failed getting Poker Rooms : " + url + " **********\n");
		}
	    }
	    
	    if (failedRoomsUrls.size() > 0) {
		logger.appendLogEntry("********** Begin Failed Poker Rooms Retry **********");

		for (String url : failedRoomsUrls) {

		    AtlasPokerRoomsScrape pokerRoomsScrape = new AtlasPokerRoomsScrape(url);
		    pokerRoomsScrape.execute();

		    if (pokerRoomsScrape.getPokerRooms().size() > 0) {

			logger.appendLogEntry("********** Success getting Poker Rooms on Retry : " + url + " **********\n");

			for (Result pokerRoom : pokerRoomsScrape.getPokerRooms()) {
			    pokerRooms.add(pokerRoom);
			}

		    } else {
			logger.appendLogEntry("********** Failed getting Poker Rooms on Retry : " + url + " **********\n");
		    }
		}
		
		logger.appendLogEntry("********** Complete Failed Poker Rooms Retry **********");
	    }
	    
	    // TODO take pokerRooms Result list and query the Details
	    
	} else {
	    logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	logger.closeFile();
    }
}

























