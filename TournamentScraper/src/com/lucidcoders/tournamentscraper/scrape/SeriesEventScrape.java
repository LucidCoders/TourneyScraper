package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.rest.response.SeriesEventsResponse;
import com.lucidcoders.tournamentscraper.rest.response.SeriesEventsResponse.SeriesEventResult;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;

public class SeriesEventScrape {
    
    private List<SeriesResult> mSeriesResults = new ArrayList<>();
    private List<SeriesResult> mFailedSeriesResults = new ArrayList<>();
    private List<String> mEventLinks = new ArrayList<>();
    private List<ArrayList<String>> mEventLinksArray = new ArrayList<>();
    
    private ScrapeLogger mLogger;
    
    public SeriesEventScrape(List<SeriesResult> seriesResults) {
	mSeriesResults = seriesResults;
	mLogger = ScrapeLogger.getInstance();
    }
    
    public SeriesEventScrape(List<SeriesResult> seriesResults, ScrapeLogger logger) {
	mSeriesResults = seriesResults;
	mLogger = logger;
    }

    public SeriesEventScrape execute() {
	mLogger.appendLogEntry("Begin Atlas Series Events Scrape...");

	for (SeriesResult seriesResult : mSeriesResults) {
	    ImportIoRequest seriesEventsRequest = new ImportIoRequest(seriesResult.getSeriesLink());

	    HttpResponse response;
	    try {
		response = seriesEventsRequest.queryGet(Extractor.ATLAS_SERIES_EVENTS);
	    } catch (URISyntaxException | IOException e) {
		e.printStackTrace();
		mLogger.appendLogEntry("Failed to send AtlasSeriesEvents request : " + seriesResult.getSeriesLink() + " : " + e.getClass() + " : "
			+ e.getMessage());
		mFailedSeriesResults.add(seriesResult);
		continue;
	    }

	    if (response.getStatusLine().getStatusCode() == 200) {
		if (!seriesEventsRequest.isAtlasError()) {
		    SeriesEventsResponse seriesResponse = new Gson().fromJson(seriesEventsRequest.getResult().toString(),
			    SeriesEventsResponse.class);

		    ArrayList<String> eventLinksForArray = new ArrayList<>();
		    for (SeriesEventResult result : seriesResponse.getResults()) {
			mEventLinks.add(result.getEventLink());
			eventLinksForArray.add(result.getEventLink());
		    }
		    mEventLinksArray.add(eventLinksForArray);
		} else {
		    mLogger.appendLogEntry(
			    "Failed response from AtlasSeriesEvents request" + " - " + seriesResult.getSeriesLink()
				    + " - errorType : " + seriesEventsRequest.getAtlasError().getErrorType()
				    + " - error : " + seriesEventsRequest.getAtlasError().getError());
		    mFailedSeriesResults.add(seriesResult);
		}
	    } else {
		mLogger.appendLogEntry(
			"Failed response from AtlasSeriesEvents request : " + seriesResult.getSeriesLink());
		mFailedSeriesResults.add(seriesResult);
	    }
	}
	
	mLogger.appendLogEntry("Complete Atlas Series Events Scrape");
	return this;
    }

    public List<SeriesResult> getFailedSeriesResults() {
	return mFailedSeriesResults;
    }

    public List<String> getEventLinks() {
	return mEventLinks;
    }

    public List<ArrayList<String>> getEventLinksArray() {
	return mEventLinksArray;
    }
}



















