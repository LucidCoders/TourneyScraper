package com.lucidcoders.tournamentscraper.object;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.api.client.util.Base64;
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
	    
	    if (0 < dateStrings.length) {
		series.setStartDate(Util.stringToDateTime(dateStrings[0].trim(), "MMM dd, yyyy"));
		if (1 < dateStrings.length) {
		    series.setEndDate(Util.stringToDateTime(dateStrings[1].trim(), "MMM dd, yyyy"));
		} else {
		    series.setEndDate(Util.stringToDateTime(dateStrings[0].trim(), "MMM dd, yyyy"));
		}
	    }
	    
	    byte[] imageBytes = null;
	    try {
		imageBytes = Util.downloadImageUrl(new URL(seriesDetailResult.getImage()));
	    } catch (MalformedURLException e) {
		// TODO Maybe add logging here
		e.printStackTrace();
	    }
	    
	    if (imageBytes != null && imageBytes.length > 0) {
		series.setImage(new String(Base64.encodeBase64(imageBytes)));
	    }
	    
	    series.setLocation(seriesDetailResult.getLocation());
	    series.setDescription(seriesDetailResult.getDescription());
	}
	
	return series;
    }
}























