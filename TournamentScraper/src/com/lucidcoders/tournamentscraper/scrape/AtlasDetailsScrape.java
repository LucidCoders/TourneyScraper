package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.object.DetailGroup;
import com.lucidcoders.tournamentscraper.object.DetailGroup.TournamentInfoField;
import com.lucidcoders.tournamentscraper.object.TourneyDetails;
import com.lucidcoders.tournamentscraper.object.TourneyDetails.TournamentInfo;
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

	    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	    AtlasDetailsResponse detailsResponse = gson.fromJson(atlasDetailsRequest.getResult(),
		    AtlasDetailsResponse.class);
	    
	    TourneyDetails tourneyDetails = buildTourneyDetails(detailsResponse);

	    System.out.println(gson.toJson(tourneyDetails, TourneyDetails.class));
	}
    }

    private TourneyDetails buildTourneyDetails(AtlasDetailsResponse detailsResponse) {
	
	TourneyDetails tourneyDetails = new TourneyDetails();

	boolean firstPass = true;

	for (Result eachResult : detailsResponse.getResults()) {

	    if (firstPass) {
		tourneyDetails.setCasinoName(eachResult.getCasinoText());
		tourneyDetails.setEventName(eachResult.getEventName());
		tourneyDetails.setSeriesName(eachResult.getSeriesText());
		tourneyDetails.setBuyIn(eachResult.getBuyIn());
		tourneyDetails.setEventDate(eachResult.getEventDate());
		tourneyDetails.setEventTime(eachResult.getEventTime().replaceFirst("m", "m,"));
		tourneyDetails.setAddressText(eachResult.getAddressText());
		tourneyDetails.setAddressUrl(eachResult.getAddressSource());

		firstPass = false;

	    } else if (eachResult.getDetailGroup().equalsIgnoreCase(DetailGroup.TOURNAMENT_INFO.getGroupName())) {

		buildTournamentInfo(tourneyDetails, eachResult);
	    }
	}

	return tourneyDetails;
    }

    private void buildTournamentInfo(TourneyDetails tourneyDetails, Result eachResult) {

	List<String> values = new ArrayList<String>();
	TournamentInfoField currentField = null;
	TournamentInfo tournamentInfo = tourneyDetails.new TournamentInfo();

	for (String string : eachResult.getDetails()) {
	    if (string.equalsIgnoreCase(TournamentInfoField.EVENT_NUMBER.getFieldName())) {

		currentField = TournamentInfoField.EVENT_NUMBER;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_NAME.getFieldName())) {

		currentField = TournamentInfoField.EVENT_NAME;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_TYPE.getFieldName())) {

		currentField = TournamentInfoField.EVENT_TYPE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.GAME_TYPE.getFieldName())) {

		currentField = TournamentInfoField.GAME_TYPE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.EVENT_START_DATE.getFieldName())) {

		currentField = TournamentInfoField.EVENT_START_DATE;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.STARTING_FLIGHTS.getFieldName())) {

		currentField = TournamentInfoField.STARTING_FLIGHTS;
		values = new ArrayList<String>();

	    } else if (string.equalsIgnoreCase(TournamentInfoField.LENGTH_OF_EVENT.getFieldName())) {

		currentField = TournamentInfoField.LENGTH_OF_EVENT;
		values = new ArrayList<String>();

	    } else if (currentField != null) {

		switch (currentField) {

		case EVENT_NUMBER:
		    values.add(string);
		    tournamentInfo.setEventNumber(values);
		    
		    break;

		case EVENT_START_DATE:
		    values.add(string);
		    tournamentInfo.setEventStartDate(values);

		    break;
		default:
		    break;
		    
		}
	    }
	    
	    tourneyDetails.setTourneyInfo(tournamentInfo);
	}
    }
}
































