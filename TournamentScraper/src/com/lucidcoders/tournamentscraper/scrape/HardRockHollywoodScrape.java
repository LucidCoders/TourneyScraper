package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.HardRockHollywoodResponse;
import com.lucidcoders.tournamentscraper.rest.response.HardRockHollywoodResponse.Result;
import com.lucidcoders.tournamentscraper.util.HardRockHollywoodDeserializer;

public class HardRockHollywoodScrape {

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest request = new ImportIoRequest(
		"http://www.seminolehardrockpokeropen.com/daily-tournament-schedule-july-2015/");

	HttpResponse response = request.magicGet();

	if (response.getStatusLine().getStatusCode() == 200) {
	    Gson gson = new GsonBuilder().registerTypeAdapter(Result.class, new HardRockHollywoodDeserializer())
		    .create();

	    HardRockHollywoodResponse hollywoodResponse = gson.fromJson(request.getResult().toString(),
		    HardRockHollywoodResponse.class);

	    String testResponse = new Gson().toJson(hollywoodResponse, HardRockHollywoodResponse.class);
	    System.out.println("Testing: " + testResponse);
	}
    }
}
