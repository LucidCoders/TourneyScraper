package com.lucidcoders.tournamentscraper.rest.response;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AtlasDetailsResponse extends BaseQueryResponse {

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
	@SerializedName("address/_source")
	private String addressSource;
	private String address;
	@SerializedName("series/_source")
	private String seriesSource;
	private String casino;
	@SerializedName("casino/_text")
	private String casinoText;
	private String series;
	@SerializedName("event_date")
	private String eventDate;
	@SerializedName("casino/_source")
	private String casinoSource;
	@SerializedName("event_name")
	private String eventName;
	@SerializedName("buy_in")
	private String buyIn;
	@SerializedName("address/_text")
	private String addressText;
	@SerializedName("event_time")
	private String eventTime;
	private List<String> details = new ArrayList<String>();
	@SerializedName("detail_group")
	private String detailGroup;

	public String getSeriesText() {
	    return seriesText;
	}

	public void setSeriesText(String seriesText) {
	    this.seriesText = seriesText;
	}

	public String getAddressSource() {
	    return addressSource;
	}

	public void setAddressSource(String addressSource) {
	    this.addressSource = addressSource;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public String getSeriesSource() {
	    return seriesSource;
	}

	public void setSeriesSource(String seriesSource) {
	    this.seriesSource = seriesSource;
	}

	public String getCasino() {
	    return casino;
	}

	public void setCasino(String casino) {
	    this.casino = casino;
	}

	public String getCasinoText() {
	    return casinoText;
	}

	public void setCasinoText(String casinoText) {
	    this.casinoText = casinoText;
	}

	public String getSeries() {
	    return series;
	}

	public void setSeries(String series) {
	    this.series = series;
	}

	public String getEventDate() {
	    return eventDate;
	}

	public void setEventDate(String eventDate) {
	    this.eventDate = eventDate;
	}

	public String getCasinoSource() {
	    return casinoSource;
	}

	public void setCasinoSource(String casinoSource) {
	    this.casinoSource = casinoSource;
	}

	public String getEventName() {
	    return eventName;
	}

	public void setEventName(String eventName) {
	    this.eventName = eventName;
	}

	public String getBuyIn() {
	    return buyIn;
	}

	public void setBuyIn(String buyIn) {
	    this.buyIn = buyIn;
	}

	public String getAddressText() {
	    return addressText;
	}

	public void setAddressText(String addressText) {
	    this.addressText = addressText;
	}

	public String getEventTime() {
	    return eventTime;
	}

	public void setEventTime(String eventTime) {
	    this.eventTime = eventTime;
	}

	public List<String> getDetails() {
	    return details;
	}

	public void setDetails(List<String> details) {
	    this.details = details;
	}

	public String getDetailGroup() {
	    return detailGroup;
	}

	public void setDetailGroup(String detailGroup) {
	    this.detailGroup = detailGroup;
	}

    }

}
