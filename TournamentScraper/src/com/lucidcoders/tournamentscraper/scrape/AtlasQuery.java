package com.lucidcoders.tournamentscraper.scrape;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.gae.TourneyDetailService;
import com.lucidcoders.tournamentscraper.util.MyLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class AtlasQuery {
    
    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	if (!logger.initialize()) return;
	logger.writeToLog("******************************************* ATLAS QUERY LOG *******************************************");
	logger.appendToLog("*******************************************************************************************************\n");

	List<TourneyDetails> eventDetails = TourneyDetailService.getInstance().listEvents(null, null);
	
	if (eventDetails != null && eventDetails.size() > 0) {

	    Gson gson = new GsonBuilder().create();
	    for (TourneyDetails eventDetail : eventDetails) {
		logger.appendLogEntry(eventDetail.getAddressUrl());
		logger.appendLogEntry(gson.toJson(eventDetail, TourneyDetails.class)
			+ "\n");
	    }
	} else {
	    logger.appendLogEntry("Failed or No Results\n");
	}
	
	logger.closeFile();
    }
}
