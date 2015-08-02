package com.lucidcoders.tournamentscraper.object;

import java.util.List;

import com.lucidcoders.tournamentscraper.rest.response.SeriesDetailsResponse;
import com.lucidcoders.tournamentscraper.rest.response.AtlasSeriesResponse.SeriesResult;
import com.lucidcoders.tournamentscraper.rest.response.SeriesDetailsResponse.SeriesDetailResult;
import com.lucidcoders.tournamentscraper.util.Util;
import com.lucidcoders.tourneyspot.backend.seriesApi.model.Series;

public class SeriesBuilder {

    private SeriesDetailsResponse mSeriesDetailResponse;
    private SeriesResult mSeriesResult;
    
    public SeriesBuilder(SeriesDetailsResponse seriesDetailResponse, SeriesResult seriesResult) {
	mSeriesDetailResponse = seriesDetailResponse;
	mSeriesResult = seriesResult;
    }
    
    public Series build() {
	Series series = null;
	
	List<SeriesDetailResult> seriesDetailResults = mSeriesDetailResponse.getResults();
	if (seriesDetailResults.size() > 0) {
	    series = new Series();
	    
	    SeriesDetailResult seriesDetailResult = seriesDetailResults.get(0);
	    
	    series.setSeriesId(mSeriesResult.getSeriesLinkSource().replace("/poker-tournament-series/", ""));
	    series.setSeriesName(seriesDetailResult.getSeriesName());
	    series.setCasinoId(seriesDetailResult.getCasinoSource().replace("/poker-room/", ""));
	    series.setCasinoName(seriesDetailResult.getCasinoText());
	    
	    String dateStrings[] = seriesDetailResult.getDate().split("-");
	    if (dateStrings[0] != null) {
		series.setStartDate(Util.stringToDateTime(dateStrings[0].trim(), "MMM dd, yyyy"));
		if (dateStrings[1] != null) {
		    series.setEndDate(Util.stringToDateTime(dateStrings[1].trim(), "MMM dd, yyyy"));
		} else {
		    series.setEndDate(Util.stringToDateTime(dateStrings[0].trim(), "MMM dd, yyyy"));
		}
	    }
	    
	    series.setLocation(seriesDetailResult.getLocation());
	    series.setDescription(seriesDetailResult.getDescription());
	}
	
	return series;
    }
}






















