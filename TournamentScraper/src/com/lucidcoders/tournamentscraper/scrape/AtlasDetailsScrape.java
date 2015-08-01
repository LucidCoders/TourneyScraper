package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.object.TourneyDetailsBuilder;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasDetailsResponse;
import com.lucidcoders.tournamentscraper.util.MyLogger;
import com.lucidcoders.tourneyspot.backend.tourneyDetailApi.model.TourneyDetails;

public class AtlasDetailsScrape {

    private List<String> mUrls;
    private List<TourneyDetails> mEventDetails = new ArrayList<TourneyDetails>();
    private List<String> mFailedUrls = new ArrayList<String>();
    
    public AtlasDetailsScrape(List<String> urls) {
	mUrls = urls;
    }

    public void execute() {
	
	MyLogger logger = MyLogger.getInstance();
	logger.appendLogEntry("Begin Atlas Details Scrape...");
	
	ImportIoRequest atlasDetailsRequest;

	for (String url : mUrls) {
	    
	    atlasDetailsRequest = new ImportIoRequest(url);

	    HttpResponse response;
	    try {
		response = atlasDetailsRequest.queryGet(Extractor.ATLAS_DETAILS_5_FIELDS);
	    } catch (URISyntaxException | IOException e) {
		e.printStackTrace();
		logger.appendLogEntry("Failed to send AtlasDetails request : " + url + " : " + e.getClass() + " : "
			+ e.getMessage());
		mFailedUrls.add(url);
		continue;
	    }

	    if (response.getStatusLine().getStatusCode() == 200) {

		if (!atlasDetailsRequest.isAtlasError()) {

		    Gson gson = new GsonBuilder().setDateFormat("EEEE, MMM d, yyyy").disableHtmlEscaping().create();

		    AtlasDetailsResponse detailsResponse = gson.fromJson(atlasDetailsRequest.getResult(),
			    AtlasDetailsResponse.class);

		    if (detailsResponse.getResults().size() == 1) {
			
			try {
			    response = atlasDetailsRequest.queryGet(Extractor.ATLAS_DETAILS_6_FIELDS);
			} catch (URISyntaxException | IOException e) {
			    e.printStackTrace();
			    logger.appendLogEntry("Failed to send AtlasDetails request : " + url + " : " + e.getClass()
				    + " : " + e.getMessage());
			    mFailedUrls.add(url);
			    continue;
			}

			if (response.getStatusLine().getStatusCode() == 200) {

			    if (!atlasDetailsRequest.isAtlasError()) {
				detailsResponse = gson.fromJson(atlasDetailsRequest.getResult(),
					AtlasDetailsResponse.class);
			    } else {
				logger.appendLogEntry("Failed response from AtlasDetails request" + " - " + url
					+ " - errorType : " + atlasDetailsRequest.getAtlasError().getErrorType()
					+ " - error : " + atlasDetailsRequest.getAtlasError().getError());
				mFailedUrls.add(url);
				continue;
			    }

			} else {
			    logger.appendLogEntry("Failed response from AtlasDetails request : " + url);
			    mFailedUrls.add(url);
			    continue;
			}
		    }

		    TourneyDetails tourneyDetails = new TourneyDetailsBuilder(detailsResponse, url).build();
		    mEventDetails.add(tourneyDetails);
		    
		} else {
		    mFailedUrls.add(url);
		    logger.appendLogEntry("Failed response from AtlasDetails request" + " - " + url
				+ " - errorType : " + atlasDetailsRequest.getAtlasError().getErrorType()
				+ " - error : " + atlasDetailsRequest.getAtlasError().getError());
		}
	    } else {
		mFailedUrls.add(url);
		logger.appendLogEntry("Failed response from AtlasDetails request : " + url);
	    }
	}
	
	logger.appendLogEntry("Complete Atlas Details Scrape");
    }
    
    public List<TourneyDetails> getEventDetails() {
	return mEventDetails;
    }
    
    public List<String> getFailedUrls() {
	return mFailedUrls;
    }
}
