package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasPokerRoomsScrape {

    private String mUrl;
    
    public AtlasPokerRoomsScrape(String areaUrl) {
	mUrl = "http://www.pokeratlas.com/poker-rooms/" + areaUrl.replace("http://www.pokeratlas.com/", "");
    }
    
    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	logger.appendLogEntry("Begin Atlas Poker Rooms Scrape : " + mUrl);
	
	ImportIoRequest pokerRoomsRequest = new ImportIoRequest(mUrl);
	
	HttpResponse response;
	try {
	    response = pokerRoomsRequest.queryGet(Extractor.ATLAS_POKER_ROOMS);
	} catch (URISyntaxException | IOException e) {
	    e.printStackTrace();
	    logger.appendLogEntry(
		    "Failed to send AtlasPokerRooms request : " + mUrl + " : " + e.getClass() + " : " + e.getMessage());
	    logger.appendLogEntry("Complete Atlas Poker Rooms Scrape : " + mUrl);
	    return;
	}

	if (response.getStatusLine().getStatusCode() == 200) {

	    if (!pokerRoomsRequest.isAtlasError()) {
		//TODO
	    } else {
		logger.appendLogEntry("Failed response from AtlasPokerRooms request"
			+ " - " + mUrl
			+ " - errorType : " + pokerRoomsRequest.getAtlasError().getErrorType()
			+ " - error : " + pokerRoomsRequest.getAtlasError().getError());
	    }
	} else {
	    logger.appendLogEntry("Failed response from AtlasPokerRooms request : " + mUrl);
	}
	
	logger.appendLogEntry("Complete Atlas Poker Rooms Scrape : " + mUrl);
    }
}




























