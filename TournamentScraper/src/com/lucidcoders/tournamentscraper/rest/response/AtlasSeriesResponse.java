package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AtlasSeriesResponse extends BaseQueryResponse {

    private List<SeriesResult> results = new ArrayList<SeriesResult>();

    public List<SeriesResult> getResults() {
	return results;
    }

    public void setResults(List<SeriesResult> results) {
	this.results = results;
    }

    public class SeriesResult {

	private String series;
	@SerializedName("series_link")
	private String seriesLink;
	@SerializedName("series_link/_text")
	private String seriesLinkText;
	@SerializedName("series_link/_source")
	private String seriesLinkSource;

	public String getSeries() {
	    return series;
	}

	public void setSeries(String series) {
	    this.series = series;
	}

	public String getSeriesLink() {
	    return seriesLink;
	}

	public void setSeriesLink(String seriesLink) {
	    this.seriesLink = seriesLink;
	}

	public String getSeriesLinkText() {
	    return seriesLinkText;
	}

	public void setSeriesLinkText(String seriesLinkText) {
	    this.seriesLinkText = seriesLinkText;
	}

	public String getSeriesLinkSource() {
	    return seriesLinkSource;
	}

	public void setSeriesLinkSource(String seriesLinkSource) {
	    this.seriesLinkSource = seriesLinkSource;
	}
    }
}
