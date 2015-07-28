package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasAreasResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasAreasResponse.Result;
import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasAreasScrape {

    private List<String> mAreaUrls = new ArrayList<String>();

    public void execute() {
	MyLogger logger = MyLogger.getInstance();
	logger.appendLogEntry("Begin Atlas Areas Scrape...", true);
	
	String areasUrl = "http://www.pokeratlas.com/areas";

	ImportIoRequest atlasAreasRequest = new ImportIoRequest(areasUrl);

	HttpResponse response;
	try {
	    response = atlasAreasRequest.queryGet(Extractor.ATLAS_AREAS);
	} catch (URISyntaxException | IOException e) {
	    e.printStackTrace();
	    logger.appendLogEntry(
		    "Failed to send AtlasAreas request : " + areasUrl + " : " + e.getClass() + " : " + e.getMessage());
	    logger.appendLogEntry("Complete Atlas Areas Scrape", true);
	    return;
	}

	if (response.getStatusLine().getStatusCode() == 200) {
	    if (!atlasAreasRequest.isAtlasError()) {
		AtlasAreasResponse areasResponse = new Gson().fromJson(atlasAreasRequest.getResult().toString(),
			AtlasAreasResponse.class);

		String testResponse = new Gson().toJson(areasResponse, AtlasAreasResponse.class);
		System.out.println("Testing: " + testResponse);

		for (Result resultSet : areasResponse.getResults()) {
		    for (String area : resultSet.getArea()) {
			mAreaUrls.add(area);
		    }
		}
	    } else {
		logger.appendLogEntry("Failed response from AtlasAreas request"
			+ " - " + areasUrl
			+ " - errorType : " + atlasAreasRequest.getAtlasError().getErrorType()
			+ " - error : " + atlasAreasRequest.getAtlasError().getError());
	    }
	} else {
	    logger.appendLogEntry("Failed response from AtlasAreas request : " + areasUrl);
	}
	
	logger.appendLogEntry("Complete Atlas Areas Scrape", true);
    }

    public List<String> getAreaUrls() {
	return mAreaUrls;
    }
}
