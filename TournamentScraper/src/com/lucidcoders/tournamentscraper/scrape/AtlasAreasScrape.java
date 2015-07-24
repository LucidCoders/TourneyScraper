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

public class AtlasAreasScrape {

    private List<String> areaUrls = new ArrayList<String>();

    public void execute() throws URISyntaxException, IOException {

	ImportIoRequest atlasAreasRequest = new ImportIoRequest("http://www.pokeratlas.com/areas");

	HttpResponse response = atlasAreasRequest.queryGet(Extractor.ATLAS_AREAS);

	if (response.getStatusLine().getStatusCode() == 200) {
	    AtlasAreasResponse areasResponse = new Gson().fromJson(atlasAreasRequest.getResult().toString(),
		    AtlasAreasResponse.class);

	    String testResponse = new Gson().toJson(areasResponse, AtlasAreasResponse.class);
	    System.out.println("Testing: " + testResponse);

	    int count = 1;
	    for (Result resultSet : areasResponse.getResults()) {
		for (String area : resultSet.getArea()) {
		    System.out.println(count + ": " + area);
		    areaUrls.add(area);
		    count++;
		}
	    }
	}
    }

    public List<String> getAreaUrls() {
	return areaUrls;
    }
}
