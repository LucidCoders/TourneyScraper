package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasUpcomingResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasUpcomingResponse.Result;
import com.lucidcoders.tournamentscraper.util.MyLogger;

public class AtlasUpcomingScrape {

    private String baseUrl = "http://www.pokeratlas.com/poker-tournaments/winnipeg/upcoming";

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest atlasUpcomingRequest = new ImportIoRequest(baseUrl);
	HttpResponse response = atlasUpcomingRequest.queryGet(Extractor.ATLAS_UPCOMING);

	if (response.getStatusLine().getStatusCode() == 200) {
	    MyLogger writer = new MyLogger();
	    writer.writeToLog("*** EVENT LINK RESULTS ***");
	    writer.appendToLog("**************************\n");

	    AtlasUpcomingResponse upcomingResponse = new Gson().fromJson(atlasUpcomingRequest.getResult(),
		    AtlasUpcomingResponse.class);

	    int count = 1;
	    for (Result result : upcomingResponse.getResults()) {
		writer.appendToLog("EventLink #" + count + ": " + result.getEventLink());
		count++;
	    }

	    for (int i = 1; i <= 3; i++) {
		ImportIoRequest atlasUpcomingScrollRequest = new ImportIoRequest(baseUrl + "?page=" + i);

		HttpResponse scrollResponse = atlasUpcomingScrollRequest.queryGet(Extractor.ATLAS_UPCOMING_SCROLL);
		if (scrollResponse.getStatusLine().getStatusCode() != 200) {
		    break;
		}

		AtlasUpcomingResponse upcomingScrollResponse = new Gson().fromJson(
			atlasUpcomingScrollRequest.getResult(), AtlasUpcomingResponse.class);

		for (Result result : upcomingScrollResponse.getResults()) {
		    writer.appendToLog("EventLink #" + count + ": " + result.getEventLink());
		    count++;
		}
	    }
	    writer.closeFile();
	}
	System.out.println("Dunzo!");
    }
}
