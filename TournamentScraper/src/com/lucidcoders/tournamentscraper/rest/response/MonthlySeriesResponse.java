package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MonthlySeriesResponse extends BaseQueryResponse {

    private List<Result> results = new ArrayList<Result>();

    public List<Result> getResults() {
	return results;
    }

    public void setResults(List<Result> results) {
	this.results = results;
    }

    /********************** Inner Classes ***********************/

    public class Result {

	@SerializedName("series/_text")
	private String seriesText;
	private String series;
	private String casino;
	@SerializedName("series/_source")
	private String seriesSource;
	private String location;
	private String dates;
	private String status;

	public String getSeriesText() {
	    return seriesText;
	}

	public void setSeriesText(String seriesText) {
	    this.seriesText = seriesText;
	}

	public String getSeries() {
	    return series;
	}

	public void setSeries(String series) {
	    this.series = series;
	}

	public String getCasino() {
	    return casino;
	}

	public void setCasino(String casino) {
	    this.casino = casino;
	}

	public String getSeriesSource() {
	    return seriesSource;
	}

	public void setSeriesSource(String seriesSource) {
	    this.seriesSource = seriesSource;
	}

	public String getLocation() {
	    return location;
	}

	public void setLocation(String location) {
	    this.location = location;
	}

	public String getDates() {
	    return dates;
	}

	public void setDates(String dates) {
	    this.dates = dates;
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}
    }
}