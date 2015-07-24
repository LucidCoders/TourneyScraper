package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.object.DetailGroup;
import com.lucidcoders.tournamentscraper.object.TourneyDetails;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasDetailsResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasDetailsResponse.Result;

public class AtlasDetailsScrape {

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest atlasDetailsRequest = new ImportIoRequest(
		"http://www.pokeratlas.com/poker-tournament/2015-wpt-deepstacks-chicagoland-event-14-1100-nl-holdem-no-limit-holdem-main-event-majestic-star?topid=105873-3896");

	HttpResponse response = atlasDetailsRequest.queryGet(Extractor.ATLAS_DETAILS);

	if (response.getStatusLine().getStatusCode() == 200) {
	    
	    AtlasDetailsResponse detailsResponse = new Gson().fromJson(atlasDetailsRequest.getResult(),
		    AtlasDetailsResponse.class);
	    TourneyDetails tourneyDetails = new TourneyDetails();

	    boolean firstPass = true;

	    for (Result eachResult : detailsResponse.getResults()) {

		if (firstPass) {
		    tourneyDetails.setCasinoName(eachResult.getCasino());
		    tourneyDetails.setEventName(eachResult.getEventName());
		    tourneyDetails.setSeriesName(eachResult.getSeriesText());
		    tourneyDetails.setBuyIn(eachResult.getBuyIn());
		    tourneyDetails.setEventDate(eachResult.getEventDate());
		    tourneyDetails.setEventTime(eachResult.getEventTime().replaceFirst("m", "m,"));
		    tourneyDetails.setAddressText(eachResult.getAddressText());
		    tourneyDetails.setAddressUrl(eachResult.getAddressSource());

		    firstPass = false;

		} else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.TOURNAMENT_INFO.getGroupName())) {

		    for (String eachString : eachResult.getDetails()) {
			
		    }
		}
	    }

	    System.out.println(new Gson().toJson(tourneyDetails, TourneyDetails.class));
	}
    }
}




























