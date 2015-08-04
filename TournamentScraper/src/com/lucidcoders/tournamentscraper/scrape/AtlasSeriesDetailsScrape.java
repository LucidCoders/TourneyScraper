package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lucidcoders.tournamentscraper.object.SeriesBuilder;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.rest.response.SeriesDetailsResponse;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;

public class AtlasSeriesDetailsScrape {

    List<SeriesResult> mSeriesResults = new ArrayList<SeriesResult>();
    List<SeriesResult> mFailedSeriesResults = new ArrayList<SeriesResult>();
    List<Series> mSeriesDetails = new ArrayList<Series>();
    
    public AtlasSeriesDetailsScrape(List<SeriesResult> seriesResults) {
	mSeriesResults = seriesResults;
    }
    
    public AtlasSeriesDetailsScrape execute() {
	
	ScrapeLogger logger = ScrapeLogger.getInstance();
	logger.appendLogEntry("Begin Atlas Series Details Scrape...");
	
	ImportIoRequest seriesDetailsRequest;
	
	for (SeriesResult seriesResult : mSeriesResults) {
	    seriesDetailsRequest = new ImportIoRequest(seriesResult.getSeriesLink());

	    HttpResponse response;
	    try {
		response = seriesDetailsRequest.queryGet(Extractor.ATLAS_SERIES_DETAILS);
	    } catch (URISyntaxException | IOException e) {
		e.printStackTrace();
		logger.appendLogEntry("Failed to send Series Details request : " + seriesResult.getSeriesLink() + " : "
			+ e.getClass() + " : " + e.getMessage());
		mFailedSeriesResults.add(seriesResult);
		continue;
	    }
	    
	    if (response.getStatusLine().getStatusCode() == 200) {

		if (!seriesDetailsRequest.isAtlasError()) {
		    Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		    SeriesDetailsResponse detailsResponse = gson.fromJson(seriesDetailsRequest.getResult(),
			    SeriesDetailsResponse.class);
		    
		    Series seriesDetails = new SeriesBuilder(detailsResponse, seriesResult).build();
		    mSeriesDetails.add(seriesDetails);
		    
		    //TODO testing series builder - remove later
//			String testResponse = gson.toJson(seriesDetails, Series.class);
//			System.out.println("Testing: " + testResponse);
		    
		} else {
		    mFailedSeriesResults.add(seriesResult);
		    logger.appendLogEntry("Failed response from Series Details request" + " - " + seriesResult.getSeriesLink()
				+ " - errorType : " + seriesDetailsRequest.getAtlasError().getErrorType()
				+ " - error : " + seriesDetailsRequest.getAtlasError().getError());
		}
	    } else {
		mFailedSeriesResults.add(seriesResult);
		logger.appendLogEntry("Failed response from Series Details request : " + seriesResult.getSeriesLink());
	    }
	    
//	    break;//TODO for testing remove later
	}
	
	logger.appendLogEntry("Complete Series Details Scrape");	
	return this;
    }
    
    public List<Series> getSeriesDetails() {
	return mSeriesDetails;
    }
    
    public List<SeriesResult> getFailedSeriesResults() {
	return mFailedSeriesResults;
    }
}
























