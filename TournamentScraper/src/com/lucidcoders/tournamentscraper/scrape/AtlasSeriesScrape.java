package com.lucidcoders.tournamentscraper.scrape;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import com.google.gson.Gson;
import com.lucidcoders.tournamentscraper.rest.Extractor;
import com.lucidcoders.tournamentscraper.rest.ImportIoRequest;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.util.ScrapeLogger;

public class AtlasSeriesScrape {

    private ScrapeLogger mLogger;
    private List<SeriesResult> mSeries = new ArrayList<SeriesResult>();
    
    public AtlasSeriesScrape(ScrapeLogger logger) {
	mLogger = logger;
    }

    public AtlasSeriesScrape() {
	mLogger = ScrapeLogger.getInstance();
    }

    public AtlasSeriesScrape execute() {
	mLogger.appendLogEntry("Begin Atlas Series Scrape...");

	String seriesUrl = "http://www.pokeratlas.com/poker-tournament-series";

	ImportIoRequest atlasSeriesRequest = new ImportIoRequest(seriesUrl);

	HttpResponse response;
	try {
	    response = atlasSeriesRequest.queryGet(Extractor.ATLAS_SERIES);
	} catch (URISyntaxException | IOException e) {
	    e.printStackTrace();
	    mLogger.appendLogEntry("Failed to send AtlasSeries request : " + seriesUrl + " : " + e.getClass() + " : "
		    + e.getMessage());
	    mLogger.appendLogEntry("Complete Atlas Series Scrape");
	    return this;
	}

	if (response.getStatusLine().getStatusCode() == 200) {
	    if (!atlasSeriesRequest.isAtlasError()) {
		AtlasSeriesResponse seriesResponse = new Gson().fromJson(atlasSeriesRequest.getResult().toString(),
			AtlasSeriesResponse.class);

		for (SeriesResult result : seriesResponse.getResults()) {
		    mSeries.add(result);
		}
	    } else {
		mLogger.appendLogEntry("Failed response from AtlasSeries request" + " - " + seriesUrl + " - errorType : "
			+ atlasSeriesRequest.getAtlasError().getErrorType() + " - error : "
			+ atlasSeriesRequest.getAtlasError().getError());
	    }
	} else {
	    mLogger.appendLogEntry("Failed response from AtlasSeries request : " + seriesUrl);
	}

	mLogger.appendLogEntry("Complete Atlas Series Scrape");
	return this;
    }

    public List<SeriesResult> getSeriesResults() {
	return mSeries;
    }
}
