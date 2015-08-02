package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class SeriesDetailsResponse extends BaseQueryResponse {

    private List<SeriesDetailResult> results = new ArrayList<SeriesDetailResult>();

    public List<SeriesDetailResult> getResults() {
	return results;
    }

    public void setResults(List<SeriesDetailResult> results) {
	this.results = results;
    }

    public class SeriesDetailResult {

	private String date;
	@SerializedName("casino/_text")
	private String casinoName;
	@SerializedName("casino/_source")
	private String casinoSource;
	@SerializedName("series_name")
	private String seriesName;
	private String description;
	private String location;

	public String getDate() {
	    return date;
	}

	public void setDate(String date) {
	    this.date = date;
	}

	public String getCasinoText() {
	    return casinoName;
	}

	public void setCasinoText(String casinoText) {
	    this.casinoName = casinoText;
	}

	public String getCasinoSource() {
	    return casinoSource;
	}

	public void setCasinoSource(String casinoSource) {
	    this.casinoSource = casinoSource;
	}

	public String getSeriesName() {
	    return seriesName;
	}

	public void setSeriesName(String seriesName) {
	    this.seriesName = seriesName;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getLocation() {
	    return location;
	}

	public void setLocation(String location) {
	    this.location = location;
	}
    }
}
