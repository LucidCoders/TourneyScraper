package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.object.CasinoBuilder;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasCasinoResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasPokerRoomsResponse.Result;
import com.lucidcoders.tournamentscraper.util.CasinoDeserializer;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.casinoApi.model.Casino;

public class AtlasCasinoScrape {
    
    private List<Result> mPokerRooms = new ArrayList<Result>();
    private List<Result> mFailedRooms = new ArrayList<Result>();
    private List<Casino> mCasinos = new ArrayList<Casino>();
    
    private ScrapeLogger mLogger;

    public AtlasCasinoScrape(List<Result> pokerRooms) {
	mPokerRooms = pokerRooms;
	mLogger = ScrapeLogger.getInstance();
    }
    
    public AtlasCasinoScrape(List<Result> pokerRooms, ScrapeLogger logger) {
	mPokerRooms = pokerRooms;
	mLogger = logger;
    }
    
    public AtlasCasinoScrape execute() {
	mLogger.appendLogEntry("Begin Atlas Casino Scrape...");
	
	ImportIoRequest casinoRequest;

	for (Result pokerRoom : mPokerRooms) {
	    String url = pokerRoom.getCasinoUrl();
	    casinoRequest = new ImportIoRequest(url);
	    
	    HttpResponse response;
	    try {
		response = casinoRequest.queryGet(Extractor.ATLAS_CASINO);
	    } catch (URISyntaxException | IOException e) {
		e.printStackTrace();
		mLogger.appendLogEntry("Failed to send AtlasCasino request : " + url + " : " + e.getClass() + " : "
			+ e.getMessage());
		mFailedRooms.add(pokerRoom);
		continue;
	    }
	    
	    if (response.getStatusLine().getStatusCode() == 200) {
		if (!casinoRequest.isAtlasError()) {

		    Gson gson = new GsonBuilder().disableHtmlEscaping()
			    .registerTypeAdapter(AtlasCasinoResponse.Result.class, new CasinoDeserializer()).create();

		    AtlasCasinoResponse casinoResponse = gson.fromJson(casinoRequest.getResult(),
			    AtlasCasinoResponse.class);
		    
		    Casino casino = new CasinoBuilder(casinoResponse, pokerRoom).build();
		    mCasinos.add(casino);
		    
//		    String testResponse = gson.toJson(casinoResponse, AtlasCasinoResponse.class);
//		    System.out.println("Testing: " + testResponse);

		} else {
		    mFailedRooms.add(pokerRoom);
		    mLogger.appendLogEntry("Failed response from AtlasCasino request" + " - " + url
				+ " - errorType : " + casinoRequest.getAtlasError().getErrorType()
				+ " - error : " + casinoRequest.getAtlasError().getError());
		}
	    } else {
		mFailedRooms.add(pokerRoom);
		mLogger.appendLogEntry("Failed response from AtlasCasino request : " + url);
	    }
	}
	
	mLogger.appendLogEntry("Complete Atlas Casino Scrape");
	return this;
    }
    
    public List<Casino> getCasinos() {
	return mCasinos;
    }
    
    public List<Result> getFailedRooms() {
	return mFailedRooms;
    }
}





















