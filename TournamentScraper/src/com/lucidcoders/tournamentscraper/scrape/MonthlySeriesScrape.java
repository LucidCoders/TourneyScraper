package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.MonthlySeriesResponse;

public class MonthlySeriesScrape {

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest monthlySeriesRequest = new ImportIoRequest(
		"http://www.cardplayer.com/poker-tournaments/monthly/2015/08");

	HttpResponse response = monthlySeriesRequest.queryGet(Extractor.MONTHLY_SERIES);

	if (response.getStatusLine().getStatusCode() == 200) {
	    MonthlySeriesResponse seriesResponse = new Gson().fromJson(monthlySeriesRequest.getResult().toString(),
		    MonthlySeriesResponse.class);

	    String testResponse = new Gson().toJson(seriesResponse, MonthlySeriesResponse.class);
	    System.out.println("Testing: " + testResponse);
	}
    }
}
