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

public class AtlasUpcomingScrape {

    private static final int PAGE_COUNT = 4;
    private String mBaseUrl; //= "http://www.pokeratlas.com/poker-tournaments/winnipeg/upcoming";
    private List<String> mEventLinks = new ArrayList<String>();
    
    public AtlasUpcomingScrape(String url) {
	mBaseUrl = url;
    }

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest atlasUpcomingRequest = new ImportIoRequest(mBaseUrl);
	HttpResponse response = atlasUpcomingRequest.queryGet(Extractor.ATLAS_UPCOMING);

	if (response.getStatusLine().getStatusCode() == 200) {

	    AtlasUpcomingResponse upcomingResponse = new Gson().fromJson(atlasUpcomingRequest.getResult(),
		    AtlasUpcomingResponse.class);

	    for (Result result : upcomingResponse.getResults()) {
		mEventLinks.add(result.getEventLink());
	    }

	    for (int i = 1; i <= PAGE_COUNT; i++) {
		ImportIoRequest atlasUpcomingScrollRequest = new ImportIoRequest(mBaseUrl + "?page=" + i);

		HttpResponse scrollResponse = atlasUpcomingScrollRequest.queryGet(Extractor.ATLAS_UPCOMING_SCROLL);
		if (scrollResponse.getStatusLine().getStatusCode() != 200) {
		    break;
		}

		AtlasUpcomingResponse upcomingScrollResponse = new Gson().fromJson(
			atlasUpcomingScrollRequest.getResult(), AtlasUpcomingResponse.class);

		for (Result result : upcomingScrollResponse.getResults()) {
		    mEventLinks.add(result.getEventLink());
		}
	    }
	}
	System.out.println("Dunzo!");
    }
    
    public List<String> getEventLinks() {
	return mEventLinks;
    }
}
