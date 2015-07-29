package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasUpcomingResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasUpcomingResponse.Result;
import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasUpcomingScrape {

    private int mPageCount;
    private String mBaseUrl;
    private String mScrollUrl;
    private List<String> mEventLinks = new ArrayList<String>();
    
    public AtlasUpcomingScrape(String url, int pageCount) {
	
	mBaseUrl = "http://www.pokeratlas.com/poker-tournaments/" + url.replace("http://www.pokeratlas.com/", "")
		+ "/upcoming";
	mScrollUrl = mBaseUrl + "?page=";
	mPageCount = pageCount;
    }

    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	logger.appendLogEntry("Begin Atlas Upcoming Scrape : " + mBaseUrl);

	ImportIoRequest atlasUpcomingRequest = new ImportIoRequest(mBaseUrl);
	
	HttpResponse response;
	try {
	    response = atlasUpcomingRequest.queryGet(Extractor.ATLAS_UPCOMING);
	} catch (URISyntaxException | IOException e) {
	    e.printStackTrace();
	    logger.appendLogEntry(
		    "Failed to send AtlasUpcoming request : " + mBaseUrl + " : " + e.getClass() + " : " + e.getMessage());
	    logger.appendLogEntry("Complete AtlasUpcoming Scrape : " + mBaseUrl);
	    return;
	}

	if (response.getStatusLine().getStatusCode() == 200) {
	    
	    if (!atlasUpcomingRequest.isAtlasError()) {
		
		AtlasUpcomingResponse upcomingResponse = new Gson().fromJson(atlasUpcomingRequest.getResult(),
			AtlasUpcomingResponse.class);

		for (Result result : upcomingResponse.getResults()) {
		    mEventLinks.add(result.getEventLink());
		}

		for (int i = 1; i <= mPageCount; i++) {
		    
		    String scrollUrl = mScrollUrl + i;
		    ImportIoRequest atlasUpcomingScrollRequest = new ImportIoRequest(scrollUrl);

		    HttpResponse scrollResponse;
		    try {
			scrollResponse = atlasUpcomingScrollRequest.queryGet(Extractor.ATLAS_UPCOMING_SCROLL);
		    } catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			logger.appendLogEntry("Failed to send AtlasUpcomingScroll request : " + scrollUrl + " : "
				+ e.getClass() + " : " + e.getMessage());
			break;
		    }

		    if (scrollResponse.getStatusLine().getStatusCode() == 200) {
			
			if (!atlasUpcomingScrollRequest.isAtlasError()) {
			    
			    AtlasUpcomingResponse upcomingScrollResponse = new Gson().fromJson(
				    atlasUpcomingScrollRequest.getResult(), AtlasUpcomingResponse.class);

			    for (Result result : upcomingScrollResponse.getResults()) {
				mEventLinks.add(result.getEventLink());
			    }
			} else {
			    logger.appendLogEntry("Failed response from AtlasUpcomingScroll request"
					+ " - " + scrollUrl
					+ " - errorType : " + atlasUpcomingScrollRequest.getAtlasError().getErrorType()
					+ " - error : " + atlasUpcomingScrollRequest.getAtlasError().getError());
			    break;
			}
		    } else {
			logger.appendLogEntry("Failed response from AtlasUpcomingScroll request : " + scrollUrl);
			break;
		    }
		}
	    } else {
		logger.appendLogEntry("Failed response from AtlasUpcoming request"
			+ " - " + mBaseUrl
			+ " - errorType : " + atlasUpcomingRequest.getAtlasError().getErrorType()
			+ " - error : " + atlasUpcomingRequest.getAtlasError().getError());
	    }
	} else {
	    logger.appendLogEntry("Failed response from AtlasUpcoming request : " + mBaseUrl);
	}
	
	logger.appendLogEntry("Complete AtlasUpcoming Scrape : " + mBaseUrl);
    }

    public List<String> getEventLinks() {
	return mEventLinks;
    }
}
