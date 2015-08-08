package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tournamentscraper.util.PokerRoomDeserializer;

public class AtlasPokerRoomsScrape {

    private String mUrl;
    private List<Result> mPokerRooms = new ArrayList<Result>();
    private ScrapeLogger mLogger;
    
    public AtlasPokerRoomsScrape(String areaUrl) {
	mUrl = "http://www.pokeratlas.com/poker-rooms/" + areaUrl.replace("http://www.pokeratlas.com/", "");
	mLogger = ScrapeLogger.getInstance();
    }
    
    public AtlasPokerRoomsScrape(String areaUrl, ScrapeLogger logger) {
	mUrl = "http://www.pokeratlas.com/poker-rooms/" + areaUrl.replace("http://www.pokeratlas.com/", "");
	mLogger = logger;
    }
    
    public void execute() {
	mLogger.appendLogEntry("Begin Atlas Poker Rooms Scrape : " + mUrl);
	
	ImportIoRequest pokerRoomsRequest = new ImportIoRequest(mUrl);
	
	HttpResponse response;
	try {
	    response = pokerRoomsRequest.queryGet(Extractor.ATLAS_POKER_ROOMS);
	} catch (URISyntaxException | IOException e) {
	    e.printStackTrace();
	    mLogger.appendLogEntry(
		    "Failed to send AtlasPokerRooms request : " + mUrl + " : " + e.getClass() + " : " + e.getMessage());
	    mLogger.appendLogEntry("Complete Atlas Poker Rooms Scrape : " + mUrl);
	    return;
	}

	if (response.getStatusLine().getStatusCode() == 200) {

	    if (!pokerRoomsRequest.isAtlasError()) {

		Gson gson = new GsonBuilder().registerTypeAdapter(Result.class, new PokerRoomDeserializer())
			.disableHtmlEscaping().create();

		AtlasPokerRoomsResponse roomsResponse = gson.fromJson(pokerRoomsRequest.getResult(),
			AtlasPokerRoomsResponse.class);
		
		mPokerRooms = roomsResponse.getResults();
		
//		String testResponse = gson.toJson(roomsResponse, AtlasPokerRoomsResponse.class);
//		System.out.println("Testing: " + testResponse);

	    } else {
		mLogger.appendLogEntry("Failed response from AtlasPokerRooms request"
			+ " - " + mUrl
			+ " - errorType : " + pokerRoomsRequest.getAtlasError().getErrorType()
			+ " - error : " + pokerRoomsRequest.getAtlasError().getError());
	    }
	} else {
	    mLogger.appendLogEntry("Failed response from AtlasPokerRooms request : " + mUrl);
	}
	
	mLogger.appendLogEntry("Complete Atlas Poker Rooms Scrape : " + mUrl);
    }
    
    public List<Result> getPokerRooms() {
	return mPokerRooms;
    }
}




























