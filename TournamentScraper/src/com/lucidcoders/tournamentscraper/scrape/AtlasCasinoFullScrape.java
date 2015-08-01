package com.lucidcoders.tournamentscraper.scrape;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;
import com.lucidcoders.tournamentscraper.util.MyLogger;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;

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
		    
		    logger.appendLogEntry("********** Success getting Poker Room Links : " + url + " **********\n");
		    
		    for (Result pokerRoom : pokerRoomsScrape.getPokerRooms()) {
			pokerRooms.add(pokerRoom);
		    }
		    
		} else {
		    failedRoomsUrls.add(url);
		    logger.appendLogEntry("********** Failed getting Poker Room Links : " + url + " **********\n");
		}
		break;//TODO for testing, remove later
	    }
	    
	    if (failedRoomsUrls.size() > 0) {
		logger.appendLogEntry("********** Begin Failed Poker Room Links Retry **********");

		for (String url : failedRoomsUrls) {

		    AtlasPokerRoomsScrape pokerRoomsScrape = new AtlasPokerRoomsScrape(url);
		    pokerRoomsScrape.execute();

		    if (pokerRoomsScrape.getPokerRooms().size() > 0) {

			logger.appendLogEntry("********** Success getting Poker Room Links on Retry : " + url + " **********\n");

			for (Result pokerRoom : pokerRoomsScrape.getPokerRooms()) {
			    pokerRooms.add(pokerRoom);
			}

		    } else {
			logger.appendLogEntry("********** Failed getting Poker Room Links on Retry : " + url + " **********\n");
		    }
		}
		
		logger.appendLogEntry("********** Complete Failed Poker Room Links on Retry **********\n");
	    }
	    
	    if (pokerRooms.size() > 0) {
		
		AtlasCasinoScrape casinoScrape = new AtlasCasinoScrape(pokerRooms);
		casinoScrape.execute();
		
		List<Casino> casinos = casinoScrape.getCasinos();

		if (casinos.size() > 0) {
		    logger.appendLogEntry("********** Success getting Casinos **********\n");

		    for (Casino casino : casinos) {

			//TODO implement insert
			String testResponse = new Gson().toJson(casino, Casino.class);
			System.out.println("Testing: " + testResponse);
		    }

		} else {
		    logger.appendLogEntry("********** Failed getting Casinos **********\n");
		}

		// TODO Implement retry here
		List<Result> failedRooms = casinoScrape.getFailedRooms();
		if (failedRooms.size() > 0) {
		    logger.appendLogEntry("********** Begin Failed Casinos Retry **********");

		    AtlasCasinoScrape casinoScrapeRetry = new AtlasCasinoScrape(failedRooms);
		    casinoScrapeRetry.execute();

		    List<Casino> casinosRetry = casinoScrapeRetry.getCasinos();

		    if (casinosRetry.size() > 0) {
			logger.appendLogEntry("********** Success getting Casinos on Retry **********\n");

			for (Casino casino : casinosRetry) {

			    // TODO implement insert
			    String testResponse = new Gson().toJson(casino, Casino.class);
			    System.out.println("Testing: " + testResponse);
			}

		    } else {
			logger.appendLogEntry("********** Failed getting Casinos on Retry **********\n");
		    }
		}
	    } else {
		logger.appendLogEntry("********** Failed to get Poker Room Links **********\n");
	    }
	} else {
	    logger.appendLogEntry("********** Failed to get Area Urls **********\n");
	}
	logger.closeFile();
    }
}

























